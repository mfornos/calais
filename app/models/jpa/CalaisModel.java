package models.jpa;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import mx.bigdata.jcalais.CalaisClient;
import mx.bigdata.jcalais.CalaisConfig;
import mx.bigdata.jcalais.CalaisConfig.ProcessingParam;
import mx.bigdata.jcalais.CalaisObject;
import mx.bigdata.jcalais.CalaisResponse;
import play.db.jpa.JPA;
import play.db.jpa.Model;
import play.exceptions.UnexpectedException;
import play.modules.calais.CalaisPlugin;
import play.modules.calais.Semantic;

@Entity
public class CalaisModel extends Model {

    private static final long serialVersionUID = 1385191760390406048L;

    @OneToOne(mappedBy = "reference")
    public CalaisDocument document;

    public CalaisDocument analize() {
        CalaisDocument document = CalaisDocument.find("from CalaisDocument where reference.id = ?", id).first();
        if (document == null) {
            document = new CalaisDocument();
            document.reference = this;
            document.save();
            this.document = document;
        } else {
            document.clear();
        }

        Class<? extends CalaisModel> clazz = getClass();
        for (Field f : CalaisPlugin.getFields(clazz)) {
            try {
                String content = (String) f.get(this);

                // TODO process urls...

                CalaisConfig conf = new CalaisConfig();
                Semantic fan = f.getAnnotation(Semantic.class);
                conf.set(ProcessingParam.CONTENT_TYPE, fan.contentType());
                analyze(document, content, conf);
            } catch (IllegalArgumentException e) {
                throw new UnexpectedException(e);
            } catch (IllegalAccessException e) {
                throw new UnexpectedException(e);
            }
        }

        return document;
    }

    public void analyze(CalaisDocument document, String content, CalaisConfig conf) {
        CalaisClient client = CalaisPlugin.getCalaisClient();
        try {
            CalaisResponse response = (conf == null) ? client.analyze(content) : client.analyze(content, conf);
            addTopics(document, response);
            addSocialTags(document, response);
            addEntities(document, response);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }

    private void addEntities(CalaisDocument document2, CalaisResponse response) {
        for (CalaisObject entity : response.getEntities()) {
            String oid = entity.getField("_uri");

            SemEntity sentity = SemEntity.findById(oid);

            if (sentity == null) {
                sentity = new SemEntity();
                sentity.name = entity.getField("name");
                sentity.type = entity.getField("_type");
                sentity.id = oid;
                sentity = JPA.em().merge(sentity).save();
            }

            SemEntityDetection ed = new SemEntityDetection();
            ed.document = document;
            ed.relevance = getDouble(entity.getField("relevance"));
            ed.entity = sentity;
            document.entities.add(ed);
            ed.save();
        }

    }

    private void addSocialTags(CalaisDocument document, CalaisResponse response) {
        for (CalaisObject tag : response.getSocialTags()) {
            String oid = tag.getField("socialTag");

            SocialTag stag = SocialTag.findById(oid);

            if (stag == null) {
                stag = new SocialTag();
                stag.name = tag.getField("name");
                stag.id = oid;
                stag = JPA.em().merge(stag).save();
            }

            SocialTagDetection st = new SocialTagDetection();
            st.document = document;
            st.importance = getInt(tag.getField("importance"));
            st.socialTag = stag;
            document.socialTags.add(st);
            st.save();
        }

    }

    private void addTopics(CalaisDocument document, CalaisResponse response) {
        for (CalaisObject topic : response.getTopics()) {
            String oid = topic.getField("category");

            Topic socialTopic = Topic.findById(oid);

            if (socialTopic == null) {
                socialTopic = new Topic();
                socialTopic.name = topic.getField("categoryName");
                socialTopic.id = oid;
                socialTopic = JPA.em().merge(socialTopic).save();
            }

            TopicDetection td = new TopicDetection();
            td.document = document;
            td.score = getDouble(topic.getField("score"));
            td.topic = socialTopic;
            document.topics.add(td);
            td.save();
        }
    }

    private double getDouble(String v) {
        double d = (v == null) ? 0 : Double.parseDouble(v);
        return d;
    }

    private int getInt(String v) {
        int i = (v == null) ? 0 : Integer.parseInt(v);
        return i;
    }
}

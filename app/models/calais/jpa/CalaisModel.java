package models.calais.jpa;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;

import models.calais.jpa.CalaisDocument;
import models.calais.jpa.CalaisModel;
import models.calais.jpa.Detection;
import models.calais.jpa.DetectionBase;
import models.calais.jpa.Resolution;
import models.calais.jpa.SocialTag;
import models.calais.jpa.SocialTagDetection;
import models.calais.jpa.Topic;
import models.calais.jpa.TopicDetection;
import models.calais.jpa.entities.CalaisEntity;
import models.calais.jpa.entities.CalaisEntityDetection;
import models.calais.jpa.facts.Fact;
import mx.bigdata.jcalais.CalaisClient;
import mx.bigdata.jcalais.CalaisConfig;
import mx.bigdata.jcalais.CalaisConfig.ProcessingParam;
import mx.bigdata.jcalais.CalaisObject;
import mx.bigdata.jcalais.CalaisResponse;

import org.apache.commons.beanutils.ConvertUtils;

import play.Logger;
import play.Play;
import play.db.jpa.JPA;
import play.db.jpa.Model;
import play.exceptions.UnexpectedException;
import play.modules.calais.CalaisPlugin;
import play.modules.calais.Semantic;

@Entity
public class CalaisModel extends Model {

    static final String MODELS_JPA_FACTS = "models.calais.jpa.facts.";

    static final String MODELS_JPA_ENTITIES = "models.calais.jpa.entities.";

    public enum AnalysisType {
        FULL, TOPICS, SOCIAL_TAGS, ENTITIES
    }

    private static final String RESOLUTIONS = "resolutions";

    private static final long serialVersionUID = 1385191760390406048L;

    @OneToOne(mappedBy = "reference")
    public CalaisDocument document;

    public CalaisDocument analyze() {
        return analyze(EnumSet.of(AnalysisType.FULL));
    }

    public CalaisDocument analyze(EnumSet<AnalysisType> requestedAnalysis) {
        if (document == null) {
            document = new CalaisDocument();
            document.reference = this;
            document.save();
        } else {
            document.clear();
        }

        Class<? extends CalaisModel> clazz = getClass();
        for (Field f : CalaisPlugin.getFields(clazz)) {
            try {
                String content = (String) f.get(this);
                CalaisConfig conf = new CalaisConfig();
                Semantic fan = f.getAnnotation(Semantic.class);
                conf.set(ProcessingParam.CONTENT_TYPE, fan.contentType());
                analyze(content, conf, requestedAnalysis);
            } catch (IllegalArgumentException e) {
                throw new UnexpectedException(e);
            } catch (IllegalAccessException e) {
                throw new UnexpectedException(e);
            }
        }

        return document;
    }

    @PreRemove
    protected void preRemove() {
        if (document != null) {
            document.delete();
        }
    }

    private void addEntities(CalaisResponse response) {
        for (CalaisObject entity : response.getEntities()) {
            String oid = entity.getField("_uri");
            CalaisEntity sentity = CalaisEntity.findById(oid);
            if (sentity == null) {
                sentity = mapCalaisObject(oid, MODELS_JPA_ENTITIES, entity);
            }

            if (sentity != null) {
                CalaisEntityDetection ed = new CalaisEntityDetection();
                extractDetections(entity, ed.instances);
                sentity.detections.add(ed);
                ed.document = document;
                ed.relevance = getDouble(entity.getField("relevance"));
                ed.entity = sentity;
                document.entities.add((CalaisEntityDetection) ed.save());
            }
        }

    }

    private void addFacts(CalaisResponse response) {
        for (CalaisObject fact : response.getRelations()) {
            String oid = fact.getField("_uri");
            Fact sfact = Fact.findById(oid);

            if (sfact == null) {
                sfact = mapCalaisObject(oid, MODELS_JPA_FACTS, fact);
            }

            if (sfact != null) {
                document.facts.add(sfact);
            }
        }

    }

    private void addSocialTags(CalaisResponse response) {
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

    private void addTopics(CalaisResponse response) {
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

    private void analyze(String content, CalaisConfig conf, EnumSet<AnalysisType> requestedAnalysis) {
        CalaisClient client = CalaisPlugin.getCalaisClient();
        boolean isFull = requestedAnalysis.contains(AnalysisType.FULL);
        try {
            CalaisResponse response = (conf == null) ? client.analyze(content) : client.analyze(content, conf);
            if (isFull || requestedAnalysis.contains(AnalysisType.TOPICS)) {
                addTopics(response);
            }
            if (isFull || requestedAnalysis.contains(AnalysisType.SOCIAL_TAGS)) {
                addSocialTags(response);
            }
            if (isFull || requestedAnalysis.contains(AnalysisType.ENTITIES)) {
                addEntities(response);
                document.save();
                addFacts(response);
            }
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void extractDetections(CalaisObject entity, List<Detection> detections) {
        Iterable<Map<String, Object>> instances = entity.getList("instances");
        for (Map<String, Object> instance : instances) {
            Detection detection = new Detection();
            detection.detection = (String) instance.get("detection");
            detection.exact = (String) instance.get("exact");
            detection.prefix = (String) instance.get("prefix");
            detection.suffix = (String) instance.get("suffix");
            detection.offset = (Integer) instance.get("offset");
            detection.length = (Integer) instance.get("length");
            detections.add(detection);
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

    private void map(CalaisObject item, DetectionBase obj, String oid) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getFields();
        List<Field> relationsToMap = new ArrayList<Field>();
        for (Field f : fields) {
            try {
                String name = f.getName().toLowerCase();
                if (!Collection.class.isAssignableFrom(f.getType())) {
                    if (CalaisEntity.class.isAssignableFrom(f.getType())) {
                        relationsToMap.add(f);
                    } else {
                        f.set(obj, ConvertUtils.convert(item.getField(name), f.getType()));
                    }
                } else {
                    Type genericType = f.getGenericType();
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        Type[] actualTypeArguments = pt.getActualTypeArguments();
                        if (CalaisEntity.class.isAssignableFrom((Class<?>) actualTypeArguments[0])) {
                            relationsToMap.add(f);
                        }
                    }
                    if (RESOLUTIONS.equalsIgnoreCase(f.getName())) {
                        @SuppressWarnings("unchecked")
                        Iterable<Map<String, Object>> resolutions = item.getList(RESOLUTIONS);
                        if (resolutions != null) {
                            f.set(obj, mapResolutions(resolutions));
                        }
                    }
                }
            } catch (SecurityException e) {
                throw new UnexpectedException(e);
            } catch (IllegalArgumentException e) {
                throw new UnexpectedException(e);
            } catch (IllegalAccessException e) {
                throw new UnexpectedException(e);
            }
        }

        obj.id = oid;
        obj = obj.save();

        for (Field f : relationsToMap) {
            try {
                mapRelation(item, (Fact) obj, f, f.getName());
            } catch (IllegalAccessException e) {
                throw new UnexpectedException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends DetectionBase> T mapCalaisObject(String oid, String packageName, CalaisObject item) {
        DetectionBase obj = null;
        String type = item.getField("_type");

        Class<? extends DetectionBase> clazz = Play.classloader.getClassIgnoreCase(packageName + type);
        if (clazz != null) {
            try {
                obj = clazz.newInstance();
            } catch (InstantiationException e) {
                throw new UnexpectedException(e);
            } catch (IllegalAccessException e) {
                throw new UnexpectedException(e);
            }
            map(item, obj, oid);
        }

        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    private void mapRelation(CalaisObject item, Fact obj, Field f, String name) throws IllegalAccessException {
        try {
            if (!Collection.class.isAssignableFrom(f.getType())) {
                String eid = item.getField(name);
                if (eid != null) {
                    CalaisEntity entity = (CalaisEntity) f.getType().getMethod("findById", Object.class)
                            .invoke(null, eid);
                    if (entity != null) {
                        f.set(obj, entity);
                        entity.facts.add(obj);
                        entity.save();
                    } else {
                        Logger.warn("No entity for field: %s", f.getName());
                    }
                }
            } else {
                Iterable<String> items = item.getList(name);
                Collection<CalaisEntity> col = (Collection<CalaisEntity>) f.get(obj);
                for (String eid : items) {
                    Class<? extends CalaisEntity> clazz = (Class<? extends CalaisEntity>) ((ParameterizedType) f
                            .getGenericType()).getActualTypeArguments()[0];
                    CalaisEntity entity = (CalaisEntity) clazz.getMethod("findById", Object.class).invoke(null, eid);
                    if (entity != null) {
                        col.add(entity);
                        entity.facts.add(obj);
                        entity.save();
                    } else {
                        Logger.warn("No entity for field: %s", f.getName());
                    }
                }
            }
        } catch (InvocationTargetException e) {
            throw new UnexpectedException(e);
        } catch (NoSuchMethodException e) {
            throw new UnexpectedException(e);
        }
    }

    private Set<Resolution> mapResolutions(Iterable<Map<String, Object>> resolutions) {
        Set<Resolution> result = new HashSet<Resolution>();
        Field[] fields = Resolution.class.getFields();

        for (Map<String, Object> resolution : resolutions) {
            Resolution res = new Resolution();
            for (Field f : fields) {
                try {
                    String name = f.getName().toLowerCase();
                    f.set(res, ConvertUtils.convert(resolution.get(name), f.getType()));
                } catch (SecurityException e) {
                    throw new UnexpectedException(e);
                } catch (IllegalArgumentException e) {
                    throw new UnexpectedException(e);
                } catch (IllegalAccessException e) {
                    throw new UnexpectedException(e);
                }
            }
            result.add(res);
        }
        return result;
    }
}

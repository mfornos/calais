package models.calais.jpa;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;

import models.calais.jpa.entities.CalaisEntity;
import models.calais.jpa.entities.CalaisEntityDetection;
import models.calais.jpa.facts.Fact;
import play.Play;
import play.db.jpa.Model;
import play.exceptions.UnexpectedException;

@Entity
public class CalaisDocument extends Model {
    private static final long serialVersionUID = 3473635097597885203L;

    @OneToOne
    public CalaisModel reference;

    @OneToMany(mappedBy = "document")
    public Set<TopicDetection> topics = new HashSet<TopicDetection>();

    @OneToMany(mappedBy = "document")
    public Set<SocialTagDetection> socialTags = new HashSet<SocialTagDetection>();

    @OneToMany(mappedBy = "document")
    public Set<CalaisEntityDetection> entities = new HashSet<CalaisEntityDetection>();

    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Fact> facts = new HashSet<Fact>();

    public Date updated;

    public Date created;

    public void clear() {
        preRemove();
        save();
        topics = new HashSet<TopicDetection>();
        socialTags = new HashSet<SocialTagDetection>();
        entities = new HashSet<CalaisEntityDetection>();
        facts = new HashSet<Fact>();
        save();
    }

    public List<CalaisEntity> getEntitiesByType(String type) {
        if (Play.classloader.getClassIgnoreCase(CalaisModel.MODELS_JPA_ENTITIES + type) == null) {
            throw new UnexpectedException("Invalid entity class " + type);
        }
        return find(
                "from CalaisEntity ent where ent.class = "
                        + type
                        + " and exists (select id from CalaisEntityDetection where document.id = ? and entity.id = ent.id )",
                id).fetch();
    }

    @PreRemove
    protected void preRemove() {
        deleteRefs(topics);
        deleteRefs(socialTags);
        deleteRefs(entities);
        facts = null;
    }

    private void deleteRefs(Collection<? extends Model> collection) {
        for (Model d : collection) {
            d.delete();
        }
    }
}

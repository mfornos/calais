package models.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Query;

import models.jpa.entities.CalaisEntityDetection;
import models.jpa.entities.City;
import models.jpa.facts.Fact;
import play.db.jpa.JPA;
import play.db.jpa.Model;

@Entity
@NamedQuery(name = "CalaisDocument.findAllCities", query = "from CalaisEntity ent where ent.class = City and exists (select id from CalaisEntityDetection where document.id = :docid and entity.id = ent.id )")
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

    @ManyToMany
    public Set<Fact> facts = new HashSet<Fact>();

    public Date updated;

    public Date created;

    public void clear() {
        topics = new HashSet<TopicDetection>();
        socialTags = new HashSet<SocialTagDetection>();
        entities = new HashSet<CalaisEntityDetection>();
        facts = new HashSet<Fact>();
        save();
    }

    @SuppressWarnings("unchecked")
    public List<City> getCities() {
        Query q = JPA.em().createNamedQuery("CalaisDocument.findAllCities");
        q.setParameter("docid", id);
        return q.getResultList();
    }
}

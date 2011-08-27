package models.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

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
    public Set<SemEntityDetection> entities = new HashSet<SemEntityDetection>();

    public Date updated;

    public Date created;

    public void clear() {
        topics = new HashSet<TopicDetection>();
        socialTags = new HashSet<SocialTagDetection>();
        entities = new HashSet<SemEntityDetection>();
        save();
    }
}

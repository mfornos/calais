package models.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

@Entity(name = "topics")
@Table(indexes = { @Index(name = "idx_topic_name", columnNames = { "name" }) }, appliesTo = "topics")
public class Topic extends DetectionBase {
    private static final long serialVersionUID = -2416784342637040547L;

    @OneToMany(mappedBy = "topic")
    public Set<TopicDetection> topics = new HashSet<TopicDetection>();

    public static Topic findByName(String name) {
        return find("from Topic where name = ?", name).first();
    }

}

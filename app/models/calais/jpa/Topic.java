package models.calais.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

@Entity
@Table(indexes = { @Index(name = "calais_idx_topic_name", columnNames = { "name" }) }, appliesTo = "calais_Topic")
public class Topic extends DetectionBase {
    private static final long serialVersionUID = -2416784342637040547L;

    @OneToMany(mappedBy = "topic")
    public Set<TopicDetection> topics = new HashSet<TopicDetection>();

    public static Topic findByName(String name) {
        return find("from Topic where name = ?", name).first();
    }

}

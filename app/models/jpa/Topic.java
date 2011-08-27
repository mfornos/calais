package models.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Topic extends DetectionBase {
    private static final long serialVersionUID = -2416784342637040547L;

    @OneToMany(mappedBy = "topic")
    public Set<TopicDetection> topics = new HashSet<TopicDetection>();

}

package models.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class TopicDetection extends Model implements Comparable<TopicDetection> {
    private static final long serialVersionUID = 5721283424524540514L;

    @ManyToOne
    public CalaisDocument document;

    @ManyToOne
    public Topic topic;

    public double score;

    @Override
    public int compareTo(TopicDetection o) {
        return ((Double) o.score).compareTo(score);
    }
}

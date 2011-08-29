package models.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class SocialTagDetection extends Model implements Comparable<SocialTagDetection> {

    private static final long serialVersionUID = -7268896174840894013L;

    @ManyToOne
    public CalaisDocument document;

    @ManyToOne
    public SocialTag socialTag;

    public int importance;

    @Override
    public int compareTo(SocialTagDetection o) {
        return ((Integer) o.importance).compareTo(importance);
    }
}

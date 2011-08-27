package models.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class SemEntityDetection extends Model {
    private static final long serialVersionUID = 2865413274776012405L;
    @ManyToOne
    public CalaisDocument document;

    @ManyToOne
    public SemEntity entity;

    public double relevance;
}

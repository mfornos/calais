package models.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.jpa.CalaisDocument;
import play.db.jpa.Model;

@Entity
public class CalaisEntityDetection extends Model implements Comparable<CalaisEntityDetection> {
    private static final long serialVersionUID = 2865413274776012405L;

    @ManyToOne
    public CalaisDocument document;

    @ManyToOne
    public CalaisEntity entity;

    public double relevance;

    @Override
    public int compareTo(CalaisEntityDetection o) {
        return ((Double) o.relevance).compareTo(relevance);
    }
}

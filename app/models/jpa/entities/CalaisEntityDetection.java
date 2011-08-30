package models.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.jpa.CalaisDocument;
import models.jpa.Detection;
import play.db.jpa.Model;

@Entity
public class CalaisEntityDetection extends Model implements Comparable<CalaisEntityDetection> {
    private static final long serialVersionUID = 2865413274776012405L;

    @ManyToOne
    public CalaisDocument document;

    @ManyToOne
    public CalaisEntity entity;

    public double relevance;
    
    @OneToMany(cascade = CascadeType.ALL)
    public List<Detection> instances = new ArrayList<Detection>();

    @Override
    public int compareTo(CalaisEntityDetection o) {
        return ((Double) o.relevance).compareTo(relevance);
    }
}

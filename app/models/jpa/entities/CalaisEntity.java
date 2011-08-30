package models.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import models.jpa.DetectionBase;
import models.jpa.facts.Fact;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.STRING)
public class CalaisEntity extends DetectionBase {

    private static final long serialVersionUID = 7187361170356880417L;

    @OneToMany(mappedBy = "entity")
    public Set<CalaisEntityDetection> detections = new HashSet<CalaisEntityDetection>();

    public boolean hasGeoMap() {
        return false;
    }

    @ManyToMany
    public Set<Fact> facts = new HashSet<Fact>();
}

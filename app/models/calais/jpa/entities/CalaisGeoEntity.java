package models.calais.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import models.calais.jpa.Resolution;
import models.calais.jpa.entities.CalaisEntity;

@Entity
public class CalaisGeoEntity extends CalaisEntity {
    private static final long serialVersionUID = 5423866800848899171L;

    @ElementCollection
    public Set<Resolution> resolutions = new HashSet<Resolution>();

    public boolean hasGeoMap() {
        for (Resolution r : resolutions) {
            if (r.latitude != null && r.longitude != null) {
                return true;
            }
        }
        return false;
    }
}

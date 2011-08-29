package models.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import models.jpa.Resolution;

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

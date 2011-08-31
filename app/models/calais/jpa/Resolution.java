package models.calais.jpa;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class Resolution {
    @Basic
    public Double longitude;

    @Basic
    public Double latitude;

    @Basic
    public String name;

    @Basic
    public String shortname;

    @Basic
    public String containedbystate;

    @Basic
    public String containedbycountry;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((containedbycountry == null) ? 0 : containedbycountry.hashCode());
        result = prime * result + ((containedbystate == null) ? 0 : containedbystate.hashCode());
        result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
        result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((shortname == null) ? 0 : shortname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Resolution other = (Resolution) obj;
        if (containedbycountry == null) {
            if (other.containedbycountry != null)
                return false;
        } else if (!containedbycountry.equals(other.containedbycountry))
            return false;
        if (containedbystate == null) {
            if (other.containedbystate != null)
                return false;
        } else if (!containedbystate.equals(other.containedbystate))
            return false;
        if (latitude == null) {
            if (other.latitude != null)
                return false;
        } else if (!latitude.equals(other.latitude))
            return false;
        if (longitude == null) {
            if (other.longitude != null)
                return false;
        } else if (!longitude.equals(other.longitude))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (shortname == null) {
            if (other.shortname != null)
                return false;
        } else if (!shortname.equals(other.shortname))
            return false;
        return true;
    }
}

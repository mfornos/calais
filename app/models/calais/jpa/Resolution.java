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
}

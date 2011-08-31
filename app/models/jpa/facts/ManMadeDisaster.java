package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import models.jpa.entities.Facility;

@Entity
@DiscriminatorValue("ManMadeDisaster")
public class ManMadeDisaster extends Fact {
    private static final long serialVersionUID = -5775055754883658607L;
    @Lob
    public String effect;
    public String manMadeDisaster;
    public String location;
    public String locationType;
    public String date;
    public String dateString;
    @OneToOne(cascade = CascadeType.ALL)
    public Facility facility; 
}

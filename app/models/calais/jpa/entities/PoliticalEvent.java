package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PoliticalEvent")
public class PoliticalEvent extends CalaisEntity {
    private static final long serialVersionUID = -8951694179678654209L;

    public String politicalEventType;
    public String dateString;
    public String date;
    public String location;
}

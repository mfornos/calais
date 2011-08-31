package models.jpa.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("NaturalDisaster")
public class NaturalDisaster extends Fact {
    private static final long serialVersionUID = -2119817223228934229L;
    @Lob
    public String effect;
    public String naturalDisaster;
    public String location;
    public String locationType;
    public String date;
    public String dateString;
}

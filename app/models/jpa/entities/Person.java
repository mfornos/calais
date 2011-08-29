package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Person")
public class Person extends CalaisEntity {

    private static final long serialVersionUID = -8897401023523395298L;
    public String nationality;
    public String personType;
    public String commonName;
}

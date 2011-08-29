package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Organization")
public class Organization extends CalaisEntity {

    private static final long serialVersionUID = -6963982387933902859L;
    public String organizationtype;
    public String nationality;
}

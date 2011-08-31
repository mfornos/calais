package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Company")
public class Company extends CalaisEntity {
    private static final long serialVersionUID = 2363096335677642610L;

    public String nationality;
}

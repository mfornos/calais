package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Facility")
public class Facility extends CalaisEntity {

    private static final long serialVersionUID = -3718092803970855836L;

}

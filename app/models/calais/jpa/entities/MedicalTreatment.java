package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MedicalTreatment")
public class MedicalTreatment extends CalaisEntity {

    private static final long serialVersionUID = 8748958474070969409L;

}

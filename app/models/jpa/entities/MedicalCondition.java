package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MedicalCondition")
public class MedicalCondition extends CalaisEntity {

    private static final long serialVersionUID = -6894908114536884385L;

}

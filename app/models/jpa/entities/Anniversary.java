package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Anniversary")
public class Anniversary extends CalaisEntity {
    private static final long serialVersionUID = -4277186560503019527L;

}

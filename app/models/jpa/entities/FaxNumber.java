package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FaxNumber")
public class FaxNumber extends CalaisEntity {

    private static final long serialVersionUID = 8428774118092720641L;

}

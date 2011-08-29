package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EmailAddress")
public class EmailAddress extends CalaisEntity {

    private static final long serialVersionUID = -2580443192159333057L;
}

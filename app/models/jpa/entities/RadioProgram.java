package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RadioProgram")
public class RadioProgram extends CalaisEntity {

    private static final long serialVersionUID = 4875882529637434683L;

}

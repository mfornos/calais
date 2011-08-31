package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RadioStation")
public class RadioStation extends CalaisEntity {

    private static final long serialVersionUID = 6867893840550063976L;

}

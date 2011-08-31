package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
public class Movie extends CalaisEntity {

    private static final long serialVersionUID = -3238337364657058236L;

}

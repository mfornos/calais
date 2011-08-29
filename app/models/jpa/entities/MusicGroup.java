package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MusicGroup")
public class MusicGroup extends CalaisEntity {

    private static final long serialVersionUID = -8462012848237739972L;

}

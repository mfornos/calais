package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MusicAlbum")
public class MusicAlbum extends CalaisEntity {

    private static final long serialVersionUID = 4769015054848559964L;

}

package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Technology")
public class Technology extends CalaisEntity {

    private static final long serialVersionUID = 4263914556763720666L;

}

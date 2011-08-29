package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MarketIndex")
public class MarketIndex extends CalaisEntity {

    private static final long serialVersionUID = 5290246514618957238L;

}

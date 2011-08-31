package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Product")
public class Product extends CalaisEntity {

    private static final long serialVersionUID = -6341233979710125989L;
    public String productType;

}

package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Company;
import models.calais.jpa.entities.Product;

@Entity
@DiscriminatorValue("CompanyProduct")
public class CompanyProduct extends Fact {
    private static final long serialVersionUID = 4795363430202892855L;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;

    @OneToOne(cascade = CascadeType.ALL)
    public Product product;

    public String productType;
}

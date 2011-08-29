package models.jpa.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Company;
import models.jpa.entities.Product;

@Entity
@DiscriminatorValue("CompanyProduct")
public class CompanyProduct extends Fact {
    private static final long serialVersionUID = 4795363430202892855L;

    @OneToOne
    public Company company;
    @OneToOne
    public Product product;
    public String productType;
}

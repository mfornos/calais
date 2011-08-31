package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Company;
import models.jpa.entities.Product;

@Entity
@DiscriminatorValue("ProductRelease")
public class ProductRelease extends Fact {
    private static final long serialVersionUID = 615120370122192364L;
    @OneToOne(cascade = CascadeType.ALL)
    public Company company;
    @OneToOne(cascade = CascadeType.ALL)
    public Product product;
    public String productType;
    public String status;
    public String date;
    public String dateString;
}

package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Company;
import models.calais.jpa.entities.Organization;
import models.calais.jpa.entities.Product;

@Entity
@DiscriminatorValue("ProductIssues")
public class ProductIssues extends Fact {
    private static final long serialVersionUID = 5357387852539970940L;
    
    @OneToOne(cascade=CascadeType.ALL)
    public Product product;
    
    @OneToOne(cascade=CascadeType.ALL)
    public Company company;
    
    @OneToOne(cascade=CascadeType.ALL)
    public Organization organization_Issuer;
    
    public String status;
    public String date;
    public String dateString;
}

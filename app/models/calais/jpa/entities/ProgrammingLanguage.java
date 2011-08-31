package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ProgrammingLanguage")
public class ProgrammingLanguage extends CalaisEntity {

    private static final long serialVersionUID = 1881155870259450196L;

}

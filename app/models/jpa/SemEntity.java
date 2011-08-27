package models.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class SemEntity extends DetectionBase {

    private static final long serialVersionUID = 7187361170356880417L;

    @OneToMany(mappedBy = "entity")
    public Set<SemEntityDetection> entities = new HashSet<SemEntityDetection>();

    public String type;
}

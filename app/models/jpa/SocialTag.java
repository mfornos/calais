package models.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class SocialTag extends DetectionBase {
    private static final long serialVersionUID = 1404081250119174010L;

    @OneToMany(mappedBy = "socialTag")
    public Set<SocialTagDetection> socialTags = new HashSet<SocialTagDetection>();
}

package models.calais.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

@Entity
@Table(indexes = { @Index(name = "calais_idx_tag_name", columnNames = { "name" }) }, appliesTo = "calais_SocialTag")
public class SocialTag extends DetectionBase {
    private static final long serialVersionUID = 1404081250119174010L;

    @OneToMany(mappedBy = "socialTag")
    public Set<SocialTagDetection> socialTags = new HashSet<SocialTagDetection>();

    public static SocialTag findByName(String name) {
        return find("from SocialTag where name = ?", name).first();
    }
}

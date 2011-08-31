package models.calais.jpa;

import javax.persistence.Entity;
import javax.persistence.Lob;

import play.db.jpa.Model;

@Entity
public class Detection extends Model {
    private static final long serialVersionUID = 799269563695030819L;

    public int offset;

    public int length;

    public String exact;

    @Lob
    public String prefix;

    @Lob
    public String suffix;

    @Lob
    public String detection;
}

package models.jpa;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Detection extends Model {
    private static final long serialVersionUID = 799269563695030819L;

    public int offset;

    public int length;

    public String exact;
}
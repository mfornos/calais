package models.jpa;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

@MappedSuperclass
public class DetectionBase extends GenericModel {

    private static final long serialVersionUID = -1584911030733876629L;

    @Id
    public String id;

    @Required
    public String name;

    @Override
    public Object _key() {
        return id;
    }

}

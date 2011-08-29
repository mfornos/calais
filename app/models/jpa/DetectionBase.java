package models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

@MappedSuperclass
public class DetectionBase extends GenericModel {

    private static final long serialVersionUID = -1584911030733876629L;

    @Id
    public String id;

    @Required
    public String name;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Detection> instances = new ArrayList<Detection>();

    @Override
    public Object _key() {
        return id;
    }

}

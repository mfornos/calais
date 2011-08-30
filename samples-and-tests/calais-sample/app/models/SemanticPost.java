package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import models.jpa.CalaisModel;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.modules.calais.Semantic;

@Entity
public class SemanticPost extends CalaisModel {

    @Required
    @MaxSize(255)
    @Semantic
    public String title;

    @Lob
    @Required
    @MaxSize(10000)
    @Semantic
    public String content;

    public Date created;

    public Date updated;

    @Override
    public String toString() {
        return title;
    }

    @PrePersist
    void onPrePersist() {
        created = new Date();
        updated = created;
    }

    @PreUpdate
    void onPreUpdate() {
        updated = new Date();
    }
}

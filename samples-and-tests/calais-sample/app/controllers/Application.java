package controllers;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import models.SemanticPost;
import models.calais.jpa.SocialTag;
import models.calais.jpa.Topic;
import mx.bigdata.jcalais.CalaisClient;
import mx.bigdata.jcalais.CalaisResponse;

import org.apache.commons.lang.StringUtils;

import play.Logger;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.db.jpa.JPABase;
import play.exceptions.UnexpectedException;
import play.mvc.Controller;

public class Application extends Controller {

    @Inject
    static CalaisClient client;

    public static void index() {
        List<SemanticPost> posts = SemanticPost.find("order by updated desc").fetch();
        render(posts);
    }

    public static void analyze(String text) {
        if (StringUtils.isNotBlank(text)) {
            try {
                CalaisResponse cresp = client.analyze(text);
                renderArgs.put("entities", cresp.getEntities());
                renderArgs.put("facts", cresp.getRelations());
                renderArgs.put("payload", cresp.getPayload());
            } catch (IOException e) {
                throw new UnexpectedException(e);
            }
        }
        render();
    }

    public static void delete(Long id) {
        SemanticPost post = SemanticPost.findById(id);
        notFoundIfNull(post);
        try {
            post.delete();
            flash.success("Post deleted");
        } catch (Exception e) {
            flash.error("Error deleting post: %s", e.getMessage());
        }
        index();
    }

    public static void post() {
        render();
    }

    public static void save(Long id, @Required @MaxSize(255) String title, @Required @MaxSize(10000) String content) {
        SemanticPost post;
        if (validation.hasErrors()) {
            flash.error("Please correct these errors !");
            post = new SemanticPost();
            post.title = title;
            post.content = content;
            render("@Application.post", post);
        }
        
        if (id == null) {
            post = new SemanticPost();
            post.title = title;
            post.content = content;
            post.validateAndCreate();
        } else {
            post = SemanticPost.findById(id);
            notFoundIfNull(post);
            post.title = title;
            post.content = content;
            post.validateAndSave();
        }

        post.analyze().save();
        index();
    }

    public static void edit(Long id) {
        SemanticPost post = SemanticPost.findById(id);
        notFoundIfNull(post);
        render("@Application.post", post);
    }

    public static void showTag(String name) {
        SocialTag tag = SocialTag.findByName(name);
        notFoundIfNull(tag);
        render(tag);
    }

    public static void showTopic(String name) {
        Topic topic = Topic.findByName(name);
        notFoundIfNull(topic);
        render(topic);
    }

}
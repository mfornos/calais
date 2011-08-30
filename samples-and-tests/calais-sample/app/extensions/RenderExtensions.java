package extensions;

import java.lang.reflect.Field;
import java.util.List;

import models.jpa.entities.CalaisEntity;
import models.jpa.facts.Fact;
import play.exceptions.UnexpectedException;
import play.templates.JavaExtensions;

public class RenderExtensions extends JavaExtensions {

    public static String renderFact(Fact fact) {
        StringBuilder sb = new StringBuilder();
        sb.append("<strong style=\"font-size: 120%;\">");
        sb.append(fact.getName());
        sb.append("</strong>");
        for (Field f : fact.getClass().getFields()) {
            try {
                Object object = f.get(fact);
                if (object != null) {
                    if (CalaisEntity.class.isAssignableFrom(f.getType())) {
                        appendName(sb, f);
                        sb.append(((CalaisEntity) object).name);
                    } else if (String.class.isAssignableFrom(f.getType())) {
                        appendName(sb, f);
                        sb.append(object);
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new UnexpectedException(e);
            } catch (IllegalAccessException e) {
                throw new UnexpectedException(e);
            }
        }
        return sb.toString();
    }

    public static void appendName(StringBuilder sb, Field f) {
        sb.append("<br/><strong>");
        sb.append(f.getName());
        sb.append("</strong>: ");
    }

    public static String toId(String uri) {
        return uri.replaceAll("\\.", "_").replaceAll(":", "-").replaceAll("/", "-");
    }
    
    public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        java.util.Collections.sort(list);
        return list;
    }

}

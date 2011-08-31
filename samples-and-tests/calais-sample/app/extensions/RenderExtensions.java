package extensions;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

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
                    } else if (Collection.class.isAssignableFrom(f.getType())) {
                        Type genericType = f.getGenericType();
                        if (genericType instanceof ParameterizedType) {
                            ParameterizedType pt = (ParameterizedType) genericType;
                            Type[] actualTypeArguments = pt.getActualTypeArguments();
                            if (CalaisEntity.class.isAssignableFrom((Class<?>) actualTypeArguments[0])) {
                                appendName(sb, f);
                                sb.append("<ol>");
                                for (Object o : (Collection) object) {
                                    sb.append("<li>");
                                    sb.append(((CalaisEntity) o).name);
                                    sb.append("</li>");
                                }
                                sb.append("</ol>");
                            }
                        }
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
        sb.append("<br /><strong>");
        sb.append(f.getName());
        sb.append("</strong>: ");
    }

    public static String toId(String uri) {
        return uri.replaceAll("\\.", "_").replaceAll(":", "-").replaceAll("/", "-");
    }

}

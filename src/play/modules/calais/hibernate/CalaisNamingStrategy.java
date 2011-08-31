package play.modules.calais.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.util.StringHelper;

import play.Play;

// XXX see https://hibernate.onjira.com/browse/HHH-6077
public class CalaisNamingStrategy extends ImprovedNamingStrategy {
    private static final long serialVersionUID = -6244630957876718576L;

    private static final String PREFIX = "calais_";

    private List<String> calaisNames;

    public String classToTableName(String className) {
        String name = StringHelper.unqualify(className);
        return (isCalaisClassName(className)) ? PREFIX + name : name;
    }

    @SuppressWarnings("rawtypes")
    private boolean isCalaisClassName(String className) {
        if (calaisNames == null) {
            calaisNames = new ArrayList<String>();
            List<Class> classes = Play.classloader.getAnnotatedClasses(Entity.class);
            for (Class<?> clazz : classes) {
                String name = clazz.getName();
                if (name.startsWith("models.calais")) {
                    calaisNames.add(StringHelper.unqualify(name));
                }
            }
        }
        return calaisNames.contains(className);
    }

}

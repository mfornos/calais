package play.modules.calais;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Entity;

import mx.bigdata.jcalais.CalaisClient;
import mx.bigdata.jcalais.rest.CalaisRestClient;

import org.apache.commons.lang.StringUtils;

import play.Play;
import play.PlayPlugin;
import play.exceptions.UnexpectedException;
import play.inject.Injector;

public class CalaisPlugin extends PlayPlugin {
    private static ThreadLocal<CalaisClientSource> clientSource = new ThreadLocal<CalaisClientSource>();

    private static String calaisAPIKey;

    private static Map<Class<?>, List<Field>> calaisFieldCache = new HashMap<Class<?>, List<Field>>();

    public static CalaisClient getCalaisClient() {
        return clientSource.get().getBeanOfType(CalaisClient.class);
    }

    public static List<Field> getFields(Class<?> clazz) {
        return calaisFieldCache.get(clazz);
    }

    @Override
    public void beforeInvocation() {
        CalaisClientSource source = clientSource.get();
        if (source == null) {
            CalaisClient client = new CalaisRestClient(calaisAPIKey);
            source = new CalaisClientSource(client);
            clientSource.set(source);
        }
        Injector.inject(source);
    }

    @Override
    public void onApplicationStart() {
        Properties p = Play.configuration;
        calaisAPIKey = p.getProperty("calais.api.key");
        if (StringUtils.isBlank(calaisAPIKey)) {
            throw new UnexpectedException("Please, configure calais.api.key in application.conf");
        }

        loadCalaisClasses();
    }

    private void cacheField(Class<?> clazz, Field f) {
        List<Field> fields = calaisFieldCache.get(clazz);
        if (fields == null) {
            fields = new ArrayList<Field>();
        }
        fields.add(f);
        calaisFieldCache.put(clazz, fields);
    }

    private void loadCalaisClasses() {
        @SuppressWarnings("rawtypes")
        List<Class> classes = Play.classloader.getAnnotatedClasses(Entity.class);
        if (classes != null) {
            for (Class<?> clazz : classes) {
                for (Field f : clazz.getFields()) {
                    if (f.isAnnotationPresent(Semantic.class)) {
                        cacheField(clazz, f);
                    }
                }
            }
        }
    }
}

package play.modules.calais;

import mx.bigdata.jcalais.CalaisClient;
import play.inject.BeanSource;

public class CalaisClientSource implements BeanSource {

    private final CalaisClient client;

    public CalaisClientSource(CalaisClient client) {
        this.client = client;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBeanOfType(Class<T> type) {
        return (T) ((CalaisClient.class.isAssignableFrom(type)) ? client : null);
    }

}

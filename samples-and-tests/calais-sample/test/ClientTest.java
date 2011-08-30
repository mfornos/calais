import org.junit.*;

import java.io.IOException;
import java.util.*;

import javax.inject.Inject;

import play.Logger;
import play.modules.calais.CalaisPlugin;
import play.test.*;
import models.jpa.*;
import mx.bigdata.jcalais.CalaisClient;
import mx.bigdata.jcalais.CalaisObject;
import mx.bigdata.jcalais.CalaisResponse;

public class ClientTest extends UnitTest {

    @Test
    public void testCalaisConnection() {
        CalaisClient client = CalaisPlugin.getCalaisClient();
        assertNotNull(client);
        try {
            CalaisResponse result = client.analyze("iphone");
            Iterable<CalaisObject> entities = result.getEntities();
            assertNotNull(entities);
            for (CalaisObject entity : entities) {
                String field = entity.getField("producttype");
                assertEquals("electronics", field.toLowerCase());
                break;
            }
            Iterable<CalaisObject> socialTags = result.getSocialTags();
            assertNotNull(socialTags);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

}

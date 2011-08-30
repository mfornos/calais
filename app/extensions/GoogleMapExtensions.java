package extensions;

import models.jpa.Resolution;
import play.templates.JavaExtensions;

public class GoogleMapExtensions extends JavaExtensions {
    public static String staticMap(Resolution resolution) {
        int zoom = (resolution.containedbycountry == null) ? 2 : 10;
        return staticMap(resolution, zoom, "250x250");
    }

    public static String staticMap(Resolution resolution, int zoom, String size) {
        StringBuilder sb = new StringBuilder("http://maps.google.com/maps/api/staticmap?center=");
        sb.append(resolution.latitude);
        sb.append(',');
        sb.append(resolution.longitude);
        sb.append("&zoom=");
        sb.append(zoom);
        sb.append("&size=");
        sb.append(size);
        sb.append("&markers=color:blue|label:L|");
        sb.append(resolution.latitude);
        sb.append(',');
        sb.append(resolution.longitude);
        sb.append("&sensor=false");
        return sb.toString();
    }
}

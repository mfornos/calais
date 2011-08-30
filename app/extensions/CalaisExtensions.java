package extensions;

import java.util.List;

import models.jpa.Resolution;
import models.jpa.SocialTagDetection;
import play.templates.JavaExtensions;

public class CalaisExtensions extends JavaExtensions {
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

    public static String tagCloud(List<SocialTagDetection> socialTags, String link) {
        StringBuilder sb = new StringBuilder();
        int max = 1; /* score > 0.8 */
        // int min = 2; /* score is between 0.6 and 0.8*/

        for (SocialTagDetection d : socialTags) {
            if (d.importance == max) {
                appendTag("large-tag", link, sb, d);
            } else {
                appendTag("medium-tag", link, sb, d);
            }
        }
        return sb.toString();
    }

    private static void appendTag(String style, String link, StringBuilder sb, SocialTagDetection d) {
        sb.append("<a href=\"");
        sb.append(link);
        sb.append('/');
        sb.append(d.socialTag.name);
        sb.append("\" class=\"social-tag ");
        sb.append(style);
        sb.append("\">");
        sb.append(d.socialTag.name);
        sb.append("</a>");
    }

    public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        java.util.Collections.sort(list);
        return list;
    }
}

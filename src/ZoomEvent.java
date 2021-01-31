import java.io.Serializable;
import java.net.URI;

public class ZoomEvent implements Serializable {
    private String name;
    private URI uri;
    private String date;
    private String time;
    private boolean repeating;
    private int[] repeatingDays;

    public ZoomEvent(String name, URI URL, String date, String time) {
        this.name = name;
        this.uri = URL;
        this.date = date;
        this.time = time;
    }

    public ZoomEvent(URI URL, boolean repeating, int[] repeatingDays) {
        this.uri = URL;
        this.repeating = repeating;
        this.repeatingDays = repeatingDays;
    }

    public void openLink() {
        try {
            java.awt.Desktop.getDesktop().browse(this.uri);
            System.out.println("Link opened in browser.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public URI getUri() {
        return uri;
    }

    public String toString() {
        if (!repeating) {
            return this.date + " " + this.time + " " + this.name;
        }
        return null;
    }
}

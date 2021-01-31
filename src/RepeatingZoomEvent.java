import java.io.Serializable;
import java.net.URI;

public class RepeatingZoomEvent implements Serializable {

    private String name;
    private URI uri;
    private String time;
    private boolean repeating;
    private int[] repeatingDays;

    public RepeatingZoomEvent(String name, URI URL, String time, boolean repeating, int[] repeatingDays) {
        this.name = name;
        this.uri = URL;
        this.time = time;
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


    public String getTime() {
        return time;
    }

    public URI getUri() {
        return uri;
    }

    public boolean isRepeating() {
        return this.repeating;
    }

    public String toString() {
        if (!repeating) {
            return this.time + " " + this.name;
        }
        return null;
    }
}

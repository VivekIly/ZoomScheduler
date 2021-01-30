import java.net.URI;

public class ZoomEvent {
    private URI uri;
    private String date;
    private boolean repeating;
    private int[] repeatingDays;

    public ZoomEvent(URI URL, String time) {
        this.uri = URL;
        this.date = time;
    }

    public ZoomEvent(URI URL, boolean repeating, int[] repeatingDays) {
        this.uri = URL;
        this.repeating = repeating;
        this.repeatingDays = repeatingDays;
    }

    public void openLink() {
        try {
            java.awt.Desktop.getDesktop().browse(this.uri);
            System.out.println("Link opened in browser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "";
    }
}

import java.io.Serializable;
import java.net.URI;

public class RepeatingZoomEvent implements Serializable {

    private String name;
    private URI uri;
    private String time;
    private int[] repeatingDays;

    public RepeatingZoomEvent(String name, URI URL, String time, int[] repeatingDays) {
        this.name = name;
        this.uri = URL;
        this.time = time;
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

    public int[] getRepeatingDays() {
        return repeatingDays;
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

    public String toString() {

        String returnString = "";

        for (int i : this.repeatingDays) {
            if (i == 0) {
                returnString += "M";
            }
            if (i == 1) {
                returnString += "T";
            }
            if (i == 2) {
                returnString += "W";
            }
            if (i == 3) {
                returnString += "Th";
            }
            if (i == 4) {
                returnString += "F";
            }
            if (i == 5) {
                returnString += "Sa";
            }
            if (i == 6) {
                returnString += "Su";
            }
        }

        return returnString;
    }
}

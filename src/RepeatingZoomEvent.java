import java.io.*;
import java.net.URI;

public class RepeatingZoomEvent implements Serializable {

    private String name;
    private URI uri;
    private String time;
    private int[] repeatingDays;

    private String deleteTime;

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

    private String getDaysInInts() {
        String returnString = "";

        for (int i : this.repeatingDays) {
            returnString += i;
        }

        return returnString;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public boolean serialize() {
        String name = null;
        for (int i = 99; i >= 1; i--) {
            if (!Tools.fileExists(this.getDaysInInts() + " " + i + ".rze", new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"))) {
                name = this.getDaysInInts() + " " + i + ".rze";
            }
        }
        try {
            String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
            FileOutputStream fileOut = new FileOutputStream(outName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

        return Tools.fileExists(name, new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"));
    }

    public String toString() {

        String returnString = "";

        for (int i = 0; i < this.repeatingDays.length; i++) {
            if (i == 0 && this.repeatingDays[i] == 1) {
                returnString += "M";
            }
            if (i == 1 && this.repeatingDays[i] == 1) {
                returnString += "T";
            }
            if (i == 2 && this.repeatingDays[i] == 1) {
                returnString += "W";
            }
            if (i == 3 && this.repeatingDays[i] == 1) {
                returnString += "Th";
            }
            if (i == 4 && this.repeatingDays[i] == 1) {
                returnString += "F";
            }
            if (i == 5 && this.repeatingDays[i] == 1) {
                returnString += "Sa";
            }
            if (i == 6 && this.repeatingDays[i] == 1) {
                returnString += "Su";
            }
        }

        returnString += " " + this.time + " " + this.name;

        return returnString;
    }
}

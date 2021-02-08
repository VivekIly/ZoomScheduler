import java.io.*;
import java.net.URI;

public class ZoomEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 2102570816361121772L;

    private String name;
    private URI uri;
    private String date;
    private String time;
    private boolean repeating;
    private int[] repeatingDays;

    private String deleteTime;

    public ZoomEvent(String name, URI URL, String date, String time) {
        this.name = name;
        this.uri = URL;
        this.date = date;
        this.time = time;
        if (serialize()) {
            System.out.println(this.toString() + ".ze saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
        }
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

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public boolean isRepeating() {
        return this.repeating;
    }

    public boolean serialize() {
        String name = null;
        for (int i = 1; i < 100; i++) {
            if (!Tools.fileExists(this.date + " " + i + ".ze", new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"))) {
                name = Tools.getIntFromString(this.date) + " " + i + ".ze";
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
        return this.date + " " + this.time + " " + this.name;
    }
}

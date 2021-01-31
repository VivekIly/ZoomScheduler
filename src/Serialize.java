import java.io.*;
import java.util.ArrayList;

public class Serialize implements Serializable {

    private ArrayList<ZoomEvent> arrlist;
    private ArrayList<RepeatingZoomEvent> arrlistRepeating;
    private final String defColor;

    public Serialize(ArrayList<ZoomEvent> arrayList) {
        this.arrlist = arrayList;
        this.defColor = Main.defaultColorTheme;
    }

    public Serialize(ArrayList<RepeatingZoomEvent> repeatingList, boolean repeating) {
        this.arrlistRepeating = repeatingList;
        this.defColor = Main.defaultColorTheme;
    }

    public static void initSerialize() {

        File dir = new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER");
        boolean bool = dir.mkdir();
        if (bool) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Directory could not be created. It may already exist.");
        }
        String name = "events.arl";
        String repeating = "repeating_events.arl";
        String settings = "theme.thm";
        String outSettings = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + settings;
        String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
        String repeatingName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + repeating;

        if (!Tools.fileExists(name, new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"))) {
            try {
                FileOutputStream fileOut = new FileOutputStream(outName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                ArrayList<ZoomEvent> events = new ArrayList<>();
                out.writeObject(events);
                out.close();
                fileOut.close();
                System.out.println(name + " saved in " + outName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Tools.println("events.arl already exists.");
        }

        if (!Tools.fileExists(repeating, new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"))) {
            try {
                FileOutputStream fileOut = new FileOutputStream(repeatingName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                ArrayList<ZoomEvent> repeating_events = new ArrayList<>();
                out.writeObject(repeating_events);
                out.close();
                fileOut.close();
                System.out.println(repeating + " saved in " + repeatingName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Tools.println("repeating_events.arl already exists.");
        }

        if (!Tools.fileExists(settings, new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"))) {
            try {
                FileOutputStream fileOut = new FileOutputStream(outSettings);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                String setgs = "l";
                out.writeObject(setgs);
                out.close();
                fileOut.close();
                System.out.println(settings + " saved in " + outSettings);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Tools.println("theme.thm already exists.");
        }
    }

    public static void serialize(ArrayList<ZoomEvent> arrayList, boolean isRepeating) {
        if (!isRepeating) {
            try {
                String name = "events.arl";
                String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
                FileOutputStream fileOut = new FileOutputStream(outName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(arrayList);
                out.close();
                fileOut.close();
                System.out.println(name + " saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
            } catch (IOException i) {
                i.printStackTrace();
            }
        } else {
            try {
                String name = "events_repeating.arl";
                String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
                FileOutputStream fileOut = new FileOutputStream(outName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(arrayList);
                out.close();
                fileOut.close();
                System.out.println(name + " saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public static void serializeRepeating(ArrayList<RepeatingZoomEvent> arrayList, boolean isRepeating) {
        if (!isRepeating) {
            try {
                String name = "events.arl";
                String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
                FileOutputStream fileOut = new FileOutputStream(outName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(arrayList);
                out.close();
                fileOut.close();
                System.out.println(name + " saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
            } catch (IOException i) {
                i.printStackTrace();
            }
        } else {
            try {
                String name = "events_repeating.arl";
                String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
                FileOutputStream fileOut = new FileOutputStream(outName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(arrayList);
                out.close();
                fileOut.close();
                System.out.println(name + " saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public static void serializeSettings() {
        try {
            String name = "theme.thm";
            String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
            FileOutputStream fileOut = new FileOutputStream(outName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Main.defaultColorTheme);
            out.close();
            fileOut.close();
            System.out.println(name + " saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<ZoomEvent> fetch() {
        ArrayList<ZoomEvent> tempAL = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + "events.arl");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempAL = (ArrayList<ZoomEvent>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempAL;
    }

    public static ArrayList<RepeatingZoomEvent> fetchRepeating() {
        ArrayList<RepeatingZoomEvent> tempAL = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + "repeating_events.arl");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempAL = (ArrayList<RepeatingZoomEvent>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempAL;
    }

    public static String fetchColor() {
        String defaultColor = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + "theme.thm");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            defaultColor = (String) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return defaultColor;
    }
}


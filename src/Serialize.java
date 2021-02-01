import java.io.*;

public class Serialize implements Serializable {

    public static void initSerialize() {

        File dir = new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER");
        boolean bool = dir.mkdir();
        if (bool) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Directory could not be created. It may already exist.");
        }

        String settings = "theme.thm";
        String outSettings = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + settings;


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

    public static ZoomEvent fetch(String name) {
        ZoomEvent temp = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (ZoomEvent) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static RepeatingZoomEvent fetchRepeating(String name) {
        RepeatingZoomEvent temp = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (RepeatingZoomEvent) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
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


import java.io.*;

public class Serialize implements Serializable {

    private final BinarySearchTree obj;
    private final String settings;

    public Serialize(BinarySearchTree object, String defaultColor) {
        this.obj = object;
        this.settings = Main.defaultColorTheme;
    }

    public static void initSerialize() {

        File dir = new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER");
        boolean bool = dir.mkdir();
        if (bool) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Directory could not be created. It may already exist.");
        }
        String name = "events.bst";
        String repeating = "repeating_events.bst";
        String settings = "settings.stg";
        String outColor = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + settings;
        String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
        String repeatingName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + repeating;

        if (!Tools.fileExists(name, new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"))) {
            try {
                FileOutputStream fileOut = new FileOutputStream(outName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                BinarySearchTree events = new BinarySearchTree("events");
                out.writeObject(events);
                out.close();
                fileOut.close();
                System.out.println("Saved in " + outName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Tools.println("events.bst already exists.");
        }

        if (!Tools.fileExists(repeating, new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"))) {
            try {
                FileOutputStream fileOut = new FileOutputStream(repeatingName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                BinarySearchTree repeating_events = new BinarySearchTree("repeating events");
                out.writeObject(repeating_events);
                out.close();
                fileOut.close();
                System.out.println("Saved in " + repeatingName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Tools.println("repeating_events.bst already exists.");
        }

        if (!Tools.fileExists(settings, new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER"))) {
            try {
                FileOutputStream fileOut = new FileOutputStream(outColor);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                String defaultColor = "l";
                out.writeObject(defaultColor);
                out.close();
                fileOut.close();
                System.out.println("Saved in " + outColor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void serialize(BinarySearchTree bst, boolean isRepeating) {
        if (!isRepeating) {
            try {
                String name = "events.bst";
                String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
                FileOutputStream fileOut = new FileOutputStream(outName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(this.obj);
                out.close();
                fileOut.close();
                System.out.println(" Saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
            } catch (IOException i) {
                i.printStackTrace();
            }
        } else {
            try {
                String name = "events_repeating.bst";
                String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
                FileOutputStream fileOut = new FileOutputStream(outName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(this.obj);
                out.close();
                fileOut.close();
                System.out.println(" Saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public void serializeSettings() {
        try {
            String name = "settings.stg";
            String outName = System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name;
            FileOutputStream fileOut = new FileOutputStream(outName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.settings);
            out.close();
            fileOut.close();
            System.out.println(" Saved in " + System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + name);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static BinarySearchTree fetch() {
        BinarySearchTree tempBST = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + "events.bst");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempBST = (BinarySearchTree) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempBST;
    }

    public static BinarySearchTree fetchRepeating() {
        BinarySearchTree tempBST = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + "repeating_events.bst");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempBST = (BinarySearchTree) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempBST;
    }

    public static String fetchColor() {
        String defaultColor = null;
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER" + File.separator + "settings.stg");
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


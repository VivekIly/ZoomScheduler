import java.io.*;

public class Serialize implements Serializable {

    private final Object obj;

    public Serialize(Object object) {
        this.obj = object;
    }

    public static void initSerialize() {

        File dir = new File(System.getProperty("user.home") + File.separator +"ZoomScheduler - DO NOT TAMPER");
        boolean bool = dir.mkdir();
        if (bool) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Directory could not be created. It may already exist.");
        }
        String name = "events.bst";
        String outName = System.getProperty("user.home") + File.separator +"ZoomScheduler - DO NOT TAMPER" + File.separator + name;

        if (!Tools.fileExists(name, new File(System.getProperty("user.home") + File.separator +"ZoomScheduler - DO NOT TAMPER"))) {
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
    }

    public void serialize() {
        try {
            String name = "events.bst";
            String outName = System.getProperty("user.home") + File.separator +"ZoomScheduler - DO NOT TAMPER" + File.separator + name;
            FileOutputStream fileOut = new FileOutputStream(outName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.obj);
            out.close();
            fileOut.close();
            System.out.println(" Saved in " + System.getProperty("user.home") + File.separator +"ZoomScheduler - DO NOT TAMPER" + File.separator + name);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static BinarySearchTree fetch() {
        BinarySearchTree tempBST = null;
        try{
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + File.separator +"ZoomScheduler - DO NOT TAMPER" + File.separator + "events.bst");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempBST = (BinarySearchTree) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempBST;
    }
}

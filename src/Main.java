

public class Main {

    private static boolean rerunFlag = false;
    public static String colorTheme = "l";
    public static String defaultColorTheme = "l";

    public static void main(String[] args) throws InterruptedException {

        Serialize.initSerialize();

        Main.defaultColorTheme = Serialize.fetchColor();
        Main.colorTheme = defaultColorTheme;

        MainWindow mainWindow = new MainWindow();

        BinarySearchTree bst = Serialize.fetch();

        ZoomEvent[] array = null;
        if (bst.getArray() != null) {
            Tools.println(bst.getArray());
            array = new ZoomEvent[bst.getArray().length];

            int index = 0;
            for (Object o : bst.getArray()) {
                array[index] = (ZoomEvent) o;
                index++;
            }
        }

        for (int a = 0; a < 1; a--) {
            if (rerunFlag){
                setRerunFlag(false);
                Main.rerun();
            }
            String time = Tools.getCurrentTime();
            System.out.print(time);
            Thread.sleep(1000);
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");

            if (array != null) {
                for (int i = 0; i < array.length; i++) {
                    if (array[i] != null && array[i].getTime().equals(time.substring(11, 16))) {
                        array[i].openLink();
                        ZoomEvent[] temp = null;
                        int index = 0;
                        for (int j = 0; j < array.length; j++) {
                            temp = new ZoomEvent[array.length - 1];
                            if (array[j] != array[i]) {
                                temp[index] = array[j];
                                index++;
                            }
                        }
                        array = temp;
                    }
                }
            }
        }
    }

    private void updateToday() {

    }

    public static void rerun() {
        BinarySearchTree bst = Serialize.fetch();

        Main.defaultColorTheme = Serialize.fetchColor();
        Main.colorTheme = defaultColorTheme;

        ZoomEvent[] array = null;
        if (bst.getArray() != null) {
            Tools.println(bst.getArray());
            array = new ZoomEvent[bst.getArray().length];

            int index = 0;
            for (Object o : bst.getArray()) {
                array[index] = (ZoomEvent) o;
                index++;
            }
        }

        for (int a = 0; a < 1; a--) {
            if (rerunFlag){
                setRerunFlag(false);
                Main.rerun();
            }
            String time = Tools.getCurrentTime();
            System.out.print(time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");

            if (array != null) {
                for (int i = 0; i < array.length; i++) {
                    if (array[i] != null && array[i].getDate().equals(time.substring(0, 10)) && array[i].getTime().equals(time.substring(11, 16))) {
                        array[i].openLink();
                        ZoomEvent[] temp = null;
                        int index = 0;
                        for (int j = 0; j < array.length; j++) {
                            temp = new ZoomEvent[array.length - 1];
                            if (array[j] != array[i]) {
                                temp[index] = array[j];
                                index++;
                            }
                        }
                        array = temp;
                    }
                }
            }
        }
    }

    public static void setRerunFlag(boolean bool) {
        Main.rerunFlag = bool;
    }
}

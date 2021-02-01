import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static boolean rerunFlag = false;
    public static String colorTheme = "l";
    public static String defaultColorTheme = "l";

    public static void main(String[] args) throws InterruptedException {

        Serialize.initSerialize();

        Main.defaultColorTheme = Serialize.fetchColor();
        Main.colorTheme = defaultColorTheme;

        MainWindow mainWindow = new MainWindow();

        ArrayList<ZoomEvent> arl = Serialize.fetch();
        ArrayList<RepeatingZoomEvent> arlR = Serialize.fetchRepeating();

        ZoomEvent[] array = null;
        if (arl != null) {
            Tools.println("One-time events: " + arl);
            array = new ZoomEvent[arl.size()];

            int index = 0;
            for (Object o : arl) {
                array[index] = (ZoomEvent) o;
                index++;
            }
        }

        RepeatingZoomEvent[] arrayRepeating = null;
        if (arlR != null) {
            Tools.println("Repeating events: " + arlR);
            arrayRepeating = new RepeatingZoomEvent[arlR.size()];

            int index = 0;
            for (Object o : arlR) {
                arrayRepeating[index] = (RepeatingZoomEvent) o;
                index++;
            }
        }

        for (int a = 0; a < 1; a--) {
            if (rerunFlag){
                setRerunFlag(false);
                Main.rerun();
            }
            String time = Tools.getCurrentTime();
            System.out.print(time + " - ");
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
        ArrayList<ZoomEvent> arl = Serialize.fetch();
        ArrayList<RepeatingZoomEvent> arlR = Serialize.fetchRepeating();

        Main.defaultColorTheme = Serialize.fetchColor();
        Main.colorTheme = defaultColorTheme;

        ZoomEvent[] array = null;
        if (arl != null) {
            Tools.println(arl);
            array = new ZoomEvent[arl.size()];

            int index = 0;
            for (Object o : arl) {
                array[index] = (ZoomEvent) o;
                index++;
            }
        }

        RepeatingZoomEvent[] arrayRepeating = null;
        if (arlR != null) {
            Tools.println(arlR);
            array = new ZoomEvent[arlR.size()];

            int index = 0;
            for (Object o : arlR) {
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

    public static void setRerunFlag(boolean bool) {Main.rerunFlag = bool;}

    public static int getDayOfWeek(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    // TODO: Implement repeating events.
    // TODO: Fix scheduled events window.
    // TODO: Display current date and time in the main window.
    // TODO: Remove past events.
    // TODO: Remove scheduled event.
    // TODO: Can only open one of each type of window.
    // TODO: Move serialization to individual Object classes.
    // TODO: Fit window to all screen sizes.
}

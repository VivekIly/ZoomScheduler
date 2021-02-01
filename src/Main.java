import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static boolean rerunFlag = false;
    public static String colorTheme = "l";
    public static String defaultColorTheme = "l";
    public static String currentTime;
    public static int today;

    public static ArrayList<File> toDeleteZE = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Main.today = getDayOfWeek(Tools.getDate());
        if (Main.today == 1) {
            Main.today = 6;
        } else {
            Main.today -= 2;
        }

        Serialize.initSerialize();

        Main.defaultColorTheme = Serialize.fetchColor();
        Main.colorTheme = defaultColorTheme;

        MainWindow mainWindow = new MainWindow();

        File dir = new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER");
        File[] zeFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".ze");
            }
        });

        File[] rzeFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".rze");
            }
        });

        if (zeFiles != null && zeFiles.length > 0) {
            Tools.print("One-time events: ");

            ArrayList<ZoomEvent> zeArray = new ArrayList<>();
            for (File f : zeFiles) {
                ZoomEvent z = Serialize.fetch(f.getName());
                zeArray.add(z);
            }

            Tools.println(zeArray);
        } else {
            Tools.println("One-time events: []");
        }

        if (rzeFiles != null && rzeFiles.length > 0) {
            Tools.print("Repeating events: ");

            ArrayList<RepeatingZoomEvent> zeArray = new ArrayList<>();
            for (File f : rzeFiles) {
                RepeatingZoomEvent z = Serialize.fetchRepeating(f.getName());
                zeArray.add(z);
            }

            Tools.println(zeArray);
        } else {
            Tools.println("Repeating events: []");
        }

        for (int a = 0; a < 1; a--) {
            Main.currentTime = Tools.getCurrentTime();

            if (Main.currentTime.substring(11, 16).equals("00:00")) {
                Main.today = getDayOfWeek(Tools.getDate());
            }

            for (File f : toDeleteZE) {
                ZoomEvent e = Serialize.fetch(f.getName());

                if (e.getDeleteTime() != null && e.getDeleteTime().equals(Main.currentTime.substring(11, 16))) {
                    if (f.delete()) Tools.println(f.getName() + " successfully deleted.");
                    else Tools.println(f.getName() + " could not be deleted.");
                }
            }

            if (rerunFlag){
                setRerunFlag(false);
                Main.rerun();
            }

            System.out.print(Main.currentTime + " - ");
            Thread.sleep(1000);
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");

            if (zeFiles != null) {
                for (File file : zeFiles) {
                    ZoomEvent event = Serialize.fetch(file.getName());

                    if (event.getTime().equals(Main.currentTime.substring(11, 16))) {
                        event.openLink();

                        if (file.delete()) {
                            Tools.println(file.getName() + " successfully deleted.");
                        } else {
                            Tools.println(file.getName() + " could not be deleted.");
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            if (rzeFiles != null) {
                for (File file : rzeFiles) {
                    RepeatingZoomEvent event = Serialize.fetchRepeating(file.getName());

                    if (event.getTime().equals(Main.currentTime.substring(11, 16))) {
                        if (event.getRepeatingDays()[Main.today] ==  1) {
                            event.openLink();
                            try {
                                Thread.sleep(1000 * 61);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateToday() {

    }

    public static void rerun() {

        Main.defaultColorTheme = Serialize.fetchColor();
        Main.colorTheme = defaultColorTheme;

        File dir = new File(System.getProperty("user.home") + File.separator + "ZoomScheduler - DO NOT TAMPER");
        File[] zeFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".ze");
            }
        });

        File[] rzeFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".rze");
            }
        });

        if (zeFiles != null && zeFiles.length > 0) {
            Tools.print("One-time events: ");

            ArrayList<ZoomEvent> zeArray = new ArrayList<>();
            for (File f : zeFiles) {
                ZoomEvent z = Serialize.fetch(f.getName());
                zeArray.add(z);
            }

            Tools.println(zeArray);
        } else {
            Tools.println("One-time events: []");
        }

        if (rzeFiles != null && rzeFiles.length > 0) {
            Tools.print("Repeating events: ");

            ArrayList<RepeatingZoomEvent> zeArray = new ArrayList<>();
            for (File f : rzeFiles) {
                RepeatingZoomEvent z = Serialize.fetchRepeating(f.getName());
                zeArray.add(z);
            }

            Tools.println(zeArray);
        } else {
            Tools.println("Repeating events: []");
        }

        for (int a = 0; a < 1; a--) {
            Main.currentTime = Tools.getCurrentTime();

            if (Main.currentTime.substring(11, 16).equals("00:00")) {
                Main.today = getDayOfWeek(Tools.getDate());
            }

            for (File f : toDeleteZE) {
                ZoomEvent e = Serialize.fetch(f.getName());

                if (e.getDeleteTime() != null && e.getDeleteTime().equals(Main.currentTime.substring(11, 16))) {
                    if (f.delete()) Tools.println(f.getName() + " successfully deleted.");
                    else Tools.println(f.getName() + " could not be deleted.");
                }
            }

            if (rerunFlag){
                setRerunFlag(false);
                Main.rerun();
            }

            System.out.print(Main.currentTime + " - ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");

            if (zeFiles != null) {
                for (File file : zeFiles) {
                    ZoomEvent event = Serialize.fetch(file.getName());

                    if (event.getTime().equals(Main.currentTime.substring(11, 16))) {
                        event.openLink();

                        if (file.delete()) {
                            Tools.println(file.getName() + " successfully deleted.");
                        } else {
                            Tools.println(file.getName() + " could not be deleted.");
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            if (rzeFiles != null) {
                for (File file : rzeFiles) {
                    RepeatingZoomEvent event = Serialize.fetchRepeating(file.getName());

                    if (event.getTime().equals(Main.currentTime.substring(11, 16))) {
                        if (event.getRepeatingDays()[Main.today] ==  1) {
                            event.openLink();
                            try {
                                Thread.sleep(1000 * 61);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public static void setRerunFlag(boolean bool) {Main.rerunFlag = bool;}

    public static int getDayOfWeek(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy/MM/dd").parse(date);
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
    // TODO: Fix NullPointerException after removing an event.
    // TODO: Can only open one of each type of window.
    // TODO: Fit window to all screen sizes.
    // TODO: Fix bug where Event viewer window doesn't detect events.
}

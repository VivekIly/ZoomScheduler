import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static boolean rerunFlag = false;
    public static boolean auxWindowOpen = false;
    public static String colorTheme = "l";
    public static String defaultColorTheme = "l";
    public static String currentTime;
    public static int today;
    private static MainWindow mainWindow;

    public static ArrayList<File> toDeleteZE = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        JFrame mainFrame = new JFrame();
        JLabel loadingLabel = new JLabel("Loading...", JLabel.CENTER);

        Main.defaultColorTheme = Serialize.fetchColor();
        Main.colorTheme = defaultColorTheme;

        if (Main.colorTheme.equals("d")) {
            int textColor = 200;
            Color darkText = new Color(textColor, textColor, textColor);
            loadingLabel.setForeground(darkText);

            int themeColor = 25;
            Color dark = new Color(54, 57, 63);
            mainFrame.getContentPane().setBackground(dark);
        }

        if (Main.colorTheme.equals("l")) {
            mainFrame.getContentPane().setBackground(Color.WHITE);
            loadingLabel.setForeground(Color.BLACK);
        }

        mainFrame.setSize(100, 50);
        mainFrame.setUndecorated(true);
        mainFrame.setShape(new RoundRectangle2D.Double(0, 0, mainFrame.getWidth(), mainFrame.getHeight(), 20, 20));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);

        mainFrame.add(loadingLabel);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        Main.today = getDayOfWeek(Tools.getDate(), "yyyy/MM/dd");
        if (Main.today == 1) {
            Main.today = 6;
        } else {
            Main.today -= 2;
        }

        Serialize.initSerialize();

        Main.currentTime = Tools.getCurrentTime();


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

        Thread.sleep(1000);
        mainWindow = new MainWindow();
        mainFrame.dispose();

        for (int a = 0; a < 1; a--) {
            Main.currentTime = Tools.getCurrentTime();
            mainWindow.updateDateTime();

            if (Main.currentTime.substring(11, 16).equals("00:00")) {
                Main.today = getDayOfWeek(Tools.getDate(), "yyyy/MM/dd");
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
                        mainWindow.setDateTime("");
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
                            mainWindow.setDateTime("");
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
            mainWindow.updateDateTime();

            if (Main.currentTime.substring(11, 16).equals("00:00")) {
                Main.today = getDayOfWeek(Tools.getDate(), "yyyy/MM/dd");
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
                        mainWindow.setDateTime("");
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
                            mainWindow.setDateTime("");
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

    public static int getDayOfWeek(String date, String datePattern) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat(datePattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    // TODO: Can only open one of each type of window.
    // TODO: Fit window to all screen sizes.
    // TODO: Add option to export and import data.
    // TODO: Detect if user is already in another Zoom meeting. If so, then don't delete, show popup that Zoom meeting could not be opened.
    // TODO: Play sound and push popup message when link opens.
    // TODO: Add ability to join meeting with meeting ID and password.
    // TODO: Event viewer window display more events.
    // TODO: Can store more events.
    // TODO: Add shortcuts.
    // TODO: Add minimize window feature.
    // TODO: Rearrange by event date.
    // TODO: Detect if offline. If so, push message saying meeting could not be offline because device is offline.

    //TODO: Create an exe installer package.

    // TODO: README: System requirements.
    // TODO: README: Linux set up.
    // TODO: README: Zoom settings.
}

/*

v1.0.1 changes:

Loading window when application initially starts up.
Improve aesthetic look and feel of dark theme.





















 */
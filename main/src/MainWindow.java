import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

public class MainWindow {

    private String date;
    private String time;

    private JLabel timeLabel;

    private JFrame mainFrame;

    public MainWindow() {
        displayWindow();
    }

    public static void main(String[] args) {
        MainWindow win = new MainWindow();
    }

    private void displayWindow() {
        mainFrame = new JFrame("Zoom Scheduler");
        //mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);

        //Set dimensions of window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        mainFrame.setSize((int) (width - 100), height - 100);
        mainFrame.setShape(new RoundRectangle2D.Double(0, 0, width - 150, height - 150, 50, 50));


        //Set layout
        mainFrame.setLayout(new GridLayout(9, 1));

        //Set labels
        JPanel headerPanel = new MotionPanel(mainFrame);
        headerPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLUE));
        headerPanel.setLayout(null);
        JLabel headerLabel = new JLabel("Zoom Scheduler");
        Dimension size = headerLabel.getPreferredSize();
        headerLabel.setBounds(mainFrame.getWidth() / 2 - 125, headerPanel.getHeight() / 20, size.width * 4, size.height * 2);
        headerLabel.setFont(headerLabel.getFont().deriveFont(32.0f));
        headerPanel.add(headerLabel);

        JLabel subheaderLabel = new JLabel("Select the action you would like to take: ", JLabel.CENTER);
        subheaderLabel.setSize((int) (width), 100);

        JLabel subtext = new JLabel("PLEASE DO NOT TAMPER WITH THE \'ZoomScheduler\' FOLDER!!", JLabel.CENTER);
        subtext.setSize((int) (width), 100);
        subtext.setFont(headerLabel.getFont().deriveFont(16.0f));
        subtext.setForeground(Color.RED);
        subtext.setBounds(0, mainFrame.getHeight() - 60, mainFrame.getWidth(), 50);

        //Create control panel for buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        //Create panel and button to switch color themes
        JPanel themePanel = new JPanel();
        themePanel.setLayout(new FlowLayout());
        JButton theme = null;
        if (Main.colorTheme.equals("l")) {
            theme = new JButton("Switch to Dark theme");
        } else if (Main.colorTheme.equals("d")) {
            theme = new JButton("Temporarily switch to Light theme");
        }

        JButton defaultTheme = null;
        if (Main.defaultColorTheme.equals("l")) {
            defaultTheme = new JButton("Switch default theme to Dark");
        } else if (Main.defaultColorTheme.equals("d")) {
            defaultTheme = new JButton("Switch default theme to Light");
        }
        themePanel.add(theme);
        themePanel.add(defaultTheme);

        //Create export/import panel
        JPanel exportPanel = new JPanel();
        JButton exportButton = new JButton("Export settings");
        JButton importButton = new JButton("Import settings");
        exportPanel.setSize(mainFrame.getWidth(), 5);
        exportPanel.add(exportButton);
        exportPanel.add(importButton);

        //Create status panel
        JLabel statusPanel = new JLabel("", JLabel.CENTER);
        statusPanel.setLayout(new FlowLayout());

        //Create exit application button
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout());
        JButton exit = new JButton("Exit Application");
        JButton close = new JButton("Hide Window");
        JButton min = new JButton("Minimize Window");
        exitPanel.add(min);
        exitPanel.add(close);
        exitPanel.add(exit);

        //Create time label to display current time.
        timeLabel = new JLabel("", JLabel.CENTER);
        timeLabel.setSize(mainFrame.getWidth(), 1);

        //Switches text  and background colors to fit the color theme
        if (Main.colorTheme.equals("d")) {
            int textColor = 200;
            Color darkText = new Color(textColor, textColor, textColor);
            headerLabel.setForeground(darkText);
            timeLabel.setForeground(darkText);
            subheaderLabel.setForeground(darkText);
            controlPanel.setForeground(darkText);
            themePanel.setForeground(darkText);
            exportPanel.setForeground(darkText);
            statusPanel.setForeground(darkText);
            exitPanel.setForeground(darkText);

            Color dark = new Color(54, 57, 63);
            headerPanel.setBackground(new Color(47, 50, 54));
            controlPanel.setBackground(dark);
            themePanel.setBackground(dark);
            exportPanel.setBackground(dark);
            statusPanel.setBackground(dark);
            exitPanel.setBackground(dark);
            mainFrame.getContentPane().setBackground(dark);
        }

        if (Main.colorTheme.equals("l")) {
            headerLabel.setForeground(Color.GRAY);
            subheaderLabel.setForeground(Color.BLACK);
            controlPanel.setForeground(Color.BLACK);
            exportPanel.setForeground(Color.BLACK);
            themePanel.setForeground(Color.BLACK);
            exitPanel.setForeground(Color.BLACK);

            controlPanel.setBackground(Color.WHITE);
            headerPanel.setBackground(new Color(230, 230, 230));
            themePanel.setBackground(Color.WHITE);
            exitPanel.setBackground(Color.WHITE);
            exportPanel.setBackground(Color.WHITE);
            mainFrame.getContentPane().setBackground(Color.WHITE);
        }

        //Add panels and labels to window
        mainFrame.add(headerPanel);
        mainFrame.add(timeLabel);
        mainFrame.add(subheaderLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusPanel);
        mainFrame.add(themePanel);
        mainFrame.add(exportPanel);
        mainFrame.add(exitPanel);
        mainFrame.add(subtext);

        //Create and add buttons
        JButton addRepeating = new JButton("Add repeating event");
        JButton addEvent = new JButton("Add one-time event");
        JButton getList = new JButton("Show list of events");

        controlPanel.add(addRepeating);
        controlPanel.add(addEvent);
        controlPanel.add(getList);

        //Add action listeners to all buttons
        addRepeating.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddRepeatingWindow addRepeatingWindow = new AddRepeatingWindow();
            }
        });
        addEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddEventWindow addEventWindow = new AddEventWindow();
            }
        });
        getList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

                if (zeFiles == null && rzeFiles == null) {
                    statusPanel.setText("There are no events scheduled.");
                } else if (Objects.requireNonNull(zeFiles).length == 0 && Objects.requireNonNull(rzeFiles).length == 0) {
                    statusPanel.setText("There are no events scheduled.");
                } else {
                    EventsWindow eventsWindow = new EventsWindow();
                }
            }
        });
        theme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Main.colorTheme.equals("l")) {
                    Main.colorTheme = "d";
                } else if (Main.colorTheme.equals("d")) {
                    Main.colorTheme = "l";
                }
                mainFrame.dispose();
                MainWindow mainWindow = new MainWindow();
            }
        });
        defaultTheme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Main.defaultColorTheme.equals("l")) {
                    Main.defaultColorTheme = "d";
                    Main.colorTheme = "d";
                } else if (Main.defaultColorTheme.equals("d")) {
                    Main.defaultColorTheme = "l";
                    Main.colorTheme = "l";
                }

                Serialize.serializeSettings();

                mainFrame.dispose();

                MainWindow mainWindow = new MainWindow();
            }
        });
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                if (zeFiles != null || rzeFiles != null) {
                    try (
                            FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.home") + "/Downloads/ZoomScheduler - Export.zsx"));
                            GZIPOutputStream gos = new GZIPOutputStream(fos);
                            ObjectOutputStream oos = new ObjectOutputStream(gos)) {

                                if (zeFiles != null && zeFiles.length > 0) {
                                    for (File f : zeFiles) {
                                        oos.writeObject(f);
                                    }
                                }

                                if (rzeFiles != null && rzeFiles.length > 0) {
                                    for (File f : rzeFiles) {
                                        oos.writeObject(f);
                                    }
                                }
                        oos.flush();
                    } catch (IOException a) {
                        a.printStackTrace();
                    }
                }

                if (Tools.fileExists("ZoomScheduler - Export.zsx", new File(System.getProperty("user.home") + "/Downloads/"))) {
                    statusPanel.setText("Settings and data successfully exported to Downloads folder with the name: ZoomScheduler - Export.zsx");
                }
            }
        });
        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame confirm = new JFrame("Confirm exit");
                confirm.setUndecorated(true);
                confirm.setSize(200, 100);
                confirm.setShape(new RoundRectangle2D.Double(0, 0, 200, 100, 20, 20));
                confirm.setLayout(new GridLayout(2, 1));

                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                confirm.setLocation(dim.width / 2 - confirm.getSize().width / 2, dim.height / 2 - confirm.getSize().height / 2);

                JPanel exitConfirm = new JPanel();
                JLabel exitLabel = new JLabel("Are you sure you want to exit?");
                exitConfirm.add(exitLabel);

                JPanel exitButtons = new JPanel();
                JButton yes = new JButton("Yes");
                JButton no = new JButton("No");
                exitButtons.add(yes);
                exitButtons.add(no);

                if (Main.colorTheme.equals("d")) {
                    int textColor = 200;
                    Color darkText = new Color(textColor, textColor, textColor);
                    exitLabel.setForeground(darkText);

                    int themeColor = 16;
                    Color dark = new Color(themeColor, themeColor, themeColor);
                    exitConfirm.setBackground(dark);
                    exitButtons.setBackground(dark);
                }

                if (Main.colorTheme.equals("l")) {
                    exitLabel.setForeground(Color.BLACK);

                    int themeColor = 200;
                    Color light = new Color(themeColor, themeColor, themeColor);
                    exitConfirm.setBackground(light);
                    exitButtons.setBackground(light);
                }

                yes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Serialize.serializeSettings();
                        System.exit(0);
                    }
                });

                no.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        confirm.dispose();
                    }
                });

                confirm.add(exitConfirm);
                confirm.add(exitButtons);


                confirm.setVisible(true);
            }
        });
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Serialize.serializeSettings();
                mainFrame.setVisible(false);
            }
        });
        min.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setState(Frame.ICONIFIED);
            }
        });


        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

    }

    public void updateDateTime() {
        this.timeLabel.setText("<html>" + Tools.dateToString(Main.currentTime.substring(0, 10)) + "     &nbsp;      &nbsp;       &nbsp;      &nbsp;      &nbsp;      &nbsp;      &nbsp;      &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;" + Main.currentTime.substring(11) + "<html>");
    }

    public void setDateTime(String str) {
        this.timeLabel.setText(str);
    }

    public final JFrame getMainFrame() {return this.mainFrame;}
}

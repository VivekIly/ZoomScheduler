import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FilenameFilter;

public class EventsWindow {

    public EventsWindow() {
        Main.auxWindowOpen = true; prepareWindow();
    }

    public static void main(String[] args) {EventsWindow eventsWindow = new EventsWindow();}

    private void prepareWindow() {
        //Create JFrame
        JFrame mainFrame = new JFrame("List of events");


        //mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        //mainFrame.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        mainFrame.setSize(750, height - 100);
        mainFrame.setShape(new RoundRectangle2D.Double(0, 5, mainFrame.getWidth(), mainFrame.getHeight() - 11, 50, 50));
        mainFrame.setLayout(new GridLayout(19, 3));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(2, 0, 0, 2);


        //Create headerPanel
        JPanel headerPanel = new MotionPanel(mainFrame);
        headerPanel.setBounds(0, 0, mainFrame.getWidth(), 70);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLUE));
        headerPanel.setLayout(null);
        headerPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), 100));
        JLabel headerLabel = new JLabel("List of events:", JLabel.CENTER);
        Dimension headerSize = headerLabel.getPreferredSize();
        headerLabel.setBounds(100, headerPanel.getHeight()/50, headerSize.width*4, headerSize.height*2);
        headerLabel.setFont (headerLabel.getFont().deriveFont(22.0f));
        headerPanel.add(headerLabel);

        JLabel instruct1 = new JLabel("Click on an event to open the link");
        instruct1.setBounds(20, headerPanel.getHeight() - 45, headerSize.width*4, headerSize.height*2);
        headerPanel.add(instruct1);


        JPanel headerPanel2 = new MotionPanel(mainFrame);
        headerPanel2.setBounds(0, 0, mainFrame.getWidth(), 70);
        headerPanel2.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLUE));
        headerPanel2.setLayout(null);
        headerPanel2.setPreferredSize(new Dimension(mainFrame.getWidth(), 100));
        JLabel headerLabel2 = new JLabel("events:", JLabel.CENTER);
        Dimension headerSize2 = headerLabel2.getPreferredSize();
        headerLabel2.setBounds(-38, headerPanel2.getHeight()/50, headerSize2.width*4, headerSize2.height*2);
        headerLabel2.setFont (headerLabel2.getFont().deriveFont(22.0f));
        headerPanel2.add(headerLabel2);

        JLabel instruct2 = new JLabel("Right-click an event to remove it");
        instruct2.setBounds(0, headerPanel2.getHeight() - 45, headerSize2.width*8, headerSize2.height*2);
        headerPanel2.add(instruct2);

        mainFrame.add(headerPanel);
        mainFrame.add(headerPanel2);

        JButton close = new JButton("Close");
        mainFrame.add(close);

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


        File[] files = new File[zeFiles.length + rzeFiles.length];

        int index = 0;
        for (File f : zeFiles) {
            files[index] = f;
            index++;
        }
        for (File f : rzeFiles) {
            files[index] = f;
            index++;
        }

        int added = 0;

        for (File file : files) {
            JPanel eventPanel = new JPanel();
            if (file.getName().endsWith(".ze")) {
                ZoomEvent event = Serialize.fetch(file.getName());
                added++;
                String out = "<html>Name: " + event.getName() + "<br/>Date: " + event.getDate() + "<br/>Time: " + event.getTime();
                eventPanel.setLayout(null);
                JLabel details = new JLabel(out, JLabel.LEFT);

                Dimension size = details.getPreferredSize();
                details.setLayout(null);
                details.setBounds(5, -19, size.width * 4, size.height * 2);


                if (Main.colorTheme.equals("d")) {
                    int textColor = 200;
                    Color darkText = new Color(textColor, textColor, textColor);
                    eventPanel.setForeground(darkText);
                    headerPanel.setForeground(darkText);
                    headerLabel.setForeground(darkText);
                    headerPanel2.setForeground(darkText);
                    headerLabel2.setForeground(darkText);
                    details.setForeground(darkText);
                    mainFrame.setForeground(darkText);
                    instruct1.setForeground(darkText);
                    instruct2.setForeground(darkText);

                    Color dark = new Color(50, 53, 59);
                    eventPanel.setBackground(dark);
                    headerPanel.setBackground(dark);
                    headerLabel.setBackground(dark);
                    headerPanel2.setBackground(dark);
                    headerLabel2.setBackground(dark);
                    details.setBackground(dark);
                    mainFrame.setBackground(dark);
                }

                if (Main.colorTheme.equals("l")) {
                    int textColor = 0;
                    Color darkText = new Color(textColor, textColor, textColor);
                    eventPanel.setForeground(darkText);
                    headerPanel.setForeground(darkText);
                    headerLabel.setForeground(darkText);
                    headerPanel2.setForeground(darkText);
                    headerLabel2.setForeground(darkText);
                    details.setForeground(darkText);
                    mainFrame.setForeground(darkText);
                    instruct1.setForeground(darkText);
                    instruct2.setForeground(darkText);

                    int themeColor = 240;
                    Color dark = new Color(themeColor, themeColor, themeColor);
                    eventPanel.setBackground(dark);
                    headerPanel.setBackground(dark);
                    headerLabel.setBackground(dark);
                    headerPanel2.setBackground(dark);
                    headerLabel2.setBackground(dark);
                    details.setBackground(dark);
                    mainFrame.setBackground(dark);
                }

                details.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            event.openLink();
                        }
                    }
                });

                details.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            // TODO: Pop-up confirmation to remove event.
                            JFrame confirm = new JFrame("Confirm remove");
                            confirm.setUndecorated(true);
                            confirm.setSize(300, 100);
                            confirm.setShape(new RoundRectangle2D.Double(0, 0, confirm.getWidth(), confirm.getHeight(), 20, 20));
                            confirm.setLayout(new GridLayout(2, 1));

                            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                            confirm.setLocation(dim.width / 2 - confirm.getSize().width / 2, dim.height / 2 - confirm.getSize().height / 2);

                            JPanel exitConfirm = new JPanel();
                            JLabel exitLabel = new JLabel("<html>Are you sure you want to remove this event?<br/>" + event.toString() + "<html>", JLabel.CENTER);
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
                                    confirm.dispose();

                                    if (file.delete()) Tools.println(file.getName() + " successfully deleted.");
                                    else Tools.println(file.getName() + " could not be deleted.");

                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                    mainFrame.dispose();

                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
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
                    }
                });

                eventPanel.add(details);
                mainFrame.add(eventPanel);
            }
            else {
                RepeatingZoomEvent event = Serialize.fetchRepeating(file.getName());
                added++;
                String days = "";
                if (event.getRepeatingDays()[0] == 1) {
                    days += "M";
                }
                if (event.getRepeatingDays()[1] == 1) {
                    days += " Tu";
                }
                if (event.getRepeatingDays()[2] == 1) {
                    days += " W";
                }
                if (event.getRepeatingDays()[3] == 1) {
                    days += " Th";
                }
                if (event.getRepeatingDays()[4] == 1) {
                    days += " F";
                }
                if (event.getRepeatingDays()[5] == 1) {
                    days += " Sa";
                }
                if (event.getRepeatingDays()[6] == 1) {
                    days += " Su";
                }
                String out = "<html>Name: " + event.getName() + "&nbsp;(Repeating)<br/>Days: " + days + "<br/> Time: " + event.getTime();

                eventPanel.setLayout(null);
                JLabel details = new JLabel(out, JLabel.LEFT);

                Dimension size = details.getPreferredSize();
                details.setLayout(null);
                details.setBounds(5, -20, size.width * 4, size.height * 2);


                if (Main.colorTheme.equals("d")) {
                    int textColor = 200;
                    Color darkText = new Color(textColor, textColor, textColor);
                    eventPanel.setForeground(darkText);
                    headerPanel.setForeground(darkText);
                    headerLabel.setForeground(darkText);
                    headerPanel2.setForeground(darkText);
                    headerLabel2.setForeground(darkText);
                    details.setForeground(darkText);
                    mainFrame.setForeground(darkText);
                    instruct1.setForeground(darkText);
                    instruct2.setForeground(darkText);

                    Color dark = new Color(50, 53, 59);
                    eventPanel.setBackground(dark);
                    headerPanel.setBackground(dark);
                    headerLabel.setBackground(dark);
                    headerPanel2.setBackground(dark);
                    headerLabel2.setBackground(dark);
                    details.setBackground(dark);
                    mainFrame.setBackground(dark);
                }

                if (Main.colorTheme.equals("l")) {
                    int textColor = 0;
                    Color darkText = new Color(textColor, textColor, textColor);
                    eventPanel.setForeground(darkText);
                    headerPanel.setForeground(darkText);
                    headerLabel.setForeground(darkText);
                    headerPanel2.setForeground(darkText);
                    headerLabel2.setForeground(darkText);
                    details.setForeground(darkText);
                    mainFrame.setForeground(darkText);
                    instruct1.setForeground(darkText);
                    instruct2.setForeground(darkText);

                    int themeColor = 240;
                    Color dark = new Color(themeColor, themeColor, themeColor);
                    eventPanel.setBackground(dark);
                    headerPanel.setBackground(dark);
                    headerLabel.setBackground(dark);
                    headerPanel2.setBackground(dark);
                    headerLabel2.setBackground(dark);
                    details.setBackground(dark);
                    mainFrame.setBackground(dark);
                }

                details.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            event.openLink();
                        }
                    }
                });

                details.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            // TODO: Pop-up confirmation to remove event.
                            JFrame confirm = new JFrame("Confirm remove");
                            confirm.setUndecorated(true);
                            confirm.setSize(300, 100);
                            confirm.setShape(new RoundRectangle2D.Double(0, 0, confirm.getWidth(), confirm.getHeight(), 20, 20));
                            confirm.setLayout(new GridLayout(2, 1));

                            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                            confirm.setLocation(dim.width / 2 - confirm.getSize().width / 2, dim.height / 2 - confirm.getSize().height / 2);

                            JPanel exitConfirm = new JPanel();
                            JLabel exitLabel = new JLabel("<html>Are you sure you want to remove this event?<br/>" + event.toString() + "<html>", JLabel.CENTER);
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
                                    confirm.dispose();

                                    if (file.delete()) Tools.println(file.getName() + " successfully deleted.");
                                    else Tools.println(file.getName() + " could not be deleted.");

                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                    mainFrame.dispose();
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
                    }
                });

                eventPanel.add(details);
                mainFrame.add(eventPanel);
            }
        }

        for (int i = 0; i < 54 - added; i++) {
            JPanel blankPanel = new JPanel();

            if (Main.colorTheme.equals("d")) {
                int textColor = 200;
                Color darkText = new Color(textColor, textColor, textColor);
                blankPanel.setForeground(darkText);

                Color dark = new Color(50, 53, 59);
                blankPanel.setBackground(dark);
            }

            if (Main.colorTheme.equals("l")) {
                int textColor = 0;
                Color darkText = new Color(textColor, textColor, textColor);
                blankPanel.setForeground(darkText);

                int themeColor = 240;
                Color dark = new Color(themeColor, themeColor, themeColor);
                blankPanel.setBackground(dark);
            }
            mainFrame.add(blankPanel);
        }

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose(); Main.auxWindowOpen = false;
            }
        });

        if (Main.colorTheme.equals("d")) {
            int textColor = 200;
            Color darkText = new Color(textColor, textColor, textColor);
            headerPanel.setForeground(darkText);
            headerLabel.setForeground(darkText);
            headerPanel2.setForeground(darkText);
            headerLabel2.setForeground(darkText);

            Color dark = new Color(43, 46, 53);
            headerPanel.setBackground(dark);
            headerLabel.setBackground(dark);
            headerPanel2.setBackground(dark);
            headerLabel2.setBackground(dark);
        }

        if (Main.colorTheme.equals("l")) {
            int textColor = 0;
            Color darkText = new Color(textColor, textColor, textColor);
            headerPanel.setForeground(darkText);
            headerLabel.setForeground(darkText);
            headerPanel2.setForeground(darkText);
            headerLabel2.setForeground(darkText);

            int themeColor = 240;
            Color dark = new Color(themeColor, themeColor, themeColor);
            headerPanel.setBackground(dark);
            headerLabel.setBackground(dark);
            headerPanel2.setBackground(dark);
            headerLabel2.setBackground(dark);
        }

        mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
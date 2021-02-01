import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Collections;

public class EventsWindow {

    public EventsWindow() {
        prepareWindow();
    }

    public static void main(String[] args) {EventsWindow eventsWindow = new EventsWindow();}

    private void prepareWindow()
    {
        //Create JFrame
        JFrame mainFrame = new JFrame("Existing events");


        //mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        //mainFrame.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        mainFrame.setSize(750, height - 100);
        mainFrame.setShape(new RoundRectangle2D.Double(0, 4, mainFrame.getWidth(), mainFrame.getHeight() - 8, 50, 50));
        mainFrame.setLayout(new GridLayout(10, 3));

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
        headerLabel.setBounds(100, 20, headerSize.width*4, headerSize.height*2);
        headerLabel.setFont (headerLabel.getFont().deriveFont(22.0f));
        headerPanel.add(headerLabel);

        //Create event panel
        JPanel eventPanel = new JPanel();


        ArrayList<ZoomEvent> arl = Serialize.fetch();
        ArrayList<RepeatingZoomEvent> arlR = Serialize.fetchRepeating();

        JPanel headerPanel2 = new MotionPanel(mainFrame);
        headerPanel2.setBounds(0, 0, mainFrame.getWidth(), 70);
        headerPanel2.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLUE));
        headerPanel2.setLayout(null);
        headerPanel2.setPreferredSize(new Dimension(mainFrame.getWidth(), 100));
        JLabel headerLabel2 = new JLabel("events:", JLabel.CENTER);
        Dimension headerSize2 = headerLabel2.getPreferredSize();
        headerLabel2.setBounds(-38, 20, headerSize2.width*4, headerSize2.height*2);
        headerLabel2.setFont (headerLabel2.getFont().deriveFont(22.0f));
        headerPanel2.add(headerLabel2);
        mainFrame.add(headerPanel);
        mainFrame.add(headerPanel2);

        JButton close = new JButton("Close");
        mainFrame.add(close);

        int added = 0;
        if (arl != null) {
            for (ZoomEvent event : arl) {
                if (event != null) {
                    added++;
                    String out = "<html>Name: " + event.getName() + "<br/>Date: " + event.getDate() + "     &nbsp; &nbsp; &nbsp;     Right-click to remove<br/>Time: " + event.getTime() + "     &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;     Click to open link<br/> URL: " + event.getUri();
                    eventPanel.setLayout(null);
                    JLabel details = new JLabel(out, JLabel.LEFT);

                    Dimension size = details.getPreferredSize();
                    details.setLayout(null);
                    details.setBounds(5, -30, size.width*4, size.height*2);


                    if (Main.colorTheme.equals("d")) {
                        int textColor = 200;
                        Color darkText = new Color(textColor, textColor, textColor);
                        eventPanel.setForeground(darkText);
                        headerPanel.setForeground(darkText);
                        headerLabel.setForeground(darkText);
                        headerPanel2.setForeground(darkText);
                        headerLabel2.setForeground(darkText);
                        details.setForeground(Color.BLUE.darker());
                        mainFrame.setForeground(darkText);

                        int themeColor = 16;
                        Color dark = new Color(themeColor, themeColor, themeColor);
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
                        details.setForeground(Color.BLUE.darker());
                        mainFrame.setForeground(darkText);

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
                                confirm.setLocation(dim.width/2-confirm.getSize().width/2, dim.height/2-confirm.getSize().height/2);

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

                                        ArrayList<ZoomEvent> temp = new ArrayList<ZoomEvent>();
                                        for (int i = 0; i < arl.size(); i++) {
                                            if (!arl.get(i).toString().equals(event.toString())) {
                                                temp.set(i, arl.get(i));
                                            }
                                        }

                                        Collections.copy(arl, temp);

                                        Serialize.serialize(arl, false);
                                        Serialize.serializeSettings();

                                        Serialize.serializeRepeating(arlR, true);

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
        }

        if (arlR != null) {
            for (RepeatingZoomEvent event : arlR) {
                if (event != null) {
                    added++;
                    String days = "";
                    if (event.getRepeatingDays()[0] == 1) {
                        days += "Mon, ";
                    }
                    if (event.getRepeatingDays()[1] == 1) {
                        days += "Tues, ";
                    }
                    if (event.getRepeatingDays()[2] == 1) {
                        days += "Wed, ";
                    }
                    if (event.getRepeatingDays()[3] == 1) {
                        days += "Th, ";
                    }
                    if (event.getRepeatingDays()[4] == 1) {
                        days += "Fri, ";
                    }
                    if (event.getRepeatingDays()[5] == 1) {
                        days += "Sat, ";
                    }
                    if (event.getRepeatingDays()[6] == 1) {
                        days += "Sun";
                    }
                    String out = "<html>Name: " + event.getName() + "<br/>Days: " + days + " &nbsp;&nbsp;&nbsp;&nbsp; Click to open link<br/> Time: " + event.getTime() + "<br/> URL: " + event.getUri();
                    eventPanel.setLayout(null);
                    JLabel details = new JLabel(out, JLabel.LEFT);

                    Dimension size = details.getPreferredSize();
                    details.setLayout(null);
                    details.setBounds(5, -30, size.width*4, size.height*2);


                    if (Main.colorTheme.equals("d")) {
                        int textColor = 200;
                        Color darkText = new Color(textColor, textColor, textColor);
                        eventPanel.setForeground(darkText);
                        headerPanel.setForeground(darkText);
                        headerLabel.setForeground(darkText);
                        details.setForeground(darkText);
                        mainFrame.setForeground(darkText);

                        int themeColor = 16;
                        Color dark = new Color(themeColor, themeColor, themeColor);
                        eventPanel.setBackground(dark);
                        headerPanel.setBackground(dark);
                        headerLabel.setBackground(dark);
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
                        details.setForeground(Color.BLUE.darker());
                        mainFrame.setForeground(darkText);

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

                    eventPanel.add(details);
                    mainFrame.add(eventPanel);
                }
            }
        }

        for (int i = 0; i < 27 - added; i++) {
            JPanel blankPanel = new JPanel();

            if (Main.colorTheme.equals("l")) {
                int themeColor = 240;
                Color light = new Color(themeColor, themeColor, themeColor);
                blankPanel.setBackground(light);

                int textColor = 0;
                Color lightText = new Color(textColor, textColor, textColor);
                blankPanel.setForeground(lightText);
            }

            if (Main.colorTheme.equals("d")) {
                int themeColor = 16;
                Color dark = new Color(themeColor, themeColor, themeColor);
                blankPanel.setBackground(dark);

                int textColor = 200;
                Color darkText = new Color(textColor, textColor, textColor);
                blankPanel.setForeground(darkText);
            }
            mainFrame.add(blankPanel);
        }

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });


        mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
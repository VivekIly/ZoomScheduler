import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class MainWindow {

    public MainWindow() {
        displayWindow();
    }

    public static void main(String[] args) {
        MainWindow win = new MainWindow();
    }

    private void displayWindow() {
        JFrame mainFrame = new JFrame("Zoom Scheduler");
        //mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);

        //Set dimensions of window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); int width = (int) screenSize.getWidth(); int height = (int) screenSize.getHeight(); mainFrame.setSize((int)(width - 100), height - 100);
        mainFrame.setShape(new RoundRectangle2D.Double(10, 10, width - 150, height - 150, 50, 50));


        //Set layout
        mainFrame.setLayout(new GridLayout(7, 1));

        //Set labels
        JPanel headerPanel = new MotionPanel(mainFrame);
        headerPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
        headerPanel.setLayout(null);
        JLabel headerLabel = new JLabel("Zoom Scheduler", JLabel.CENTER);
        Dimension size = headerLabel.getPreferredSize();
        headerLabel.setBounds((int)(width/2.65), 100, size.width*4, size.height*2);
        headerLabel.setFont(headerLabel.getFont().deriveFont(32.0f));
        headerPanel.add(headerLabel);

        JLabel subheaderLabel = new JLabel("Select the action you would like to take: ", JLabel.CENTER);
        subheaderLabel.setSize((int)(width), 100);

        JLabel subtext = new JLabel("PLEASE DO NOT TAMPER WITH THE \'ZoomScheduler\' FOLDER!!",JLabel.CENTER);
        subtext.setSize((int)(width),100);
        subtext.setForeground(Color.RED);

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
            theme = new JButton("Switch to Light theme");
        }

        JButton defaultTheme = null;
        if (Main.defaultColorTheme.equals("l")) {
            defaultTheme = new JButton("Switch default theme to Dark");
        } else if (Main.defaultColorTheme.equals("d")) {
            defaultTheme = new JButton("Switch default theme to Light");
        }
        themePanel.add(theme);
        themePanel.add(defaultTheme);

        //Create status panel
        JLabel statusPanel = new JLabel("", JLabel.CENTER);
        statusPanel.setLayout(new FlowLayout());

        //Create exit application button
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout());
        JButton exit = new JButton("Exit Application");
        JButton close = new JButton("Close Window");
        exitPanel.add(close);
        exitPanel.add(exit);

        //Switches text  and background colors to fit the color theme
        if (Main.colorTheme.equals("d")) {
            int textColor = 200;
            Color darkText = new Color(textColor, textColor, textColor);
            headerLabel.setForeground(darkText);
            subheaderLabel.setForeground(darkText);
            controlPanel.setForeground(darkText);
            themePanel.setForeground(darkText);
            statusPanel.setForeground(darkText);
            exitPanel.setForeground(darkText);

            int themeColor = 25;
            Color dark = new Color(themeColor, themeColor, themeColor);
            headerPanel.setBackground(dark);
            controlPanel.setBackground(dark);
            themePanel.setBackground(dark);
            statusPanel.setBackground(dark);
            exitPanel.setBackground(dark);
            mainFrame.getContentPane().setBackground(dark);
        }

        if (Main.colorTheme.equals("l")) {
            headerLabel.setForeground(Color.GRAY);
            subheaderLabel.setForeground(Color.BLACK);
            controlPanel.setForeground(Color.BLACK);
            themePanel.setForeground(Color.BLACK);
            exitPanel.setForeground(Color.BLACK);

            controlPanel.setBackground(Color.WHITE);
            headerPanel.setBackground(Color.WHITE);
            themePanel.setBackground(Color.WHITE);
            exitPanel.setBackground(Color.WHITE);
            mainFrame.getContentPane().setBackground(Color.WHITE);
        }

        //Add panels and labels to window
        mainFrame.add(headerPanel);
        mainFrame.add(subheaderLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusPanel);
        mainFrame.add(themePanel);
        mainFrame.add(exitPanel);
        mainFrame.add(subtext);

        //Create and add buttons
        JButton addRepeating = new JButton("Add repeating event");
        JButton addEvent = new JButton("Add one-time event");
        JButton getList = new JButton("Show list of events");

        controlPanel.add(addRepeating);
        controlPanel.add(addEvent);
        controlPanel.add(getList);

        BinarySearchTree bst = Serialize.fetch();
        BinarySearchTree bstRepeating = Serialize.fetchRepeating();

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
                if (bst.getArray() == null) {
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
                Serialize serialize = new Serialize(bst, Main.defaultColorTheme);
                serialize.serializeSettings();

                mainFrame.dispose();

                MainWindow mainWindow = new MainWindow();
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Serialize serialize = new Serialize(bst, Main.defaultColorTheme);
                serialize.serialize(bst, false);
                serialize.serializeSettings();

                Serialize serialize1 = new Serialize(bstRepeating, Main.defaultColorTheme);
                serialize.serialize(bstRepeating, true);

                mainFrame.dispose();
                System.exit(0);
            }
        });
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Serialize serialize = new Serialize(bst, Main.defaultColorTheme);
                serialize.serialize(bst, false);
                serialize.serializeSettings();

                mainFrame.dispose();
            }
        });

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }


}

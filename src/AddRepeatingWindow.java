import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddRepeatingWindow {

    public AddRepeatingWindow() {prepareWindow();}

    public static void main(String[] args) {
        AddEventWindow win = new AddEventWindow(); System.out.println("Done.");
    }

    private void prepareWindow() {
        JFrame mainFrame = new JFrame("Add repeating event");
        mainFrame.setUndecorated(true);

        //Set dimensions of window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); int width = (int) screenSize.getWidth(); double height = (int) screenSize.getHeight(); mainFrame.setSize(600, (int) (height/1.5));
        mainFrame.setShape(new RoundRectangle2D.Double(10, 10, 587, (int) (height/1.6), 50, 50));

        //Set layout
        mainFrame.setLayout(new GridLayout(8, 1));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        //Set labels
        JPanel headerPanel = new MotionPanel(mainFrame);
        headerPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
        headerPanel.setLayout(null);
        JLabel headerLabel = new JLabel("Add a repeating event", JLabel.CENTER);
        Dimension size = headerLabel.getPreferredSize();
        headerLabel.setBounds(80, 30, size.width*4, size.height*2);
        headerLabel.setFont (headerLabel.getFont().deriveFont(22.0f));
        headerPanel.add(headerLabel);

        //Create name panel for event name
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JTextField name = new JTextField(30);
        JLabel nameLabel = new JLabel("Name of event: ");
        namePanel.add(nameLabel, constraints);
        namePanel.add(name, constraints);

        //Create day panel for event days
        JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new FlowLayout());
        dayPanel.setBounds(600, (int) (height/1.6), 100, 100);
        JLabel dayLabel = new JLabel("Toggle days the event will repeat:");
        JButton mon = new JButton("Monday");
        JButton tues = new JButton("Tuesday");
        JButton wed = new JButton("Wednesday");
        JButton thurs = new JButton("Thursday");
        JButton fri = new JButton("Friday");
        JButton sat = new JButton("Saturday");
        JButton sun = new JButton("Sunday");
        dayPanel.add(dayLabel, constraints);
        dayPanel.add(mon, constraints);
        dayPanel.add(tues, constraints);
        dayPanel.add(wed, constraints);
        dayPanel.add(thurs, constraints);
        dayPanel.add(fri, constraints);
        dayPanel.add(sat, constraints);
        dayPanel.add(sun, constraints);

        JLabel days = new JLabel("", JLabel.CENTER);

        int[] daysRepeating = new int[7];
        mon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (daysRepeating[0] == 0) {
                    daysRepeating[0] = 1;
                } else {
                    daysRepeating[0] = 0;
                }

                String text = "Event will repeat on these days:     ";
                boolean textFlag = false;
                if (daysRepeating[0] == 1) {
                    text += "Monday  ";
                    textFlag = true;
                }

                if (daysRepeating[1] == 1) {
                    text += "Tuesday   ";
                    textFlag = true;
                }

                if (daysRepeating[2] == 1) {
                    text += "Wednesday   ";
                    textFlag = true;
                }

                if (daysRepeating[3] == 1) {
                    text += "Thursday   ";
                    textFlag = true;
                }

                if (daysRepeating[4] == 1) {
                    text += "Friday   ";
                    textFlag = true;
                }

                if (daysRepeating[5] == 1) {
                    text += "Saturday   ";
                    textFlag = true;
                }

                if (daysRepeating[6] == 1) {
                    text += "Sunday";
                    textFlag = true;
                }

                if (textFlag) {
                    days.setText(text);
                } else {
                    days.setText("");
                }
            }
        });
        tues.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (daysRepeating[1] == 0) {
                    daysRepeating[1] = 1;
                } else {
                    daysRepeating[1] = 0;
                }

                String text = "Event will repeat on these days:     ";
                boolean textFlag = false;
                if (daysRepeating[0] == 1) {
                    text += "Monday  ";
                    textFlag = true;
                }

                if (daysRepeating[1] == 1) {
                    text += "Tuesday   ";
                    textFlag = true;
                }

                if (daysRepeating[2] == 1) {
                    text += "Wednesday   ";
                    textFlag = true;
                }

                if (daysRepeating[3] == 1) {
                    text += "Thursday   ";
                    textFlag = true;
                }

                if (daysRepeating[4] == 1) {
                    text += "Friday   ";
                    textFlag = true;
                }

                if (daysRepeating[5] == 1) {
                    text += "Saturday   ";
                    textFlag = true;
                }

                if (daysRepeating[6] == 1) {
                    text += "Sunday";
                    textFlag = true;
                }

                if (textFlag) {
                    days.setText(text);
                } else {
                    days.setText("");
                }
            }
        });
        wed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (daysRepeating[2] == 0) {
                    daysRepeating[2] = 1;
                } else {
                    daysRepeating[2] = 0;
                }

                String text = "Event will repeat on these days:     ";
                boolean textFlag = false;
                if (daysRepeating[0] == 1) {
                    text += "Monday  ";
                    textFlag = true;
                }

                if (daysRepeating[1] == 1) {
                    text += "Tuesday   ";
                    textFlag = true;
                }

                if (daysRepeating[2] == 1) {
                    text += "Wednesday   ";
                    textFlag = true;
                }

                if (daysRepeating[3] == 1) {
                    text += "Thursday   ";
                    textFlag = true;
                }

                if (daysRepeating[4] == 1) {
                    text += "Friday   ";
                    textFlag = true;
                }

                if (daysRepeating[5] == 1) {
                    text += "Saturday   ";
                    textFlag = true;
                }

                if (daysRepeating[6] == 1) {
                    text += "Sunday";
                    textFlag = true;
                }

                if (textFlag) {
                    days.setText(text);
                } else {
                    days.setText("");
                }
            }
        });
        thurs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (daysRepeating[3] == 0) {
                    daysRepeating[3] = 1;
                } else {
                    daysRepeating[3] = 0;
                }

                String text = "Event will repeat on these days:     ";
                boolean textFlag = false;
                if (daysRepeating[0] == 1) {
                    text += "Monday  ";
                    textFlag = true;
                }

                if (daysRepeating[1] == 1) {
                    text += "Tuesday   ";
                    textFlag = true;
                }

                if (daysRepeating[2] == 1) {
                    text += "Wednesday   ";
                    textFlag = true;
                }

                if (daysRepeating[3] == 1) {
                    text += "Thursday   ";
                    textFlag = true;
                }

                if (daysRepeating[4] == 1) {
                    text += "Friday   ";
                    textFlag = true;
                }

                if (daysRepeating[5] == 1) {
                    text += "Saturday   ";
                    textFlag = true;
                }

                if (daysRepeating[6] == 1) {
                    text += "Sunday";
                    textFlag = true;
                }

                if (textFlag) {
                    days.setText(text);
                } else {
                    days.setText("");
                }
            }
        });
        fri.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (daysRepeating[4] == 0) {
                    daysRepeating[4] = 1;
                } else {
                    daysRepeating[4] = 0;
                }

                String text = "Event will repeat on these days:     ";
                boolean textFlag = false;
                if (daysRepeating[0] == 1) {
                    text += "Monday  ";
                    textFlag = true;
                }

                if (daysRepeating[1] == 1) {
                    text += "Tuesday   ";
                    textFlag = true;
                }

                if (daysRepeating[2] == 1) {
                    text += "Wednesday   ";
                    textFlag = true;
                }

                if (daysRepeating[3] == 1) {
                    text += "Thursday   ";
                    textFlag = true;
                }

                if (daysRepeating[4] == 1) {
                    text += "Friday   ";
                    textFlag = true;
                }

                if (daysRepeating[5] == 1) {
                    text += "Saturday   ";
                    textFlag = true;
                }

                if (daysRepeating[6] == 1) {
                    text += "Sunday";
                    textFlag = true;
                }

                if (textFlag) {
                    days.setText(text);
                } else {
                    days.setText("");
                }
            }
        });
        sat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (daysRepeating[5] == 0) {
                    daysRepeating[5] = 1;
                } else {
                    daysRepeating[5] = 0;
                }

                String text = "Event will repeat on these days:     ";
                boolean textFlag = false;
                if (daysRepeating[0] == 1) {
                    text += "Monday  ";
                    textFlag = true;
                }

                if (daysRepeating[1] == 1) {
                    text += "Tuesday   ";
                    textFlag = true;
                }

                if (daysRepeating[2] == 1) {
                    text += "Wednesday   ";
                    textFlag = true;
                }

                if (daysRepeating[3] == 1) {
                    text += "Thursday   ";
                    textFlag = true;
                }

                if (daysRepeating[4] == 1) {
                    text += "Friday   ";
                    textFlag = true;
                }

                if (daysRepeating[5] == 1) {
                    text += "Saturday   ";
                    textFlag = true;
                }

                if (daysRepeating[6] == 1) {
                    text += "Sunday";
                    textFlag = true;
                }

                if (textFlag) {
                    days.setText(text);
                } else {
                    days.setText("");
                }
            }
        });
        sun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (daysRepeating[6] == 0) {
                    daysRepeating[6] = 1;
                } else {
                    daysRepeating[6] = 0;
                }

                String text = "Event will repeat on these days:     ";
                boolean textFlag = false;
                if (daysRepeating[0] == 1) {
                    text += "Monday  ";
                    textFlag = true;
                }

                if (daysRepeating[1] == 1) {
                    text += "Tuesday   ";
                    textFlag = true;
                }

                if (daysRepeating[2] == 1) {
                    text += "Wednesday   ";
                    textFlag = true;
                }

                if (daysRepeating[3] == 1) {
                    text += "Thursday   ";
                    textFlag = true;
                }

                if (daysRepeating[4] == 1) {
                    text += "Friday   ";
                    textFlag = true;
                }

                if (daysRepeating[5] == 1) {
                    text += "Saturday   ";
                    textFlag = true;
                }

                if (daysRepeating[6] == 1) {
                    text += "Sunday";
                    textFlag = true;
                }

                if (textFlag) {
                    days.setText(text);
                } else {
                    days.setText("");
                }
            }
        });

        //Create time panel for event time
        JPanel timePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JTextField time = new JTextField(30);
        JLabel timeLabel = new JLabel("Time of event (in 24-hour time): ");
        timePanel.add(timeLabel, constraints);
        timePanel.add(time, constraints);

        //Create link panel for event link
        JPanel linkPanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JTextField link = new JTextField(30);
        JLabel linkLabel = new JLabel("Link to event: ");
        linkPanel.add(linkLabel, constraints);
        linkPanel.add(link, constraints);

        JLabel subtext = new JLabel("", SwingConstants.CENTER);

        //Create control panel and add buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        controlPanel.add(save);
        controlPanel.add(cancel);

        //Switches text and background colors to fit the color theme
        if (Main.colorTheme.equals("d")) {
            int textColor = 200;
            Color darkText = new Color(textColor, textColor, textColor);
            headerLabel.setForeground(darkText);
            nameLabel.setForeground(darkText);
            controlPanel.setForeground(darkText);
            days.setForeground(darkText);
            dayLabel.setForeground(darkText);
            timeLabel.setForeground(darkText);
            linkLabel.setForeground(darkText);

            int themeColor = 20;
            Color dark = new Color(themeColor, themeColor, themeColor);
            mainFrame.setBackground(dark);
            headerPanel.setBackground(dark);
            headerLabel.setBackground(dark);
            days.setBackground(dark);
            controlPanel.setBackground(dark);
            namePanel.setBackground(dark);
            dayPanel.setBackground(dark);
            timePanel.setBackground(dark);
            linkPanel.setBackground(dark);
            subtext.setBackground(dark);
            mainFrame.getContentPane().setBackground(dark);
        }

        if (Main.colorTheme.equals("l")) {
            headerLabel.setForeground(Color.GRAY);
            namePanel.setForeground(Color.BLACK);
            controlPanel.setForeground(Color.BLACK);
            dayPanel.setForeground(Color.BLACK);
            timePanel.setForeground(Color.BLACK);
            linkPanel.setForeground(Color.BLACK);

            int themeColor = 245;
            Color light = new Color(themeColor, themeColor, themeColor);
            headerPanel.setBackground(light);
            controlPanel.setBackground(light);
            namePanel.setBackground(light);
            dayPanel.setBackground(light);
            timePanel.setBackground(light);
            linkPanel.setBackground(light);
            mainFrame.getContentPane().setBackground(light);
        }

        //Add panels and labels to window
        mainFrame.add(headerPanel);
        mainFrame.add(namePanel);
        mainFrame.add(dayPanel);
        mainFrame.add(days);
        mainFrame.add(timePanel);
        mainFrame.add(linkPanel);
        mainFrame.add(subtext);
        mainFrame.add(controlPanel);

        //Add action listeners to all buttons
        ArrayList<RepeatingZoomEvent> finalTempAL = Serialize.fetchRepeating();
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean nameFlag = false;
                boolean daysFlag = false;
                boolean timeFlag = false;
                boolean linkFlag = false;

                DateValidator dateValidator = new DateValidator("yyyy/MM/dd");
                if (name.getText().equals("")) {
                    nameFlag = true;
                } //Tests for unempty name field

                if (days.getText().equals("")) {
                    daysFlag = true;
                }

                try {
                    LocalTime.parse(time.getText());
                } catch (DateTimeParseException | NullPointerException i) {
                    timeFlag = true;
                } //Tests for valid time

                URI url = null;
                if (AddEventWindow.isValid(link.getText())) {
                    try {
                        url = new URI(link.getText());
                    } catch (URISyntaxException uriSyntaxException) {
                        linkFlag = true;
                    }
                } else {
                    linkFlag = true;
                } //Tests for valid link

                String errorMessage = "<html>";

                if (nameFlag) {
                    errorMessage += "Name field is empty.<br/>";
                }

                if (daysFlag) {
                    errorMessage+= "No days have been selected.<br/>";
                }

                if (timeFlag) {
                    errorMessage += "Invalid time.<br/>";
                }

                if (linkFlag) {
                    errorMessage += "Invalid link.";
                }

                errorMessage += "<html>";

                subtext.setForeground(Color.RED);
                subtext.setText(errorMessage);

                if (!nameFlag && !timeFlag && !daysFlag && !linkFlag) {
                    //RepeatingZoomEvent temp = new RepeatingZoomEvent(name.getText(), url, time.getText(), true, daysRepeating);
                    finalTempAL.add(new RepeatingZoomEvent(name.getText(), url, time.getText(), true, daysRepeating));
                    Serialize.serializeRepeating(finalTempAL, true);
                    mainFrame.dispose();
                    Main.setRerunFlag(true);
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

}

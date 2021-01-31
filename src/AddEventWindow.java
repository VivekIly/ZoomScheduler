import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class AddEventWindow {

    public AddEventWindow() {prepareWindow();}

    public static void main(String[] args) {
        AddEventWindow win = new AddEventWindow(); System.out.println("Done.");
    }

    private void prepareWindow() {
        JFrame mainFrame = new JFrame("Add one-time event");

        //Set dimensions of window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); int width = (int) screenSize.getWidth(); double height = (int) screenSize.getHeight(); mainFrame.setSize(width/3, (int) (height/1.5));

        //Set layout
        mainFrame.setLayout(new GridLayout(7, 1));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        //Set labels
        JLabel headerLabel = new JLabel("", JLabel.CENTER);
        headerLabel.setText("Add a one-time event");
        headerLabel.setFont (headerLabel.getFont().deriveFont(22.0f));

        //Create name panel for event name
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JTextField name = new JTextField(30);
        JLabel nameLabel = new JLabel("Name of event: ");
        namePanel.add(nameLabel, constraints);
        namePanel.add(name, constraints);

        //Create date panel for event date
        JPanel datePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JTextField date = new JTextField(30);
        JLabel dateLabel = new JLabel("Date of event (in yyyy/MM/dd format):");
        datePanel.add(dateLabel, constraints);
        datePanel.add(date, constraints);

        //Create time panel for event time
        JPanel timePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JTextField time = new JTextField(30);
        JLabel timeLabel = new JLabel("Time of event (in 24-hour time): ");
        timePanel.add(timeLabel, constraints);
        timePanel.add(time, constraints);

        //Create time panel for event time
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

        //Add panels and labels to window
        mainFrame.add(headerLabel);
        mainFrame.add(namePanel);
        mainFrame.add(datePanel);
        mainFrame.add(timePanel);
        mainFrame.add(linkPanel);
        mainFrame.add(subtext);
        mainFrame.add(controlPanel);

        //Add action listeners to all buttons
        BinarySearchTree finalTempBST = Serialize.fetch();
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean nameFlag = false;
                boolean dateFlag = false;
                boolean timeFlag = false;
                boolean linkFlag = false;

                DateValidator dateValidator = new DateValidator("yyyy/MM/dd");
                if (name.getText().equals("")) {
                    nameFlag = true;
                } //Tests for unempty name field
                if (!dateValidator.isValid(date.getText())) {
                    dateFlag = true;
                } //Tests for valid date

                try {
                    LocalTime.parse(time.getText());
                } catch (DateTimeParseException | NullPointerException i) {
                    timeFlag = true;
                } //Tests for valid time

                URI url = null;
                if (isValid(link.getText())) {
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

                if (dateFlag) {
                    errorMessage+= "Invalid date.<br/>";
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

                if (!nameFlag && !timeFlag && !dateFlag && !linkFlag) {
                    ZoomEvent temp = new ZoomEvent(name.getText(), url, date.getText(), time.getText());
                    finalTempBST.addNode(new Node(temp, name.getText(), name.getText()));
                    Serialize serialize = new Serialize(finalTempBST);
                    serialize.serialize();
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

    public static boolean isValid(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }
}

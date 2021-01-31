import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    public MainWindow() {
        displayWindow();
    }

    public static void main(String[] args) {
        MainWindow win = new MainWindow();
    }

    private void displayWindow() {
        JFrame mainFrame = new JFrame("Zoom Scheduler");

        //Set dimensions of window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); int width = (int) screenSize.getWidth(); int height = (int) screenSize.getHeight(); mainFrame.setSize(width, height);

        //Set layout
        mainFrame.setLayout(new GridLayout(4, 1));

        //Set labels
        JLabel headerLabel = new JLabel("Zoom Scheduler", JLabel.CENTER);
        headerLabel.setFont(headerLabel.getFont().deriveFont(32.0f));

        JLabel subheaderLabel = new JLabel("Select the action you would like to take: ", JLabel.CENTER);
        subheaderLabel.setSize(width, 100);

        JLabel subtext = new JLabel("PLEASE DO NOT TAMPER WITH THE \'ZoomScheduler\' FOLDER!!",JLabel.CENTER);
        subtext.setSize(width,100);
        subtext.setForeground(Color.RED);

        //Create control panel for buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        //Add panels and labels to window
        mainFrame.add(headerLabel);
        mainFrame.add(subheaderLabel);
        mainFrame.add(controlPanel);
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

            }
        });
        addEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddEventWindow addEventWindow = new AddEventWindow();
            }
        });
        getList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventsWindow eventsWindow = new EventsWindow();
            }
        });

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }


}

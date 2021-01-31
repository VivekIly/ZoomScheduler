import javax.swing.*;
import java.awt.*;

public class EventsWindow {

    public EventsWindow() {
        prepareWindow();
    }

    private void prepareWindow() {
//Create a JPanel
        JPanel panel = new JPanel();


        //Create scrollbar
        JScrollPane scrollBar = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Create JFrame
        JFrame mainFrame = new JFrame("Existing events");
        //mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(false);
        //mainFrame.setLayout(new GridLayout(2, 1));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(2, 0, 0, 2);

        //Add scrollbar to mainFrame
        //mainFrame.add(scrollBar);


        BinarySearchTree bst = Serialize.fetch();

        ZoomEvent[] array = new ZoomEvent[bst.getArray().length];

        int index = 0;
        for (Object o : bst.getArray()) {
            if (!((ZoomEvent) o).isRepeating()) {
                array[index] = (ZoomEvent) o;
                index++;
            } else {

            }
        }

        JTabbedPane eventLabel = new JTabbedPane();
        if (array != null) {
            for (ZoomEvent event : array) {
                if (event != null) {
                    String out = "<html>Name: " + event.getName() + "<br/>Date: " + event.getDate() + "<br/> Time: " + event.getTime() + "<br/> URL: " + event.getUri();
                    JPanel eventPanel = new JPanel();
                    eventPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                    JLabel details = new JLabel(out, JLabel.LEFT);

                    if (Main.colorTheme.equals("d")) {
                        eventPanel.setBackground(new Color(25, 25, 25));
                        details.setForeground(new Color(200, 200, 200));
                    } else if (Main.colorTheme.equals("l")) {
                        eventPanel.setBackground(Color.WHITE);
                        details.setForeground(Color.BLACK);
                    }
                    eventPanel.add(details);
                    eventLabel.add(event.getName(), eventPanel);
                    eventLabel.setSize(400, 300);
                    panel.add(eventLabel, constraints);
                    mainFrame.add(eventLabel);
                }
            }
        }

        /*close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });*/


        mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainFrame.setSize(400, 500);
        mainFrame.setVisible(true);
    }
}
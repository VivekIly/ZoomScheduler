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
        JScrollPane scrollBar = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Create JFrame
        JFrame frame = new JFrame("Existing events");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(2, 0, 0, 2);

        //Add scrollbar to frame
        frame.add(scrollBar);

        BinarySearchTree bst = Serialize.fetch();

        ZoomEvent[] array = new ZoomEvent[bst.getArray().length];

        int index = 0;
        for (Object o : bst.getArray()) {
            array[index] = (ZoomEvent) o;
            index++;
        }

        if (array != null) {
            for (ZoomEvent event : array) {
                if (event != null) {
                    String out = event.getName() + "     " + event.getDate() + "     " + event.getTime() + "     " + event.getUri();
                    JLabel eventLabel = new JLabel(out, JLabel.LEFT);
                    eventLabel.setSize(400, 100);
                    panel.add(eventLabel, constraints);
                    frame.add(eventLabel);
                }
            }
        } else {
            JLabel eventLabel = new JLabel("There are no events scheduled.", JLabel.CENTER);
            eventLabel.setSize(400, 100);
            panel.add(eventLabel, constraints);
            frame.add(eventLabel);
        }

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
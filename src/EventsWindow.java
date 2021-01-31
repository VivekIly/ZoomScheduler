import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class EventsWindow {

    public EventsWindow() {
        prepareWindow();
    }

    private void prepareWindow() {
        //Create JFrame
        JFrame mainFrame = new JFrame("Existing events");


        //Create scrollbar
        //JScrollPane scrollBar = new JScrollPane(headerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); int width = (int) screenSize.getWidth(); int height = (int) screenSize.getHeight(); mainFrame.setSize((int)(width - 100), height - 100);
        mainFrame.setShape(new RoundRectangle2D.Double(10, 10, width - 150, height - 150, 50, 50));

        //mainFrame.setLayout(new GridLayout(2, 1));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(2, 0, 0, 2);

        //Add scrollbar to mainFrame
        //mainFrame.add(scrollBar);


        ArrayList<ZoomEvent> arl = Serialize.fetch();

        ZoomEvent[] array = new ZoomEvent[arl.size()];

        int index = 0;
        for (Object o : arl) {
            if (o != null) {
                if (!((ZoomEvent) o).isRepeating()) {
                    array[index] = (ZoomEvent) o;
                    index++;
                } else {

                }
            }
        }

        JTabbedPane eventLabel = new JTabbedPane();
        if (array != null) {
            for (ZoomEvent event : array) {
                if (event != null) {
                    String out = "<html>Name: " + event.getName() + "<br/>Date: " + event.getDate() + "<br/> Time: " + event.getTime() + "<br/> URL: " + event.getUri();
                    JPanel eventPanel = new JPanel();
                    eventPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
                    eventPanel.setLayout(null);
                    JLabel details = new JLabel(out, JLabel.LEFT);

                    Dimension size = details.getPreferredSize();
                    details.setBounds((int)(width/2.65), 100, size.width*4, size.height*2);


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
                    eventLabel.add(eventPanel, constraints);
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    private JFrame mainFrame;
    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem item1;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public MainWindow() {prepareWindow();}

    public static void main(String[] args) {
        MainWindow win = new MainWindow(); System.out.println("Done.");
    }

    private void prepareWindow() {
        this.mainFrame = new JFrame("Zoomer");
        /*this.menubar = new JMenuBar();
        this.menu = new JMenu("FILL");
        this.item1 = new JMenuItem("1");

        this.menu.add(this.item1);

        this.menubar.add(this.menu);

        this.mainFrame.setJMenuBar(menubar);*/

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        this.mainFrame.setSize(width, height);
        this.mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        headerLabel.setText("Zoom Scheduler");

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);

        JButton addRepeating = new JButton("Add repeating event");
        JButton addEvent = new JButton("Add one-time event");
        JButton getList = new JButton("Show list of events");

        controlPanel.add(addRepeating);
        controlPanel.add(addEvent);
        controlPanel.add(getList);

        addRepeating.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Add repeating event");

            }
        });

        addEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Add one-time event");
            }
        });

        getList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Show list of events");
            }
        });

        this.mainFrame.setVisible(true);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}

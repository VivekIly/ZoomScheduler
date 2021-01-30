import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingControlDemo {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu x;
    private JMenuItem m1, m2, m3;

    public SwingControlDemo(){
        prepareGUI();
    }
    public static void main(String[] args){
        SwingControlDemo  swingControlDemo = new SwingControlDemo();
        swingControlDemo.showButtonDemo();
    }
    private void prepareGUI(){
        mainFrame = new JFrame("Zoomer");
        mb = new JMenuBar();
        x = new JMenu("Menu");
        m1 = new JMenuItem("1");
        m2 = new JMenuItem("2");
        m3 = new JMenuItem("3");
        x.add(m1); x.add(m2); x.add(m3);
        mb.add(x);
        mainFrame.setJMenuBar(mb);
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    private static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = SwingControlDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    private void showButtonDemo(){
        headerLabel.setText("Control in action: Button");

        //resources folder should be inside SWING folder.
        ImageIcon icon = createImageIcon("/resources/java_icon.png","Java");

        JButton okButton = new JButton("OK");
        JButton javaButton = new JButton("Submit", icon);
        JButton cancelButton = new JButton("Cancel", icon);
        cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);


        controlPanel.add(okButton);
        controlPanel.add(javaButton);
        controlPanel.add(cancelButton);

        m1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Ok Button clicked.");
            }
        });


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Ok Button clicked.");
            }
        });
        javaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Submit Button clicked.");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Cancel Button clicked.");
            }
        });

        mainFrame.setVisible(true);
    }
}
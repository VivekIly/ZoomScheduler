import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class LoadingWindow {

    private boolean close;

    public LoadingWindow() {
        this.close = false;
        displayWindow();
    }

    public static void main(String[] args) {
        LoadingWindow loadingWindow = new LoadingWindow();
    }

    public void displayWindow() {
        JFrame mainFrame = new JFrame();
        JLabel loadingLabel = new JLabel("", JLabel.CENTER);

        mainFrame.setSize(100, 50);
        mainFrame.setUndecorated(true);
        mainFrame.setShape(new RoundRectangle2D.Double(0, 0, mainFrame.getWidth(), mainFrame.getHeight(), 10, 10));

        mainFrame.add(loadingLabel);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        for (int i = 0; i >= 0; i++) {
            if (i % 4 == 0) {
                loadingLabel.setText("Loading.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (i % 4 == 1) {
                loadingLabel.setText("Loading..");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (i % 4 == 2) {
                loadingLabel.setText("Loading...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                loadingLabel.setText("Loading....");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void close() {
        this.close = true;
    }
}

// TODO: dark and light themes.
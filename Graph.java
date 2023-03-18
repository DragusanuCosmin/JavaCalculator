import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graph extends JPanel {
    public int x = 0;
    public int y = 0;

    public Graph() {
        setLayout(new GridLayout(0, 1));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 500));
    }

    public void addPoint(int x, int y) {
        JLabel point = new JLabel("●");
        point.setForeground(Color.BLUE);
        point.setHorizontalAlignment(JLabel.CENTER);
        point.setVerticalAlignment(JLabel.CENTER);
        point.setLocation(x, y);
        add(point);
        revalidate();
        repaint();
    }
}
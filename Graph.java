import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Graph extends JPanel {

    private Point dragStart;
    private Point origin = new Point(0, 0);

    public Graph() {
        setPreferredSize(new Dimension(600, 400));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dragStart = new Point(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - dragStart.x;
                int dy = e.getY() - dragStart.y;
                origin.setLocation(origin.x + dx, origin.y + dy);
                dragStart = e.getPoint();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        int ox = origin.x;
        int oy = origin.y;
        g2.drawLine(ox, oy + height / 2, ox + width, oy + height / 2);

        // Draw y-axis
        g2.drawLine(ox + width / 2, oy, ox + width / 2, oy + height);

        // Draw ticks and labels on x-axis
        for (int i = -5; i <= 5; i++) {
            int x = ox + width / 2 + i * 50;
            g2.drawLine(x, oy + height / 2 - 5, x, oy + height / 2 + 5);
            g2.drawString(Integer.toString(i), x - 4, oy + height / 2 + 20);
        }

        // Draw ticks and labels on y-axis
        for (int i = -5; i <= 5; i++) {
            int y = oy + height / 2 - i * 50;
            g2.drawLine(ox + width / 2 - 5, y, ox + width / 2 + 5, y);
            g2.drawString(Integer.toString(i), ox + width / 2 + 10, y + 4);
        }
    }
}

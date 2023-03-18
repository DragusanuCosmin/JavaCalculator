import java.awt.Color;
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
    private double scale=1.00;
    private Point origin=new Point(0,0);

    public Graph() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.BLACK);
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
    public void repaint(){
        super.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.white);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width=getWidth();
        int height=getHeight();
        g2.drawLine(0, origin.y+height/2, width,origin.y+height/2);
        g2.drawLine(origin.x+width/2,0, origin.x+width/2, height);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TreePanel extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener {
    private List<String> lines;
    private double scale = 1.0;
    private int offsetX = 0;
    private int offsetY = 0;
    private Point lastDragPoint;

    public TreePanel(String path) {
        lines = readFile(path);
        addMouseWheelListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(3000, 3000));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale, scale);
        g2d.translate(offsetX, offsetY);
        if (lines != null) {
            drawTree(g2d, lines);
        }
    }

    private void drawTree(Graphics g, List<String> lines) {
        g.setFont(new Font("Arial", Font.BOLD, 66));
        int y = 30;
        for (String line : lines) {
            int x = 20;
            for (char ch : line.toCharArray()) {
                if (ch == '|') {
                    g.drawString(String.valueOf(ch), x, y);
                } else {
                    g.drawString(String.valueOf(ch), x, y );
                }
                x += 15; // adjust spacing as needed
            }
            y += 20; // adjust spacing as needed
        }
    }

    private List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            int notches = e.getWheelRotation();
            double scaleFactor = Math.pow(1.1, notches);
            scale *= scaleFactor;
            scale = Math.max(0.1, scale);
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastDragPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currentPoint = e.getPoint();
        int deltaX = currentPoint.x - lastDragPoint.x;
        int deltaY = currentPoint.y - lastDragPoint.y;
        offsetX += deltaX;
        offsetY += deltaY;
        lastDragPoint = currentPoint;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastDragPoint = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // No implementation needed for this method
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // No implementation needed for this method
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No implementation needed for this method
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No implementation needed for this method
    }
}

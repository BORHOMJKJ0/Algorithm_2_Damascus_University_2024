import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BinaryTreeDrawer extends JPanel {
    public Node root;
    private Map<Node, Point> nodePositions;
    private double scale = 1.0;
    private static final int NODE_SIZE = 50;
    private static final int HORIZONTAL_SPACING = 700;
    private static final int VERTICAL_SPACING = 200;

    private int offsetX = 0;
    private int offsetY = 0;

    public BinaryTreeDrawer(Node root) {
        this.root = root;
        this.nodePositions = new HashMap<>();
        this.setPreferredSize(new Dimension(3000, 3000));
        ZoomHandler zoomHandler = new ZoomHandler();
        addMouseWheelListener(zoomHandler);
        addMouseListener(zoomHandler);
        addMouseMotionListener(zoomHandler);
    }

    public void Drow() {
        calculateNodePositions(root, 1370, 0, HORIZONTAL_SPACING);
        revalidate();
        repaint();
    }

    private void calculateNodePositions(Node node, int x, int y, int xOffset) {
        if (node == null) {
            return;
        }

        nodePositions.put(node, new Point(x, y));

        calculateNodePositions(node.left, x - xOffset, y + VERTICAL_SPACING, xOffset / 2);
        calculateNodePositions(node.right, x + xOffset, y + VERTICAL_SPACING, xOffset / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(offsetX, offsetY);
        g2.scale(scale, scale);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTree(g2, root);
    }

    private void drawTree(Graphics2D g, Node node) {
        if (node == null) {
            return;
        }

        Point position = nodePositions.get(node);

        g.setColor(Color.WHITE);
        g.fillOval(position.x, position.y, NODE_SIZE, NODE_SIZE);
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString(String.valueOf(node.c), position.x + NODE_SIZE / 3, position.y + NODE_SIZE / 2);

        if (node.left != null) {
            Point leftPosition = nodePositions.get(node.left);
            g.drawLine(position.x + NODE_SIZE / 2, position.y + NODE_SIZE, leftPosition.x + NODE_SIZE / 2,
                    leftPosition.y);
            drawTree(g, node.left);
        }

        if (node.right != null) {
            Point rightPosition = nodePositions.get(node.right);
            g.drawLine(position.x + NODE_SIZE / 2, position.y + NODE_SIZE, rightPosition.x + NODE_SIZE / 2,
                    rightPosition.y);
            drawTree(g, node.right);
        }
    }

    private class ZoomHandler implements MouseWheelListener, MouseListener, MouseMotionListener {
        private Point lastDragPoint;

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            double oldScale = scale;
            if (e.getPreciseWheelRotation() < 0) {
                scale *= 1.1;
            } else {
                scale /= 1.1;
            }

            // Recalculate offsets to keep the zoom centered on the mouse position
            int x = e.getX();
            int y = e.getY();
            offsetX = (int) (x - (x - offsetX) * (scale / oldScale));
            offsetY = (int) (y - (y - offsetY) * (scale / oldScale));

            revalidate();
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            lastDragPoint = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (lastDragPoint != null) {
                Point currentPoint = e.getPoint();
                offsetX += currentPoint.x - lastDragPoint.x;
                offsetY += currentPoint.y - lastDragPoint.y;
                lastDragPoint = currentPoint;
                repaint();
            }
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
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class GenericTreeDrawer extends JPanel {
    GenericNodes root;
    Map<GenericNodes, Point> nodePositions;
    private double scale = 1.0;
    private int frameWidth;
    private static final int NODE_SIZE = 30;
    private int HORIZONTAL_SPACING ;
    private static final int VERTICAL_SPACING = 100;

    public GenericTreeDrawer(GenericNodes genericTreeRoot,int number) {
        this.HORIZONTAL_SPACING=number*100;
        this.root = genericTreeRoot;
        this.nodePositions = new HashMap<>();
        frameWidth=3000;
        this.setPreferredSize(new Dimension(3000, 3000));
        addMouseWheelListener(new ZoomHandler());
    }

    public void draw() {
        if (root != null) {
            calculateNodePositions(root, HORIZONTAL_SPACING+frameWidth/10, 0, HORIZONTAL_SPACING);
            revalidate();
            repaint();
        } else {
            System.out.println("Root is null");
        }
    }

    private void calculateNodePositions(GenericNodes node, int x, int y, int xOffset) {
        if (node == null) {
            return;
        }

        nodePositions.put(node, new Point(x, y));

        int childCount = node.childrens.size();
        int childSpacing = Math.max(1, xOffset / (childCount + 1));
        int position;
        for (int i = 0; i < childCount; i++) {
            if (childCount == 1 || childCount == 0)
                position = childCount + 1;
            else
                position = childCount;
            calculateNodePositions(node.childrens.get(i), x - xOffset + (i + position) * childSpacing, y + VERTICAL_SPACING,
                    xOffset / childCount);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(scale, scale);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTree(g2, root);
    }

    private void drawTree(Graphics2D g, GenericNodes node) {
        if (node == null) {
            return;
        }

        Point position = nodePositions.get(node);
        if (position == null) {
            System.out.println("Position is null for node: " + node.c);
            return;
        }

        g.setColor(Color.WHITE);
        g.fillOval(position.x, position.y, NODE_SIZE, NODE_SIZE);
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int textWidth = metrics.stringWidth(String.valueOf(node.c));
        int textHeight = metrics.getHeight();

        int textX = position.x + (NODE_SIZE - textWidth) / 2;
        int textY = position.y + (NODE_SIZE - textHeight) / 2 + metrics.getAscent();

        g.drawString(String.valueOf(node.c), textX, textY);

        for (GenericNodes child : node.childrens) {
            Point childPosition = nodePositions.get(child);
            if (childPosition != null) {
                g.drawLine(position.x + NODE_SIZE / 2, position.y + NODE_SIZE, childPosition.x + NODE_SIZE / 2, childPosition.y);
            }
            drawTree(g, child);
        }
    }

    private class ZoomHandler implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getPreciseWheelRotation() < 0) {
                scale *= 1.1;
            } else {
                scale /= 1.1;
            }
            revalidate();
            repaint();
        }
    }
}

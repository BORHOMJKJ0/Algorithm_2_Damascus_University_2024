import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DrawG extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JButton button;
    private GenericNodes tree;
    private binaryTree binaryTreeRoot;
    private int number;

    public DrawG(binaryTree binaryTreeRoot, GenericNodes genericTreeRoot,int number) {
        this.tree = genericTreeRoot;
        this.binaryTreeRoot = binaryTreeRoot;
        this.number=number;
        setTitle("Generic Tree Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        label = new JLabel("Generic Tree");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        button = new JButton("Convert");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(this);
        panel.add(label, BorderLayout.NORTH);
        panel.add(button, BorderLayout.SOUTH);
        add(panel, BorderLayout.NORTH);
        GenericTreeDrawer treeDrawer = new GenericTreeDrawer(genericTreeRoot,number);
        treeDrawer.draw();
        JScrollPane scrollPane = new JScrollPane(treeDrawer);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().setViewPosition(new Point(number*100-370, 0));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            dispose();
            new DrawB(binaryTreeRoot, tree,number);
        }
    }
}

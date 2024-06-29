import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class oppositebinaryTreeFrame extends JFrame implements ActionListener {
    private JButton oppositeButton,RecButton;
    private JButton binaryTreeButton;
    private TreePanel treePanel;
    private JScrollPane scrollPane;
    Node root,opposite;
    private int maxNumber,number;
    String Ofile,Rfile;
    JLabel label;
    public oppositebinaryTreeFrame(Node root,int ans , String Ofile,String Rfile,Node opposite,int number) {
        this.number=number;
        this.Ofile = Ofile;
        this.Rfile=Rfile;

        this.root=root;this.opposite=opposite;
        this.maxNumber=ans;
        setTitle("opposite Binary Tree");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel controlPanel = new JPanel();
        oppositeButton = new JButton("opposite Rectangle");
        binaryTreeButton = new JButton("Binary Tree");
        RecButton = new JButton("Rectangle");
        oppositeButton.addActionListener(this);
        binaryTreeButton.addActionListener(this);
        RecButton.addActionListener(this);
        controlPanel.add(RecButton);
        controlPanel.add(binaryTreeButton);
        controlPanel.add(oppositeButton);
        //treePanel = new TreePanel();
       BinaryTreeDrawer binaryTreeDrawer = new BinaryTreeDrawer(opposite);
        JPanel panel = new JPanel();
        label=new JLabel();
        panel.setBackground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
       // panel.add(label, BorderLayout.NORTH);
       // BinaryTreeDrawer binaryTreeDrawer = new BinaryTreeDrawer(opposite);
        binaryTreeDrawer.Drow();
        scrollPane = new JScrollPane(binaryTreeDrawer);
        scrollPane.getViewport().setViewPosition(new Point(number*100-500, 0));
        add(controlPanel, BorderLayout.NORTH);
        panel.add(scrollPane);
        add(panel,BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == RecButton) {
            this.dispose();
            RectangleFrame oppositee = new RectangleFrame(root,maxNumber,Ofile,Rfile,opposite,number);
        } else if (e.getSource() == binaryTreeButton) {
            this.dispose();
            binaryTreeFrame binartree = new binaryTreeFrame(root,maxNumber,Ofile,Rfile,opposite,number);

        } else if (e.getSource()==oppositeButton) {
            this.dispose();
            oppositeRectangleFrame opp = new oppositeRectangleFrame(root,maxNumber,Ofile,Rfile,opposite,number);

        }
    }
}
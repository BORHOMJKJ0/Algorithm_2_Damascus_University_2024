import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class binaryTreeFrame extends JFrame implements ActionListener {
    private JButton Rec,oppositeRec;
    private JButton binaryTreeButton;
    private TreePanel treePanel;
    private JScrollPane scrollPane;
    private int maxNumber;
    private JLabel label;
    Node root,opposite ;
    String Ofile,Rfile;
    int number;
    public binaryTreeFrame(Node root,int ans , String Ofile,String Rfile,Node opposite,int number) {
        this.number=number;
        this.Rfile=Rfile;
        this.Ofile=Ofile;
        this.opposite=opposite;
        this.root=root;
        this.maxNumber=ans;
        setTitle("BinaryTree");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel controlPanel = new JPanel();
        Rec = new JButton("Rectangle");
        binaryTreeButton = new JButton("opoosite Binary Tree");
        oppositeRec = new JButton("opposite Rectangle");
        Rec.addActionListener(this);
        binaryTreeButton.addActionListener(this);
        oppositeRec.addActionListener(this);
        controlPanel.add(oppositeRec);
        controlPanel.add(binaryTreeButton);
        controlPanel.add(Rec);
        //treePanel = new TreePanel();
      //  BinaryTreeDrawer treeDrawer = new BinaryTreeDrawer(root);
        JPanel panel = new JPanel();
        label=new JLabel();
        panel.setBackground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        //panel.add(label, BorderLayout.NORTH);
        BinaryTreeDrawer binaryTreeDrawer = new BinaryTreeDrawer(root);
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
        if (e.getSource() == Rec) {
            RectangleFrame oppositee = new RectangleFrame(root,maxNumber,Ofile,Rfile,opposite,number);
            this.dispose();
        } else if (e.getSource() == binaryTreeButton) {
             new oppositebinaryTreeFrame(root,maxNumber,Ofile,Rfile,opposite,number);
            this.dispose();
        } else if (e.getSource()==oppositeRec) {
            oppositeRectangleFrame opp = new oppositeRectangleFrame(root,maxNumber,Ofile,Rfile,opposite,number);
            this.dispose();
        }
    }
}
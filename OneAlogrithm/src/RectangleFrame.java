import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RectangleFrame extends JFrame implements ActionListener {
    private JButton oppositeButton,oppositebinaryTreeButton,maxNumber;
    private JButton binaryTreeButton;
    private TreePanel treePanel;
    private JScrollPane scrollPane;
    private int ans,number;
    Node root,opposite ;
    String  Ofile,Rfile;
    public RectangleFrame(Node root,int ans , String Ofile,String Rfile,Node opposite,int number) {
        this.number=number;
        this.opposite=opposite;
        this.Rfile=Rfile;
        this.Ofile = Ofile;
        this.root = root;
        this.ans=ans;
        setTitle("Rectangle");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel controlPanel = new JPanel();
        oppositeButton = new JButton("opposite Rectangle");
        binaryTreeButton = new JButton("Binary Tree");
        oppositebinaryTreeButton = new JButton("opposite Binary Tree");
        oppositeButton.addActionListener(this);
        maxNumber = new JButton("Max Number");
        maxNumber.addActionListener(this);
        binaryTreeButton.addActionListener(this);
        oppositebinaryTreeButton.addActionListener(this);
        controlPanel.add(oppositebinaryTreeButton);
        controlPanel.add(binaryTreeButton);
        controlPanel.add(oppositeButton);
        controlPanel.add(maxNumber);
        treePanel = new TreePanel(Ofile);
        scrollPane = new JScrollPane(treePanel);
        scrollPane.setPreferredSize(new Dimension(3000, 3000));
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        treePanel.revalidate();
        treePanel.repaint();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == oppositebinaryTreeButton) {
            oppositebinaryTreeFrame oppositee = new oppositebinaryTreeFrame(root,ans ,Ofile,Rfile,opposite,number);
            this.dispose();
        } else if (e.getSource() == binaryTreeButton) {
            binaryTreeFrame binartree = new binaryTreeFrame(root,ans , Ofile,Rfile,opposite,number);
            this.dispose();
        } else if (e.getSource()==oppositeButton) {
            oppositeRectangleFrame opp = new oppositeRectangleFrame(root,ans ,Ofile,Rfile,opposite,number);
            this.dispose();
        }
        else if (e.getSource()==maxNumber) {
            JOptionPane.showMessageDialog(null, "The Max Number of Rectangles = "+ans,"Message" , JOptionPane.INFORMATION_MESSAGE);
        }
    }}
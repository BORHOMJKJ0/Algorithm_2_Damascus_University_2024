import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class oppositeRectangleFrame extends JFrame implements ActionListener {
    private JButton oppositeButton,oppositebinaryTreeButton;
    private JButton binaryTreeButton;
    private TreePanel treePanel;
    private JScrollPane scrollPane;
    private int maxNumber,number;
    Node root,opposite;
    String Ofile,Rfile;
    public oppositeRectangleFrame(Node root,int ans , String Ofile,String Rfile,Node opposite,int number) {
        this.number=number;
        this.Ofile=Ofile;
        this.Rfile=Rfile;
        this.opposite=opposite;
        this.root=root;
        this.maxNumber=ans;
        setTitle("Opposite Rectangle");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel controlPanel = new JPanel();
        oppositeButton = new JButton("Rectangle");
        binaryTreeButton = new JButton("Binary Tree");
        oppositebinaryTreeButton = new JButton("opposite Binary Tree");
        oppositeButton.addActionListener(this);
        binaryTreeButton.addActionListener(this);
        oppositebinaryTreeButton.addActionListener(this);
        controlPanel.add(oppositebinaryTreeButton);
        controlPanel.add(binaryTreeButton);
        controlPanel.add(oppositeButton);
        treePanel = new TreePanel(Rfile);
        scrollPane = new JScrollPane(treePanel);
        scrollPane.setPreferredSize(new Dimension(3000, 3000));
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == oppositebinaryTreeButton) {
            oppositebinaryTreeFrame oppositee = new oppositebinaryTreeFrame(root,maxNumber,Ofile,Rfile,opposite,number);
            this.dispose();
        } else if (e.getSource() == binaryTreeButton) {
            binaryTreeFrame binartree = new binaryTreeFrame(root,maxNumber,Ofile,Rfile,opposite,number);
            this.dispose();
        } else if (e.getSource()==oppositeButton) {
            RectangleFrame opp = new RectangleFrame(root,maxNumber, Ofile,Rfile,opposite,number);
            this.dispose();
        }
    }
}
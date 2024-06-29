import java.io.*;
import java.util.*;

public class Main {
    public static void preOrder(Node root) {
        if (root == null)
            return;
        preOrder(root.left);
        System.out.println(root.c + " " + root.height + " " + root.width);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        int number = 0;

        binaryTree tree = new binaryTree();
//           "((((A[60,40]-B[60,20])|C[20,60])-((D[50,30]-((E[25,20]-F[25,20])|G[25,40]))|((H[20,40]|I[10,40])-(J[30,15]-K[30,15]))))|(L[20,65]-M[20,65]))";
        // "(A[10,20]-(B[10,20]-C[10,30]))|(D[50,30]-(E[30,40]|F[20,40]))";
//         "(A[20,10]|(B[20,10]|C[30,10]))-(D[30,50]|(E[40,30]-F[40,20]))";
        String input = tree.Import();
        Node root = tree.ConvertToBinaryTree(input);
        tree.ConvertToExpression(root);
        String w = tree.expreesion;
        for (int i = 0; i < w.length(); i++)
            if (w.charAt(i) >= 'A' && w.charAt(i) <= 'Z')
                number++;
        File Orginalfile = new File("C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\Rect.txt");
        tree.writeRectangleOnFile(root, Orginalfile);

        //to convert rotateRectangle to binaryTree and export RotateRectangle
        tree.RotateRectangle(root);
        String rotate = tree.rotateRectangle;
        Node rotateRoot = tree.ConvertToBinaryTree(rotate);
        File rotateFile = new File("C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\RotateRectangle.txt");
        tree.writeRectangleOnFile(rotateRoot, rotateFile);

        // to import rectangle from file and convert it to binaryTree
        readRectangle rectangle = new readRectangle();
        rectangle.moveRectangleToArray();
        String input2 = rectangle.transfusingOne(rectangle.rectangle);
        System.out.println(input2);

        // number of rectangles
        char[] options = {'|', '-'};
        List<Rectangle> rectangles = NumberOfRectangles.readInputFromFile();
        System.out.println(rectangles.size());
        if (rectangles == null) {
            System.out.println("Failed to read input from file.");
            return;
        }

        NumberOfRectangles num = new NumberOfRectangles(rectangles, options);
        num.processInput();
        new RectangleFrame(root, num.getValidCount(),"C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\Rect.txt","C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\RotateRectangle.txt",rotateRoot ,number);
    }
}
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GenericTree tree = new GenericTree();
        binaryTree binaryTree = new binaryTree();
        File scr;

        // import genericTree from extended_tree and export binaryTree
        scr = new File("C:\\Users\\ASUS\\IdeaProjects\\algorithm2\\src\\extended_tree.txt");
        tree.importGenericTree(scr);
        int number = tree.getnumber();
        binaryTree = GenericTree.GenericToBinary(tree.root);
        binaryTree.exportBinaryTree(binaryTree);
        GenericTree.PreOrderGenericTree(tree.root);
        new DrawG(binaryTree, tree.root, number);


//       scr= new File("C:\\Users\\ASUS\\IdeaProjects\\algorithm2\\src\\binary_tree.txt");
//       binaryTree=binaryTree.importBinaryTree(scr);
//       tree.root=tree.BinaryToGeneric(binaryTree);
//       tree.exportGenericTree(tree.root,"C:\\Users\\ASUS\\IdeaProjects\\algorithm2\\src\\extended_tree.txt");
//       tree = new GenericTree();
//

    }
}
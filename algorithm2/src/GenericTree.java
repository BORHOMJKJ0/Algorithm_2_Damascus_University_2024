import java.io.*;
import java.util.*;

class GenericNodes {
    char c;
    List<GenericNodes> childrens;

    public GenericNodes(char c) {
        this.c = c;
        this.childrens = new ArrayList<>();
    }
}

public class GenericTree {
    protected GenericNodes root = null;

    Scanner in;
    int number = 0;

    int getnumber() {
        return number;
    }

    protected void importGenericTree(File scr) {
        try {
            in = new Scanner(scr);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int num = 0;
        while (in.hasNextLine()) {
            readETree(in.nextLine(), num);
            num++;
        }
    }

    protected GenericNodes insert(GenericNodes root, char parent, GenericNodes node) {
        if (root == null) {
            this.root = node;
            return node;
        } else {
            if (root.c == parent) {
                root.childrens.add(node);
                return node;
            } else {
                for (int i = 0; i < root.childrens.size(); i++) {
                    GenericNodes child = root.childrens.get(i);
                    GenericNodes insertedNode = insert(child, parent, node);
                    if (insertedNode != null) {
                        return insertedNode;
                    }
                }
            }
        }
        return null;
    }

    protected void readETree(String str, int num) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) <= 'Z' && str.charAt(i) >= 'A') {
                if (i == 0 && num == 0) {
                    number++;
                    insert(root, '0', new GenericNodes(str.charAt(i)));
                } else if (num >= 0 && i > 0) {
                    number++;
                    insert(root, str.charAt(0), new GenericNodes(str.charAt(i)));
                }
            }
        }
    }
    public static void PreOrderGenericTree(GenericNodes node) {
        if (node == null || node.childrens.size() == 0) {
            return;
        }

        for (int i = 0; i < node.childrens.size(); i++) {
            GenericNodes child = node.childrens.get(i);
            System.out.print(child.c + " ");
            PreOrderGenericTree(child);
        }
    }

    public static binaryTree GenericToBinary(GenericNodes node) {
        if (node == null)
            return null;

        binaryTree tree = new binaryTree(node.c);

        if (node.childrens.size() > 0) {
            int size = node.childrens.size() - 1;
            tree.right = GenericToBinary(node.childrens.get(size));
        }

        binaryTree cur = tree.right;

        for (int i = node.childrens.size() - 2; i >= 0; i--) {
            GenericNodes child = node.childrens.get(i);
            cur.left = GenericToBinary(child);
            cur = cur.left;
        }

        return tree;
    }

    public GenericNodes BinaryToGeneric(binaryTree node) {
        if (node == null) {
            return null;
        }

        GenericNodes rootTree = new GenericNodes(node.c);
        List<GenericNodes> childrenList = new ArrayList<>();

        binaryTree cur = node.right;

        while (cur != null) {
            GenericNodes child = BinaryToGeneric(cur);
            childrenList.add(child);
            cur = cur.left;
        }

        Collections.reverse(childrenList);
        rootTree.childrens.addAll(childrenList);
        return rootTree;
    }

    public void exportGenericTree(GenericNodes root, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        Map<GenericNodes, Boolean> visited = new HashMap<>();
        writeNode(root, writer, visited);
        writer.close();
    }

    public void writeNode(GenericNodes node, BufferedWriter writer, Map<GenericNodes, Boolean> visited) throws IOException {
        if (node == null || visited.containsKey(node)) {
            return;
        }
        visited.put(node, true);
        if (node.childrens.size() > 0) {
            writer.write(node.c + "->");
            for (int i = 0; i < node.childrens.size(); i++) {
                writer.write(node.childrens.get(i).c);
                if (i < node.childrens.size() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
            for (GenericNodes child : node.childrens) {
                writeNode(child, writer, visited);
            }
        }
    }
}



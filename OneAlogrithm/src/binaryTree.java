import java.io.*;

public class binaryTree {
    Node root;
    char[][] grid;

    String expreesion = "";
    String rotateRectangle = "";

    public String Import() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\importExpression.txt"));
            return reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void ConvertToExpression(Node node) {
        if (node.left == null && node.right == null) {
            if (node.c <= 'Z' && node.c >= 'A') {
                expreesion += node.c;

                expreesion += "[" + node.width + ',' + node.height + "]";
            }
            return;
        }
        expreesion += '(';
        ConvertToExpression(node.left);
        expreesion += node.c;
        ConvertToExpression(node.right);
        expreesion += ')';
    }


    public void RotateRectangle(Node node) {
        if (node.left == null && node.right == null) {
            if (node.c <= 'Z' && node.c >= 'A') {
                rotateRectangle += node.c;

                rotateRectangle += "[" + node.height + ',' + node.width + "]";
            }
            return;
        }

        rotateRectangle += '(';
        RotateRectangle(node.left);
        if (node.c == '|')
            rotateRectangle += '-';
        else
            rotateRectangle += '|';
        RotateRectangle(node.right);
        rotateRectangle += ')';
    }

    public Node ConvertToBinaryTree(String word) {
        return insertRec(word);
    }

    private Node insertRec(String subtree) {
        if (subtree.isEmpty()) {
            return null;
        }

        int x = 0;
        int index = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < subtree.length(); i++) {
            char ch = subtree.charAt(i);
            if (ch == '(') {
                x++;
            } else if (ch == ')') {
                x--;
            } else if (ch == '-' || ch == '|') {
                if (min > x) {
                    min = x;
                    index = i;
                }
            }
        }

        if (index == -1) {
            for (int i = 0; i < subtree.length(); i++) {
                if (subtree.charAt(i) >= 'A' && subtree.charAt(i) <= 'Z') {
                    Node root = new Node(subtree.charAt(i));
                    String num = "";
                    for (int j = i + 1; j < subtree.length(); j++) {
                        char ch = subtree.charAt(j);
                        if (ch == ']') {
                            root.height = Integer.parseInt(num);
                            break;
                        } else if (ch == ',') {
                            root.width = Integer.parseInt(num);
                            num = "";
                        } else if (ch != '[') {
                            num += ch;
                        }
                    }
                    return root;
                }
            }
        } else {
            Node root = new Node(subtree.charAt(index));
            String left = subtree.substring(0, index);
            String right = subtree.substring(index + 1);
            root.left = insertRec(left);
            root.right = insertRec(right);
            if (root.c == '-') {
                root.width = root.left.width;
                root.height = root.left.height + root.right.height;
            } else {
                root.height = root.left.height;
                root.width = root.left.width + root.right.width;
            }
            return root;
        }
        return null;
    }


    public void drawRectangle(Node node, int x, int y) {
        if (node.c >= 'A' && node.c <= 'Z') {
            placeRectangleInGrid(node, x, y);
        }
        if (node.c == '|') {
            drawRectangle(node.left, x, y);
            drawRectangle(node.right, x, y + node.left.width);

        } else if (node.c == '-') {
            drawRectangle(node.left, x, y);
            drawRectangle(node.right, x + node.left.height, y);
        }

    }

    public void placeRectangleInGrid(Node node, int x, int y) {

        for (int i = x; i < x + node.height; i++) {
            grid[i][y] = '|';
            grid[i][y + node.width - 1] = '|';
            for (int j = y + 1; j < y + node.width - 1; j++) {
                grid[i][j] = ' ';
            }
        }

        for (int j = y; j < y + node.width; j++) {
            grid[x + node.height - 1][j] = '-';
        }

        int midX = x + node.height / 2;
        int midY = y + node.width / 2;
        grid[midX][midY] = node.c;

    }

    public void writeRectangleOnFile(Node root, File file) {
        this.grid = new char[root.height][root.width];

        drawRectangle(root, 0, 0);
        for (int i = 0; i < root.width; i++) {
            grid[0][i] = '-';
        }

        for (char[] row : grid)
            for (int i = 0; i < row.length; i++) {
                if (i + 1 != row.length) {
                    if (row[i] == row[i + 1] && row[i] == '|')
                        row[i + 1] = ' ';
                    if (row[i] == '-' && row[i + 1] == '|') {
                        row[i] = row[i + 1];
                        row[i + 1] = ' ';
                    }
                }
            }
        try (BufferedWriter write = new BufferedWriter(new FileWriter(file))) {
            for (char[] row : grid) {
                for (char c : row) {
                    write.write(c);
                }
                write.newLine();
            }
            write.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

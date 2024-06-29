import java.io.*;
import java.util.*;

class binaryTree {
    char c;
    binaryTree left, right;

    public binaryTree() {
        left = null;
        right = null;
    }

    public binaryTree(char c) {
        this.c = c;
        left = null;
        right = null;
    }

    Scanner in;

    public binaryTree importBinaryTree(File fileName) throws IOException {
        List<List<Character>> levels = new ArrayList<>();
        in = new Scanner(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> level = new ArrayList<>();
                for (String s : line.split(",")) {
                    level.add(s.trim().charAt(0));
                }
                levels.add(level);
            }
        }

        if (levels.isEmpty()) {
            return null;
        }

        Queue<binaryTree> queue = new LinkedList<>();
        binaryTree root = new binaryTree(levels.get(0).get(0));
        queue.add(root);

        int index = 1;
        while (index < levels.size() && !queue.isEmpty()) {
            List<Character> level = levels.get(index);
            int levelIndex = 0;
            int levelSize = level.size();
            while (levelIndex < levelSize && !queue.isEmpty()) {
                binaryTree node = queue.poll();
                if (levelIndex < levelSize && level.get(levelIndex) != '!') {
                    node.left = new binaryTree(level.get(levelIndex));
                    queue.add(node.left);
                }
                levelIndex++;
                if (levelIndex < levelSize && level.get(levelIndex) != '!') {
                    node.right = new binaryTree(level.get(levelIndex));
                    queue.add(node.right);
                }
                levelIndex++;
            }
            index++;
        }

        return root;
    }

    public int height(binaryTree root) {

        int r = 0, l = 0;
        if (root.right != null)
            r = height(root.right);
        if (root.left != null)
            l = height(root.left);
        return 1 + Math.max(r, l);
    }

    public void exportBinaryTree(binaryTree root) {
        if (root == null) {
            return;
        }
        int l = 0, high = height(root);
        ArrayList<ArrayList<String>> levelData = new ArrayList<>();
        Queue<binaryTree> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            ArrayList<String> currentLevelData = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                binaryTree node = queue.poll();
                if (node != null) {
                    currentLevelData.add(String.valueOf(node.c));
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    currentLevelData.add("!");
                }
            }
            levelData.add(currentLevelData);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ASUS\\IdeaProjects\\algorithm2\\src\\binary_tree.txt", false))) {
            for (ArrayList<String> level : levelData) {
                StringBuilder levelString = new StringBuilder();
                for (int i = 0; i < level.size(); i++) {
                    if (i > 0) {
                        levelString.append(",");
                    }
                    levelString.append(level.get(i));
                }
                if (l <= high) {
                    if (levelString.isEmpty())
                        writer.write("!,!");
                    else
                        writer.write(levelString.toString());
                    writer.newLine();
                }
                l++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
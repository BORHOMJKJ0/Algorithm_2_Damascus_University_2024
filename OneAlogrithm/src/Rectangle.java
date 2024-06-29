import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Rectangle {
    char c;
    int width, height, area;

    Rectangle(char c, int width, int height) {
        this.c = c;
        this.width = width;
        this.height = height;
        this.area = width * height;
    }

    public char getC() {
        return c;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return area;
    }

    static int totalArea = 0;
    static List<Rectangle> rectangles = new ArrayList<>();

    public static void readRectanglesFormFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("[\\[\\],]");
                    char letter = parts[0].charAt(0);
                    int width = Integer.parseInt(parts[1]);
                    int height = Integer.parseInt(parts[2]);
                    rectangles.add(new Rectangle(letter, width, height));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        readRectanglesFormFile();
//        new Rectangle('A',6,4);
//        new Rectangle('B',6,2);
//        new Rectangle('C',2,6);
//        new Rectangle('D',5,3);
//        new Rectangle('E',3,2);
//        new Rectangle('F',3,2);
//        new Rectangle('G',2,4);
//        new Rectangle('H',2,4);
//        new Rectangle('I',1,4);
//        new Rectangle('J',3,2);
//        new Rectangle('K',3,1);
//        new Rectangle('L',2,7);
//        new Rectangle('M',2,6);


        for (Rectangle rectangle : rectangles) {
            totalArea += rectangle.width * rectangle.height;
        }

        boolean canFormBigRectangle = false;

        for (int width = 1; width * width <= totalArea; width++) {
            if (totalArea % width == 0) {
                int height = totalArea / width;
                if (canFormRectangle(width, height)) {
                    canFormBigRectangle = true;
                    break;
                }
            }
            if (canFormBigRectangle == true) break;
        }
        System.out.println(canFormBigRectangle);
    }

    public static boolean canFormRectangle(int bigWidth, int bigHeight) {
        char[][] grid = new char[bigWidth][bigHeight];

        for (int i = 0; i < bigWidth; i++) {
            for (int j = 0; j < bigHeight; j++) {
                grid[i][j] = '*';
            }
        }

        boolean x = TryToFormRectangle(0, grid, bigWidth, bigHeight);
        if (x == true) {
            Export(grid);
//            for (int i = 0; i < grid.length; i++) {
//                for (int j = 0; j < grid[i].length; j++) {
//                    System.out.print(grid[i][j] + " ");
//                }
//                System.out.println();
//            }
        }else{
            try{
                BufferedWriter write = new BufferedWriter(new FileWriter("C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\r.txt"));
                write.write("");
                write.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return x;
    }

    public static boolean TryToFormRectangle(int index, char[][] grid, int bigWidth, int bigHeight) {
        if (index == rectangles.size()) {
            return true;
        }

        Rectangle currentRectangle = rectangles.get(index);

        for (int i = 0; i < bigWidth; i++) {
            for (int j = 0; j < bigHeight; j++) {
                if (canPutRectangleInPosition(grid, i, j, currentRectangle.width, currentRectangle.height, bigWidth, bigHeight)) {
                    placeRectangle(grid, i, j, currentRectangle.width, currentRectangle.height, currentRectangle.c);
                    if (TryToFormRectangle(index + 1, grid, bigWidth, bigHeight)) {
                        return true;
                    }
                    removeRectangle(grid, i, j, currentRectangle.width, currentRectangle.height);
                }
                if (canPutRectangleInPosition(grid, i, j, currentRectangle.height, currentRectangle.width, bigWidth, bigHeight)) {
                    placeRectangle(grid, i, j, currentRectangle.height, currentRectangle.width, currentRectangle.c);
                    if (TryToFormRectangle(index + 1, grid, bigWidth, bigHeight)) {
                        return true;
                    }
                    removeRectangle(grid, i, j, currentRectangle.height, currentRectangle.width);
                }
            }
        }

        return false;
    }

    public static boolean canPutRectangleInPosition(char[][] grid, int x, int y, int width, int height, int bigWidth, int bigHeight) {
        if (x + width > bigWidth || y + height > bigHeight) {
            return false;
        }

        for (int i = x; i < bigWidth; i++) {
            for (int j = y; j < bigHeight; j++) {
                if (grid[i][j] != '*') return false;
            }
        }
        return true;
    }

    public static void placeRectangle(char[][] grid, int x, int y, int width, int height, char c) {

        boolean ok = false;
        for (int i = x; i < x + width; i++) {
            grid[i][y] = '|';
            grid[i][y + height - 1] = '|';
            for (int j = y + 1; j < y + height - 1; j++) {
                if (ok == false) {
                    grid[i][j] = c;
                    ok = true;
                } else
                    grid[i][j] = ' ';
            }
        }

        for (int j = y; j < y + height; j++) {
            grid[x + width - 1][j] = '-';
        }

    }

    public static void removeRectangle(char[][] grid, int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                grid[i][j] = '*';
            }
        }
    }

    public static void Export(char[][] grid) {
        String line = "";
        for (int i = 0; i < grid[0].length; i++) {
            line += '-';
        }
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\r.txt"));
            write.write(line);
            write.newLine();
            for (char[] i : grid) {
                for (char j : i) {
                    write.write(j);
                }
                write.newLine();
            }
            write.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

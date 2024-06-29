import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readRectangle {
    char rectangle[][];
    protected int colum;
    protected int row;
    protected Scanner rec;
    protected Scanner readToArray;
    protected String ans = "";
    protected File rectangleFile = new File("C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\input_rectangle.txt");

    public readRectangle() {
        try {
            rec = new Scanner(rectangleFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.colum = 0;
        String str = null;
        if (rec.hasNextLine()) {
            str = rec.nextLine();
        }
        colum = str.length();
        this.row = 1;
        while (rec.hasNextLine()) {
            String tmp = rec.nextLine();
            this.row++;
        }
        rectangle = new char[this.row][this.colum];
    }


    public void moveRectangleToArray() {
        try {
            readToArray = new Scanner(rectangleFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String tump = null;
        int cunt = 0;
        while (readToArray.hasNextLine()) {
            tump = readToArray.nextLine();
            int i;
            for (i = 0; i < tump.length(); i++) {
                rectangle[cunt][i] = tump.charAt(i);
            }
            cunt++;
        }

    }


    public String transfusingOne(char[][] rectangle) {
        char[][] left;
        char[][] right;
        for (int i = 1; i < rectangle.length - 1; i++) {
            if (rectangle[i][0] == '-')
                for (int j = 0; j < rectangle[0].length - 1; j++) {
                    if (rectangle[i][j] != '-') {
                        break;
                    } else if (j == rectangle[0].length - 2) {
                        left = new char[i - 1][rectangle[0].length];
                        right = new char[rectangle.length - (i + 1)][rectangle[0].length];
                        for (int f = 0; f < i - 1; f++) {
                            for (int k = 0; k < rectangle[0].length; k++)
                                left[f][k] = rectangle[f][k];
                        }
                        for (int z = i + 1; z < rectangle.length; z++) {
                            for (int e = 0; e < rectangle[0].length; e++)
                                right[z - (i + 1)][e] = rectangle[z][e];
                        }
                        ans += "(";
                        String l = transfusingOne(left);
                        ans += "-";
                        String r = transfusingOne(right);
                        ans += ")";
                        return ans;
                    }
                }
        }
        for (int j = 1; j < rectangle[0].length - 1; j++) {
            if (rectangle[1][j] == '|')
                for (int i = 1; i < rectangle.length - 1; i++) {
                    if (rectangle[i][j] != '|') {
                        break;
                    } else if (i == rectangle.length - 2) {

                        left = new char[rectangle.length][j - 1];
                        right = new char[rectangle.length][rectangle[0].length - (j + 1)];
                        for (int f = 0; f < left.length; f++) {
                            for (int k = 0; k < left[0].length; k++)
                                left[f][k] = rectangle[f][k];
                        }
                        for (int z = 0; z < rectangle.length; z++) {
                            for (int e = j + 1; e < rectangle[0].length; e++)
                                right[z][e - (j + 1)] = rectangle[z][e];
                        }
                        ans += "(";
                        String l = transfusingOne(left);
                        ans += "|";
                        String r = transfusingOne(right);
                        ans += ")";
                        return ans;
                    }
                }
        }

        for (int i = 0; i < rectangle.length; i++) {
            for (int j = 0; j < rectangle[0].length; j++) {
                if (rectangle[i][j] >= 'A' && rectangle[i][j] <= 'Z') {
                    ans += rectangle[i][j];
                    int x = 0, y = 0;
                    if (rectangle[0].length % 5 != 0)
                        x += 2;
                    if (rectangle.length % 5 != 0)
                        y += 2;
                    ans += "[" + (rectangle[0].length+x) + "," + (rectangle.length+y) + "]";

                }
            }
        }
        return ans;
    }
}

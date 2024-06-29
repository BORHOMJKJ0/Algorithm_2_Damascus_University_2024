import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NumberOfRectangles {
    private List<Rectangle> rectangles;
    private char[] options;
    private int validCount;

    public NumberOfRectangles(List<Rectangle> rectangles, char[] options) {
        this.rectangles = rectangles;
        this.options = options;
        this.validCount=0;
    }
    public int getValidCount()
    {
        return validCount;
    }
    public void processInput() {
        List<String> allCombinations = generateAllCombinations(rectangles, options);

        List<String> validCombinations = new ArrayList<>();

        for (String combination : allCombinations) {
            List<String> bracketedExpressions = generateAllBracketings(combination);
            for (String bracketedExpression : bracketedExpressions) {
                boolean b=true;
                if(validCombinations.size()!=0)
                {
                    for(String i : validCombinations)
                        if(i.equals(bracketedExpression))
                            b=false;
                }
                if(bracketedExpression.length()==1)
                    validCount++;
                else if (isValidRectangle(bracketedExpression, rectangles) && b) {
                    validCount++;
                    validCombinations.add(bracketedExpression);
                }
            }
        }



        System.out.println("Number of valid combinations: " + validCount);
        if (validCount > 0) {
            System.out.println("Valid combinations:");
            for (String validCombination : validCombinations) {
                System.out.println(validCombination);
            }
        }
    }

    public  List<String> generateAllCombinations(List<Rectangle> rectangles, char[] options) {
        List<String> allCombinations = new ArrayList<>();
        List<String> permutations = generatePermutations(rectangles);

        for (String permutation : permutations) {
            List<String> combinations = generateCombinations(permutation, options);
            allCombinations.addAll(combinations);
        }

        return allCombinations;
    }

    public  List<String> generateCombinations(String input, char[] options) {
        List<String> combinations = new ArrayList<>();
        generateCombinationsHelper(input, options, 0, new StringBuilder(), combinations);
        return combinations;
    }

    private  void generateCombinationsHelper(String input, char[] options, int index, StringBuilder current, List<String> combinations) {
        if (index == input.length() - 1) {
            current.append(input.charAt(index));
            combinations.add(current.toString());
            current.deleteCharAt(current.length() - 1);
            return;
        }

        current.append(input.charAt(index));

        for (char option : options) {
            current.append(option);
            generateCombinationsHelper(input, options, index + 1, current, combinations);
            current.deleteCharAt(current.length() - 1);
        }

        current.deleteCharAt(current.length() - 1);
    }

    private  List<String> generatePermutations(List<Rectangle> rectangles) {
        List<String> permutations = new ArrayList<>();
        boolean[] used = new boolean[rectangles.size()];
        StringBuilder current = new StringBuilder();
        generatePermutationsHelper(rectangles, used, current, permutations);
        return permutations;
    }

    private  void generatePermutationsHelper(List<Rectangle> rectangles, boolean[] used, StringBuilder current, List<String> permutations) {
        if (current.length() == rectangles.size()) {
            permutations.add(current.toString());
            return;
        }

        for (int i = 0; i < rectangles.size(); i++) {
            if (used[i]) continue;
            used[i] = true;
            current.append(rectangles.get(i).getC());
            generatePermutationsHelper(rectangles, used, current, permutations);
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }

    private  boolean isValidRectangle(String combination, List<Rectangle> rectangles) {
        char a='a';
        Stack<Character> parenthesesStack = new Stack<>();
        List<Rectangle>R=new ArrayList<>();
        for(Rectangle i:rectangles)
            R.add(i);
        for (int i = 0; i<combination.length(); i++) {
            if(combination.charAt(i)!=')')
                parenthesesStack.push(combination.charAt(i));
            else {
                Rectangle rect1 = getRectangleByLetter(R, parenthesesStack.pop());
                char latter2 = parenthesesStack.pop();
                Rectangle rect2 = getRectangleByLetter(R, parenthesesStack.pop());
                parenthesesStack.pop();
                if (latter2 == '-') {
                    if (rect2.getWidth() == rect1.getWidth()) {
                        Rectangle rectangle = new Rectangle(a, rect1.getWidth(), rect1.getHeight() + rect2.getHeight());
                        R.add(rectangle);
                        parenthesesStack.add(a);
                        a++;
                    }
                    else
                        return false;
                }
                else if (latter2 == '|') {
                    if(rect1.getHeight()==rect2.getHeight()){
                        Rectangle rectangle=new Rectangle(a,rect1.getWidth()+rect2.getWidth(),rect2.getHeight());
                        R.add(rectangle);
                        parenthesesStack.add(a);
                        a++;
                    }
                    else
                        return false;
                }
            }}
        return true;
    }

    private  Rectangle getRectangleByLetter(List<Rectangle> rectangles, char letter) {
        for (Rectangle rect : rectangles) {
            if (rect.getC() == letter) {
                return rect;
            }
        }
        return null;
    }

    public  List<String> generateAllBracketings(String combination) {
        List<String> result = new ArrayList<>();
        if (combination.length() == 1) {
            result.add(combination);
            return result;
        }

        for (int i = 1; i < combination.length(); i += 2) {
            char operator = combination.charAt(i);
            String left = combination.substring(0, i);
            String right = combination.substring(i + 1);

            List<String> leftBracketings = generateAllBracketings(left);
            List<String> rightBracketings = generateAllBracketings(right);

            for (String l : leftBracketings) {
                for (String r : rightBracketings) {
                    result.add("(" + l + operator + r + ")");
                }
            }
        }

        return result;
    }
    public static List<Rectangle> readInputFromFile() {
        String filename="C:\\Users\\ASUS\\IdeaProjects\\OneAlogrithm\\src\\input.txt";
        List<Rectangle> rectangles = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
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
            return null;
        }
        return rectangles;
    }}

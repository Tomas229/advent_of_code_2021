import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

class Main {
  public static Map<Character, Character> map = new HashMap<Character, Character>();
  public static Map<Character, Integer> score = new HashMap<Character, Integer>();


  public static void main(String[] args) throws IOException {
    List<String> input = Main.getMultiLineInputStringArray();

    Main.map.put(')', '(');
    Main.map.put(']', '[');
    Main.map.put('}', '{');
    Main.map.put('>', '<');

    Main.score.put(')', 3);
    Main.score.put(']', 57);
    Main.score.put('}', 1197);
    Main.score.put('>', 25137);

    Main.printFirstStarSolution(input);
    // Main.printSecondStarSolution(input);
  }

  public static int[] getOneLineInputIntArray() throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    // System.out.println(input);
    return Stream.of(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
  }

  public static List<String> getMultiLineInputStringArray() throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    // System.out.println(input);
    return input;
  }

  public static ArrayList<ArrayList<Integer>> getIntMatrixList() throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

    for (int i = 0; i < input.size(); i++) {
      ArrayList<Integer> aux = new ArrayList<Integer>();
      int[] nums = Stream.of(input.get(i).split("")).mapToInt(Integer::parseInt).toArray();
      for (int n : nums) {
        aux.add(n);
      }

      matrix.add(aux);
    }
    // System.out.println(matrix);
    return matrix;
  }

  public static void printFirstStarSolution(List<String> init) {
    int sum = 0;

    for (String line : init) {
      List<Character> actual = new ArrayList<Character>();
      actual.add(line.charAt(0));

      for (int i = 1, n = line.length(); i < n; i++) {
        char c = line.charAt(i);
        if (c == '(' || c == '[' || c == '{' || c == '<') {
          actual.add(c);
        } else {
          if (map.get(c) == actual.get(actual.size() - 1)) {
            actual.remove(actual.size() - 1);
          } else {
            sum = sum + score.get(c);
            break;
          }
        }
      }
    }

    System.out.println(sum);
  }

  public static void printSecondStarSolution(List<String> init) {
  }
}

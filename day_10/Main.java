import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

class Main {
  public static Map<Character, Character> map = new HashMap<Character, Character>();
  public static Map<Character, Character> reverseMap = new HashMap<Character, Character>();
  public static Map<Character, Integer> corruptedScore = new HashMap<Character, Integer>();

  public static Map<Character, Integer> incompleteScore = new HashMap<Character, Integer>();

  public static void main(String[] args) throws IOException {
    List<String> input = Main.getMultiLineInputStringArray();

    Main.map.put(')', '(');
    Main.map.put(']', '[');
    Main.map.put('}', '{');
    Main.map.put('>', '<');

    Main.reverseMap.put('(', ')');
    Main.reverseMap.put('[', ']');
    Main.reverseMap.put('{', '}');
    Main.reverseMap.put('<', '>');

    Main.corruptedScore.put(')', 3);
    Main.corruptedScore.put(']', 57);
    Main.corruptedScore.put('}', 1197);
    Main.corruptedScore.put('>', 25137);

    Main.incompleteScore.put(')', 1);
    Main.incompleteScore.put(']', 2);
    Main.incompleteScore.put('}', 3);
    Main.incompleteScore.put('>', 4);

    Main.printFirstStarSolution(input);
    Main.printSecondStarSolution(input);
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
      sum = sum + Main.getCorruptedScore(line);
    }

    System.out.println(sum);
  }

  public static void printSecondStarSolution(List<String> init) {
    List<Long> points = new ArrayList<Long>();
    for (String line : init) {
      long score = Main.getIncompleteScore(line);
      if (score != 0) {
        points.add(score);
      }
    }
    Collections.sort(points);
    System.out.println(points.get(points.size() / 2));
  }

  public static int getCorruptedScore(String line) {
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
          return corruptedScore.get(c);
        }
      }
    }
    return 0;
  }

  public static Long getIncompleteScore(String line) {
    List<Character> actual = new ArrayList<Character>();
    actual.add(line.charAt(0));

    Long sum = 0L;

    for (int i = 1, n = line.length(); i < n; i++) {
      char c = line.charAt(i);
      if (c == '(' || c == '[' || c == '{' || c == '<') {
        actual.add(c);
      } else {
        if (map.get(c) == actual.get(actual.size() - 1)) {
          actual.remove(actual.size() - 1);
        } else {
          return 0L;
        }
      }
    }
    for (int i = 0; i < actual.size(); i++) {
      sum = sum * 5;
      char r = Main.reverseMap.get(actual.get(actual.size() - i - 1));
      sum = sum + Main.incompleteScore.get(r);
    }
    return sum;
  }
}

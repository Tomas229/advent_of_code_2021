import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> input = Main.getMultiLineInputStringArray();

    List<String[]> outputPuzzle = new ArrayList<String[]>();

    int count = 0;

    for (int i = 0; i < input.size(); i++) {
      String[] aux = input.get(i).split("\\|")[1].substring(1).split(" ");
      outputPuzzle.add(aux);
      for (String s : aux) {
        if ((1 < s.length() && s.length() < 5) || s.length() == 7) {
          count++;
        }
      }

    }
    System.out.println(count);

    // Main.printFirstStarSolution(input);
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

  public static void printFirstStarSolution(int[] init) {

  }

  public static void printSecondStarSolution(int[] init) {
  }
}

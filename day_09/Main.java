import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<Integer>> input = Main.getIntMatrixList();

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
    System.out.println(input);
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

  public static ArrayList<int[]> getAdyacentIndexes(int i, int j, int heigth, int width) {
    int[] up = { i - 1, j };
    int[] down = { i + 1, j };
    int[] left = { i, j - 1 };
    int[] right = { i, j + 1 };

    ArrayList<int[]> indexes = new ArrayList<int[]>();

    if (i != 0) {
      indexes.add(up);
    }
    if (i != heigth - 1) {
      indexes.add(down);
    }
    if (j != 0) {
      indexes.add(left);
    }
    if (j != width - 1) {
      indexes.add(right);
    }
    return indexes;
  }

  public static void printFirstStarSolution(ArrayList<ArrayList<Integer>> init) {
    int sum = 0;
    for (int i = 0; i < init.size(); i++) {
      for (int j = 0; j < init.get(i).size(); j++) {

        ArrayList<int[]> indexes = Main.getAdyacentIndexes(i, j, init.size(), init.get(i).size());
        boolean low = true;

        for (int k = 0; k < indexes.size(); k++) {
          low = low && (init.get(i).get(j) < init.get(indexes.get(k)[0]).get(indexes.get(k)[1]));
        }

        if (low) {
          sum = sum + 1 + init.get(i).get(j);
        }
      }
    }
    System.out.println(sum);
  }

  public static void printSecondStarSolution(int[] init) {
  }
}

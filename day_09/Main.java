import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<Integer>> input = Main.getIntMatrixList();

    Main.printSecondStarSolution(Main.printFirstStarSolution(input), input);
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

  public static ArrayList<int[]> printFirstStarSolution(ArrayList<ArrayList<Integer>> init) {

    ArrayList<int[]> lowIndexes = new ArrayList<int[]>();

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
          int[] pair = { i, j };
          lowIndexes.add(pair);
        }
      }
    }
    System.out.println(sum);
    return lowIndexes;
  }

  public static void printSecondStarSolution(ArrayList<int[]> lowPoints, ArrayList<ArrayList<Integer>> map) {
    ArrayList<Integer> basinSizes = new ArrayList<Integer>();

    for (int[] low : lowPoints) {
      basinSizes.add(Main.basinSize(low[0], low[1], map));
    }
    Collections.sort(basinSizes);
    System.out.println(basinSizes.get(basinSizes.size() - 1) * basinSizes.get(basinSizes.size() - 2)
        * basinSizes.get(basinSizes.size() - 3));
  }

  public static int basinSize(int x, int y, ArrayList<ArrayList<Integer>> map) {
    ArrayList<int[]> basinList = new ArrayList<int[]>();
    int[] initial = { x, y };
    basinList.add(initial);

    for (int i = 0; i < basinList.size(); i++) {
      ArrayList<int[]> indexes = Main.getAdyacentIndexes(basinList.get(i)[0], basinList.get(i)[1], map.size(),
          map.get(0).size());
      for (int[] ind : indexes) {
        if (map.get(ind[0]).get(ind[1]) != 9 && ! Main.isInList(basinList, ind)) {
          basinList.add(ind);
        }
      }
    }

    return basinList.size();
  }

  public static boolean isInList(final List<int[]> list, final int[] candidate) {

    return list.stream().anyMatch(a -> Arrays.equals(a, candidate));
    // ^-- or you may want to use .parallelStream() here instead
  }
}

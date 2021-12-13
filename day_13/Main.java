import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;
import aoc.*;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> inputPoints = AoC.getMultiLineInputStringArray();
    inputPoints.removeIf(n -> (!n.contains(",")));

    List<String> inputFolds = AoC.getMultiLineInputStringArray();
    inputFolds.removeIf(n -> (n.contains(",")));
    inputFolds.remove(0);

    Main.printFirstStarSolution(inputPoints, inputFolds);
    // Main.printSecondStarSolution(input);
  }

  public static void printFirstStarSolution(List<String> points, List<String> folds) {
    String[] f = folds.get(0).substring(11).split("=");
    String axis = f[0];
    int value = Integer.parseInt(f[1]);

    List<String> newPoints = new ArrayList<String>();

    for (String p : points) {
      int x = Integer.parseInt(p.split(",")[0]);
      int y = Integer.parseInt(p.split(",")[1]);

      if (axis.equals("x")) {
        if (x < value) {
          newPoints.add(p);
        } else {
          int dx = Math.abs(value - x);
          x = value - dx;
          newPoints.add(Integer.toString(x) + "," + Integer.toString(y));
        }

      } else if (axis.equals("y")) {
        if (y < value) {
          newPoints.add(p);
        } else {
          int dy = Math.abs(value - y);
          y = value - dy;
          newPoints.add(Integer.toString(x) + "," + Integer.toString(y));
        }
      }
    }
    newPoints = newPoints.stream().distinct().collect(Collectors.toList());
    System.out.println("1st star: " + Integer.toString(newPoints.size()));
  }

  public static void printSecondStarSolution(ArrayList<ArrayList<Integer>> init) {

  }
}
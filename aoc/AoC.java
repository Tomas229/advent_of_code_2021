package aoc;

import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

public class AoC {
  public static int[] getOneLineInputIntArray() throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    // System.out.println(input);
    return Stream.of(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
  }

  public static List<String> getMultiLineInputStringArray() throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    //System.out.println(input);
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

  public static ArrayList<int[]> getAllAdyacentIndexes(int i, int j, int heigth, int width) {
    int[] up = { i - 1, j };
    int[] down = { i + 1, j };
    int[] left = { i, j - 1 };
    int[] right = { i, j + 1 };
    int[] upRight = { i - 1, j + 1 };
    int[] upLeft = { i - 1, j - 1 };
    int[] downRight = { i + 1, j + 1 };
    int[] downLeft = { i + 1, j - 1 };

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
    if (i != 0 && j != width - 1) {
      indexes.add(upRight);
    }
    if (i != 0 && j != 0) {
      indexes.add(upLeft);
    }
    if (i != heigth - 1 && j != width - 1) {
      indexes.add(downRight);
    }
    if (i != heigth - 1 && j != 0) {
      indexes.add(downLeft);
    }
    return indexes;
  }

  public static boolean isInList(final List<int[]> list, final int[] candidate) {

    return list.stream().anyMatch(a -> Arrays.equals(a, candidate));
  }
}
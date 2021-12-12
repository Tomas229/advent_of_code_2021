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
    Main.printSecondStarSolution(input);
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

  public static void printFirstStarSolution(ArrayList<ArrayList<Integer>> init) {
    ArrayList<ArrayList<DumboOctopus>> matrix = new ArrayList<ArrayList<DumboOctopus>>();
    for (int i = 0; i < init.size(); i++) {
      ArrayList<DumboOctopus> row = new ArrayList<DumboOctopus>();
      for (int j = 0; j < init.get(i).size(); j++) {
        row.add(new DumboOctopus(i, j, init.get(i).get(j), matrix));
      }
      matrix.add(row);
    }

    for (int z = 0; z < 100; z++) {
      for (ArrayList<DumboOctopus> row : matrix) {
        for (DumboOctopus octopus : row) {
          octopus.readyStep();
        }
      }
      for (ArrayList<DumboOctopus> row : matrix) {
        for (DumboOctopus octopus : row) {
          octopus.takeStep();
        }
      }
    }
    int sum = 0;
    for (ArrayList<DumboOctopus> row : matrix) {
      for (DumboOctopus octopus : row) {
        sum = sum + octopus.flashes;
      }
    }
    System.out.println("1st Star: " + Integer.toString(sum));
  }

  public static void printSecondStarSolution(ArrayList<ArrayList<Integer>> init) {
    ArrayList<ArrayList<DumboOctopus>> matrix = new ArrayList<ArrayList<DumboOctopus>>();
    for (int i = 0; i < init.size(); i++) {
      ArrayList<DumboOctopus> row = new ArrayList<DumboOctopus>();
      for (int j = 0; j < init.get(i).size(); j++) {
        row.add(new DumboOctopus(i, j, init.get(i).get(j), matrix));
      }
      matrix.add(row);
    }

    int z = 0;
    while (true) {
      z = z + 1;

      int brightness = 0;

      for (ArrayList<DumboOctopus> row : matrix) {
        for (DumboOctopus octopus : row) {
          octopus.readyStep();
        }
      }
      for (ArrayList<DumboOctopus> row : matrix) {
        for (DumboOctopus octopus : row) {
          octopus.takeStep();
        }
      }
      for (ArrayList<DumboOctopus> row : matrix) {
        for (DumboOctopus octopus : row) {
          brightness = brightness + octopus.getEnergyLevel();
        }
      }
      if (brightness == 0) {
        System.out.println("2nd Star: " + Integer.toString(z));
        break;
      }
    }
    int sum = 0;
    for (ArrayList<DumboOctopus> row : matrix) {
      for (DumboOctopus octopus : row) {
        sum = sum + octopus.flashes;
      }
    }
  }
}

class DumboOctopus {
  int energyLevel;
  int x;
  int y;
  int flashes;
  boolean canFlash;
  ArrayList<ArrayList<DumboOctopus>> matrix;

  public DumboOctopus(int x, int y, int energyLevel, ArrayList<ArrayList<DumboOctopus>> matrix) {
    this.x = x;
    this.y = y;
    this.energyLevel = energyLevel;
    this.flashes = 0;
    this.matrix = matrix;
  }

  public void flash() {
    this.canFlash = false;
    this.flashes++;
    this.energyLevel = 0;
    ArrayList<int[]> indexes = Main.getAllAdyacentIndexes(this.x, this.y, this.matrix.size(),
        this.matrix.get(0).size());
    for (int[] index : indexes) {
      this.matrix.get(index[0]).get(index[1]).increaseEnergyLevel();
    }
  }

  public void increaseEnergyLevel() {
    if (this.canFlash) {
      this.energyLevel++;
      if (this.energyLevel > 9) {
        this.flash();
      }
    }
  }

  public void readyStep() {
    this.canFlash = true;
  }

  public void takeStep() {
    this.increaseEnergyLevel();
  }

  public int getEnergyLevel() {
    return this.energyLevel;
  }

  public String toString() {
    return Integer.toString(this.energyLevel);
  }
}

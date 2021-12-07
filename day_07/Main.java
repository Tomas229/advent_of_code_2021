import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

class Main {
  public int index = 0;

  public static void main(String[] args) throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    // System.out.println(input);

    int[] init = Stream.of(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

    Main.first_star(init);
    Main.second_star(init);
  }

  public static void first_star(int[] init) {
    Arrays.sort(init);

    //for (int number : init) System.out.println("Number = " + number);
    List<CrabGroup> crabs = new ArrayList<CrabGroup>();
    int[] solutions = new int[init[init.length - 1] + 1];

    crabs.add(new CrabGroup(init[0]));
    int index = 0;

    for (int i = 1; i < init.length; i++) {
      if (crabs.get(index).position == init[i]) {
        crabs.get(index).addCrab();
      } else {
        crabs.add(new CrabGroup(init[i]));
        index++;
      }
    }

    for (int j = 0; j < solutions.length; j++) {
      int aux = 0;
      for (int k = 0; k < crabs.size(); k++) {
        aux = aux + crabs.get(k).goTo(j);
      }
      solutions[j] = aux;
    }

    int min = Arrays.stream(solutions).min().orElseThrow();
    int imin = Arrays.stream(solutions).boxed().collect(Collectors.toList()).indexOf(min);
    System.out.println(min);
    System.out.println(imin);
  }

  public static void second_star(int[] init) {
  }
}

class CrabGroup {
  int position;
  int quantity;

  public CrabGroup(int position) {
    this.position = position;
    this.quantity = 1;
  }

  public void addCrab() {
    this.quantity = this.quantity + 1;
  }

  public int goTo(int position) {
    return Math.abs(this.position - position) * this.quantity;
  }

  public String toString(){
    return "("+this.position + ";" + this.quantity + ")";
  }
}
import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

class Main {
  public int index = 0;

  public static void main(String[] args) throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    // System.out.println(input);
    String[] init = input.get(0).split(",");

    Main.first_star(init);
  }

  public static void first_star(String [] init){
    List<Lanternfish> fishes = new ArrayList<Lanternfish>();
    for (int i = 0; i < init.length; i++) {
      fishes.add(new Lanternfish(Integer.parseInt(init[i])));
    }

    for (int j = 0; j < 80; j++) {
      List<Lanternfish> aux = new ArrayList<Lanternfish>();
      for (int k = 0; k < fishes.size(); k++) {
        if (fishes.get(k).newDay()) {
          aux.add(new Lanternfish());
        }
      }
      fishes.addAll(aux);
    }
    System.out.println(fishes.size());
  }
}

class Lanternfish {
  int timer;

  public Lanternfish(int n) {
    this.timer = n;
  }

  public Lanternfish() {
    this.timer = 8;
  }

  public boolean newDay() {
    if (this.timer == 0) {
      this.timer = 6;
      return true;
    }
    this.timer = (this.timer - 1);
    return false;
  }
}

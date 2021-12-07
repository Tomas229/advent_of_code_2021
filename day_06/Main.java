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
    Main.second_star(init);
  }

  public static void first_star(String[] init) {
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

  public static void second_star(String[] init) {
    int[] fishes = new int[9];
    for (int i = 0; i < init.length; i++) {
      fishes[Integer.parseInt(init[i])] = fishes[Integer.parseInt(init[i])] + 1;
    }

    Fish f = new Fish(fishes);

    for( int j = 0; j < 256; j++){
      f.newDay();
    }
    System.out.println(f.total());

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

class Fish {
  long fish_0;
  long fish_1;
  long fish_2;
  long fish_3;
  long fish_4;
  long fish_5;
  long fish_6;
  long fish_7;
  long fish_8;

  public Fish(int[] input) {
    this.fish_0 = input[0];
    this.fish_1 = input[1];
    this.fish_2 = input[2];
    this.fish_3 = input[3];
    this.fish_4 = input[4];
    this.fish_5 = input[5];
    this.fish_6 = input[6];
    this.fish_7 = input[7];
    this.fish_8 = input[8];
  }

  public void newDay() {
    long fish_0_aux = this.fish_0;
    this.fish_0 = this.fish_1;
    this.fish_1 = this.fish_2;
    this.fish_2 = this.fish_3;
    this.fish_3 = this.fish_4;
    this.fish_4 = this.fish_5;
    this.fish_5 = this.fish_6;
    this.fish_6 = this.fish_7 + fish_0_aux;
    this.fish_7 = this.fish_8;
    this.fish_8 = fish_0_aux;
  }

  public long total() {
    return this.fish_0 + this.fish_1 + this.fish_2 + this.fish_3 + this.fish_4 + this.fish_5 + this.fish_6 + this.fish_7
        + this.fish_8;
  }
}
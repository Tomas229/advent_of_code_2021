import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

class Main {
  public int index = 0;

  public static void main(String[] args) throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));

    // System.out.println(input);

    String[] numbers = input.remove(0).split(",");
    List<Bingo> bingo = new ArrayList<Bingo>();
    while (input.size() > 0) {
      input.remove(0);
      List<String> aux = new ArrayList<String>();
      aux.add(input.remove(0));
      aux.add(input.remove(0));
      aux.add(input.remove(0));
      aux.add(input.remove(0));
      aux.add(input.remove(0));
      bingo.add(new Bingo(aux));
    }

    for (int i = 0; i < numbers.length; i++) {
      for (int j = 0; j < bingo.size(); j++) {
        if (!bingo.get(j).is_done()) {
          if (bingo.get(j).add_number(Integer.parseInt(numbers[i]))) {
            return;
          }
        }
      }
    }

  }

}

class Bingo {
  int[][] bingo = new int[5][5];
  int[][] marks = new int[5][5];

  public Bingo(List<String> l) {
    for (int i = 0; i < l.size(); i++) {
      String s = l.get(i);
      bingo[i][0] = Integer.parseInt(s.substring(0, 2).trim());
      bingo[i][1] = Integer.parseInt(s.substring(3, 5).trim());
      bingo[i][2] = Integer.parseInt(s.substring(6, 8).trim());
      bingo[i][3] = Integer.parseInt(s.substring(9, 11).trim());
      bingo[i][4] = Integer.parseInt(s.substring(12, 14).trim());
    }
  }

  public boolean is_done() {
    for (int i = 0; i < 5; i++) {
      int horizontal = 0;
      int vertical = 0;
      for (int j = 0; j < 5; j++) {
        horizontal = horizontal + this.marks[i][j];
        vertical = vertical + this.marks[j][i];
      }
      if (horizontal == 5 || vertical == 5) {
        return true;
      }
    }
    return false;
  }

  public boolean add_number(int n) {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (bingo[i][j] == n) {
          this.marks[i][j] = 1;
          if (this.is_done()) {
            System.out.println(this.final_score(n));
            return true;
          }
        }
      }
    }
    return false;
  }

  public int final_score(int n) {
    int total = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (this.marks[i][j] == 0) {
          total = total + this.bingo[i][j];
        }
      }
    }
    return total * n;
  }

}
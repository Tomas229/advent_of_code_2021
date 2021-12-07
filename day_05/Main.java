import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

class Main {
  public int index = 0;

  public static void main(String[] args) throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));

    // System.out.println(input);
    List<Point> map = new ArrayList<Point>();
    List<Integer> frec = new ArrayList<Integer>();

    for (int z = 0; z < input.size(); z++) {
      String coords = input.get(z);
      int x1 = Integer.parseInt(coords.split("->")[0].split(",")[0].trim());
      int y1 = Integer.parseInt(coords.split("->")[0].split(",")[1].trim());
      int x2 = Integer.parseInt(coords.split("->")[1].split(",")[0].trim());
      int y2 = Integer.parseInt(coords.split("->")[1].split(",")[1].trim());

      Point p1 = new Point(x1, y1);
      Point p2 = new Point(x2, y2);

      if (p1.is_hor_or_ver(p2)) {
        List<Point> r = p1.road(p2);

        for (int i = 0; i < r.size(); i++) {
          boolean new_p = true;
          Point p = r.get(i);
          for (int j = 0; j < map.size(); j++) {
            if (p.equals(map.get(j))) {
              int aux = frec.get(j) + 1;
              frec.set(j, aux);
              new_p = false;
              break; // cambiar
            }
          }
          if (new_p) {
            map.add(p);
            frec.add(1);
          }

        }
      }
    }
    System.out.println(frec);
    System.out.println(map);
    frec.removeIf(s -> (s < 2));
    System.out.println(frec.size());
    // System.out.println(frec);
  }
}

class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean is_hor_or_ver(Point p) {
    return this.x == p.x || this.y == p.y;
  }

  public boolean equals(Point p) {
    return this.x == p.x && this.y == p.y;
  }

  public List<Point> road(Point p) {
    List<Point> r = new ArrayList<Point>();
    r.add(this);
    if (this.x == p.x) {
      int aux = Math.abs(this.y - p.y);
      for (int i = 0; i < aux; i++) {
        r.add(new Point(this.x, Math.min(this.y, p.y) + i + 1));
      }
    }
    if (this.y == p.y) {
      int aux = Math.abs(this.x - p.x);
      for (int i = 0; i < aux; i++) {
        r.add(new Point(Math.min(this.x, p.x) + i + 1, this.y));
      }
    }
    r.add(p);
    return r;
  }

  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

}

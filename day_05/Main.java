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

      if (p1.is_hor_or_ver(p2) || true) {
        List<Point> r = p1.road(p2);

        List<Point> auxMap = new ArrayList<Point>();
        List<Integer> auxFrec = new ArrayList<Integer>();

        for (int i = 0; i < r.size(); i++) {
          boolean new_p = true;
          Point p = r.get(i);
          for (int j = 0; j < map.size(); j++) {
            if (p.equals(map.get(j))) {
              int aux = frec.get(j) + 1;
              frec.set(j, aux);
              new_p = false;
              break;
            }
          }
          if (new_p) {
            auxMap.add(p);
            auxFrec.add(1);
          }

        }
        map.addAll(auxMap);
        frec.addAll(auxFrec);
      }
    }
    // System.out.println(frec);
    // System.out.println(map);
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
    int dx = Math.abs(p.x - this.x);
    int dy = Math.abs(p.y - this.y); // pene
    int vx = (p.x - this.x) / (Math.max(dx, 1));
    int vy = (p.y - this.y) / (Math.max(dy, 1));

    for (int i = 0; i < Math.max(dx, dy); i++) {
      r.add(new Point(this.x + (i + 1) * vx, this.y + (i + 1) * vy));
    }

    r.add(p);
    return r;
  }

  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

}

import java.util.List;
import java.io.IOException;
import java.nio.file.*;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> content = Files.readAllLines(Paths.get("input.txt"));

    // System.out.println(content);

    // int depths[] = { 199, 200, 208, 210, 200, 207, 240, 269, 260, 263 };

    int x = 0;
    int y = 0;

    for (int i = 0; i < content.size(); i++) {
      String data = content.get(i);

      int value = Integer.parseInt(data.substring(data.length() - 1));
      String direction = data.substring(0, data.length() - 1);
      if (direction.equals("forward ")) {
        x = x + value;
      }
      if (direction.equals("down ")) {
        y = y + value;

      }
      if (direction.equals("up ")) {
        y = y - value;
      }
    }
    System.out.println(x * y);
  }
}
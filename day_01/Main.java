import java.util.List;
import java.io.IOException;
import java.nio.file.*;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> content = Files.readAllLines(Paths.get("input.txt"));

    System.out.println(content);

    // int depths[] = { 199, 200, 208, 210, 200, 207, 240, 269, 260, 263 };

    int count = 0;
    for (int i = 0; i < content.size() - 1; i++) {
      if (Integer.parseInt(content.get(i)) < Integer.parseInt(content.get(i + 1))) {
        count++;
      }
    }
    System.out.println(count);

    count = 0;
    for (int i = 0; i < content.size() - 3; i++) {
      int first_window = Integer.parseInt(content.get(i)) + Integer.parseInt(content.get(i + 1))
          + Integer.parseInt(content.get(i + 2));
      int second_window = Integer.parseInt(content.get(i + 1)) + Integer.parseInt(content.get(i + 2))
          + Integer.parseInt(content.get(i + 3));

      if (first_window < second_window) {
        count++;
      }
    }
    System.out.println(count);
  }
}
import java.util.List;
import java.io.IOException;
import java.nio.file.*;

class Main {
  public static void main(String[] args) throws IOException {

    List<String> content = Files.readAllLines(Paths.get("input.txt"));

    int count = 0;
    // int depths[] = { 199, 200, 208, 210, 200, 207, 240, 269, 260, 263 };
    for (int i = 0; i < content.size() - 1; i++) {
      if (Integer.parseInt(content.get(i)) < Integer.parseInt(content.get(i+1))) {
        count++;
      }
    }
    System.out.println(count);
  }
}
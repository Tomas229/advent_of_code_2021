import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> content = Files.readAllLines(Paths.get("input.txt"));

    // System.out.println(content);

    // int depths[] = { 199, 200, 208, 210, 200, 207, 240, 269, 260, 263 };

    int gamma[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    for (int i = 0; i < content.size(); i++) {
      String data = content.get(i);

      for (int j = 0; j < data.length(); j++) {
        char c = data.charAt(j);
        if (c == '1') {
          gamma[j] = gamma[j] + 1;
        } else {
          gamma[j] = gamma[j] - 1;
        }
      }
    }

    String s = "";
    for (int k = 0; k < gamma.length; k++) {
      if (gamma[k] > 0) {
        s = s + "1";
      } else {
        s = s + "0";
      }
    }

    System.out.println(s);
    int num = Integer.parseInt(s, 2);

    System.out.println(num * (num ^ Integer.parseInt("111111111111", 2)));
  }
}
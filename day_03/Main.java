import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

class Main {
  public int index = 0;

  public static void main(String[] args) throws IOException {
    List<String> content = Files.readAllLines(Paths.get("input.txt"));

    // System.out.println(content);

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

    int num = Integer.parseInt(s, 2);

    System.out.println(num * (num ^ Integer.parseInt("111111111111", 2)));

    System.out.println(Integer.parseInt(Main.major_list(new ArrayList<String>(content)).get(0), 2)
        * Integer.parseInt(Main.minor_list(new ArrayList<String>(content)).get(0), 2));
  }

  public static List<String> major_list(List<String> l) {
    int i = 0;
    while (l.size() > 1) {
      List<String> List_0 = new ArrayList<String>();
      List<String> List_1 = new ArrayList<String>();

      for (String code : l) {
        if (code.charAt(i) == '0') {
          List_0.add(code);
        } else {
          List_1.add(code);
        }
      }
      i = i + 1;

      if (List_0.size() > List_1.size()) {
        l.clear();
        l.addAll(List_0);
      } else {
        l.clear();
        l.addAll(List_1);
      }
    }
    return l;
  }

  public static List<String> minor_list(List<String> l) {
    int i = 0;
    while (l.size() > 1) {
      List<String> List_0 = new ArrayList<String>();
      List<String> List_1 = new ArrayList<String>();

      for (String code : l) {
        if (code.charAt(i) == '0') {
          List_0.add(code);
        } else {
          List_1.add(code);
        }
      }
      i = i + 1;

      if (List_0.size() <= List_1.size()) {
        l.clear();
        l.addAll(List_0);
      } else {
        l.clear();
        l.addAll(List_1);
      }
    }
    return l;
  }
}
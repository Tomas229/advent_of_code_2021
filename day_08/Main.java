import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> input = Main.getMultiLineInputStringArray();

    List<String[]> inputPuzzle = new ArrayList<String[]>();
    List<String[]> outputPuzzle = new ArrayList<String[]>();

    int count = 0;

    for (int i = 0; i < input.size(); i++) {
      String[] auxInput = input.get(i).split("\\|")[0].split(" ");
      inputPuzzle.add(auxInput);

      String[] auxOutput = input.get(i).split("\\|")[1].substring(1).split(" ");
      outputPuzzle.add(auxOutput);

      for (String s : auxOutput) {
        if ((1 < s.length() && s.length() < 5) || s.length() == 7) {
          count++;
        }
      }
    }
    System.out.println(count);

    // Main.printFirstStarSolution(input);
    Main.printSecondStarSolution(inputPuzzle, outputPuzzle);
  }

  public static int[] getOneLineInputIntArray() throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    // System.out.println(input);
    return Stream.of(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
  }

  public static List<String> getMultiLineInputStringArray() throws IOException {
    List<String> input = Files.readAllLines(Paths.get("input.txt"));
    // System.out.println(input);
    return input;
  }

  public static void printFirstStarSolution(int[] init) {

  }

  public static void printSecondStarSolution(List<String[]> code, List<String[]> numbers) {
    int count = 0;
    for (int i = 0; i < code.size(); i++) {
      Display d = new Display();
      d.decode(code.get(i));
      String s = "";
      for (int j = 0; j < numbers.get(i).length; j++) {
        s = s + d.turnInToInt(numbers.get(i)[j]);
      }
      count = count + Integer.parseInt(s);
    }
    System.out.println(count);
  }

  public static String sortString(String inputString) {
    char tempArray[] = inputString.toCharArray();
    Arrays.sort(tempArray);
    return new String(tempArray);
  }

}

class Display {
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;

  private String zero;
  private String one;
  private String two;
  private String three;
  private String four;
  private String five;
  private String six;
  private String seven;
  private String eigth;
  private String nine;

  public Display() {
  }

  public void decode(String[] code) {
    Arrays.sort(code, (a, b) -> Integer.compare(a.length(), b.length()));
    this.a = code[1].replace(code[0].substring(0, 1), "").replace(code[0].substring(1, 2), "");

    String cf = code[0];
    for (int i = 6; i < 9; i++) {
      if (code[i].contains(cf.substring(0, 1)) ^ code[i].contains(cf.substring(1, 2))) {
        if (code[i].contains(cf.substring(0, 1))) {
          this.f = cf.substring(0, 1);
          this.c = cf.substring(1, 2);
        } else {
          this.c = cf.substring(0, 1);
          this.f = cf.substring(1, 2);
        }
      }
    }

    String bd = code[2].replace(this.a, "").replace(this.c, "").replace(this.f, "");
    for (int i = 6; i < 9; i++) {
      if (code[i].contains(bd.substring(0, 1)) ^ code[i].contains(bd.substring(1, 2))) {
        if (code[i].contains(bd.substring(0, 1))) {
          this.b = bd.substring(0, 1);
          this.d = bd.substring(1, 2);
        } else {
          this.d = bd.substring(0, 1);
          this.b = bd.substring(1, 2);
        }
      }
    }

    String eg = code[9].replace(this.a, "").replace(this.c, "").replace(this.f, "").replace(this.b, "").replace(this.d,
        "");
    for (int i = 6; i < 9; i++) {
      if (code[i].contains(eg.substring(0, 1)) ^ code[i].contains(eg.substring(1, 2))) {
        if (code[i].contains(eg.substring(0, 1))) {
          this.g = eg.substring(0, 1);
          this.e = eg.substring(1, 2);
        } else {
          this.e = eg.substring(0, 1);
          this.g = eg.substring(1, 2);
        }
      }
    }

    this.zero = Main.sortString(this.a + this.b + this.c + this.e + this.f + this.g);
    this.one = Main.sortString(this.c + this.f);
    this.two = Main.sortString(this.a + this.c + this.d + this.e + this.g);
    this.three = Main.sortString(this.a + this.c + this.d + this.f + this.g);
    this.four = Main.sortString(this.b + this.c + this.d + this.f);
    this.five = Main.sortString(this.a + this.b + this.d + this.f + this.g);
    this.six = Main.sortString(this.a + this.b + this.d + this.e + this.f + this.g);
    this.seven = Main.sortString(this.a + this.c + this.f);
    this.eigth = Main.sortString(this.a + this.b + this.c + this.d + this.e + this.f + this.g);
    this.nine = Main.sortString(this.a + this.b + this.c + this.d + this.f + this.g);
  }

  public String turnInToInt(String s) {
    s = Main.sortString(s);
    if (s.equals(this.zero))
      return "0";
    if (s.equals(this.one))
      return "1";
    if (s.equals(this.two))
      return "2";
    if (s.equals(this.three))
      return "3";
    if (s.equals(this.four))
      return "4";
    if (s.equals(this.five))
      return "5";
    if (s.equals(this.six))
      return "6";
    if (s.equals(this.seven))
      return "7";
    if (s.equals(this.eigth))
      return "8";
    if (s.equals(this.nine))
      return "9";
    return "ERROR";
  }

  public String getZero() {
    return this.a + this.b + this.c + this.e + this.f + this.g;
  }

  public String getOne() {
    return this.c + this.f;
  }

  public String getTwo() {
    return this.a + this.c + this.d + this.e + this.g;
  }

  public String getThree() {
    return this.a + this.c + this.d + this.f + this.g;
  }

  public String getFour() {
    return this.b + this.c + this.d + this.f;
  }

  public String getFive() {
    return this.a + this.b + this.d + this.f + this.g;
  }

  public String getSix() {
    return this.a + this.b + this.d + this.e + this.f + this.g;
  }

  public String getSeven() {
    return this.a + this.c + this.f;
  }

  public String getEigth() {
    return this.a + this.b + this.c + this.d + this.e + this.f + this.g;
  }

  public String getNine() {
    return this.a + this.b + this.c + this.d + this.f + this.g;
  }
}

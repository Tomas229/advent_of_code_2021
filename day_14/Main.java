import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;
import aoc.*;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> instructions = AoC.getMultiLineInputStringArray();

    String start = instructions.get(0);
    instructions.remove(0);
    instructions.remove(0);

    Polymer protein = new Polymer(start.substring(0, 1), start.substring(start.length() - 1));

    for (int i = 0; i < start.length() - 1; i++) {
      String ss = start.substring(i, i + 2);
      protein.add(ss);
    }

    Map<String, String> map = new HashMap<String, String>();
    for (String ins : instructions) {
      map.put(ins.split("->")[0].trim(), ins.split("->")[1].trim());
    }

    Main.printFirstStarSolution(protein, map);
    Main.printSecondStarSolution(protein);
  }

  public static void printFirstStarSolution(Polymer protein, Map<String, String> instructions) {
    protein.addInstructions(instructions);
    protein.countLetters();

    for (int h = 0; h < 10; h++) {
      protein.takeStep();
    }
    System.out.print("1st Star: ");
    System.out.println(protein.getFrecuencyOfMostCommon() - protein.getFrecuencyOfLeastCommon());
  }

  public static void printSecondStarSolution(Polymer protein) {
    for (int h = 0; h < 30; h++) {
      protein.takeStep();
    }
    System.out.print("2nd Star: ");
    System.out.println(protein.getFrecuencyOfMostCommon() - protein.getFrecuencyOfLeastCommon());

  }

}

class Polymer {
  Map<String, Long> formula;
  Map<String, String> instructions;
  String firstLetter;
  String lastLetter;
  ArrayList<String> letters;

  public Polymer(String f, String l) {
    this.firstLetter = f;
    this.lastLetter = l;
    this.formula = new HashMap<String, Long>();
  }

  public void add(String ss) {
    if (!this.formula.containsKey(ss)) {
      this.formula.put(ss, 1L);
    } else {
      long v = this.formula.get(ss);
      this.formula.replace(ss, v + 1);
    }
  }

  public void addAll(String ss, long quantity) {
    if (!this.formula.containsKey(ss)) {
      this.formula.put(ss, quantity);
    } else {
      Long v = this.formula.get(ss);
      this.formula.replace(ss, v + quantity);
    }
  }

  public void remove(String ss) {
    long v = this.formula.get(ss);
    long nv = Math.max(v - 1, 0);
    if (nv > 0) {
      this.formula.replace(ss, nv);
    } else {
      this.formula.remove(ss, v);
    }
  }

  public long removeAll(String ss) {
    long v = this.formula.get(ss);
    this.formula.remove(ss, v);
    return v;
  }

  public void addInstructions(Map<String, String> instructions) {
    this.instructions = instructions;
  }

  public void takeStep() {
    Map<String, Long> formula = new HashMap<String, Long>(this.formula);
    this.formula = new HashMap<String, Long>();

    for (Map.Entry<String, Long> set : formula.entrySet()) {
      if (this.instructions.containsKey(set.getKey())) {
        String s = set.getKey();
        long v = set.getValue();
        String ns = instructions.get(s);

        this.addAll(s.substring(0, 1) + ns, v);
        this.addAll(ns + s.substring(1, 2), v);
      }
    }
  }

  public Map<String, Long> getFormula() {
    return this.formula;
  }

  public long getFrecuencyOfMostCommon() {
    long max = Long.MIN_VALUE;

    for (String l : this.letters) {
      long v = getFrecuencyOfLetter(l, l.equals(this.firstLetter) ? true : false,
          l.equals(this.lastLetter) ? true : false);

      if (v > max)
        max = v;
    }
    return max;
  }

  public long getFrecuencyOfLeastCommon() {
    long min = Long.MAX_VALUE;

    for (String l : this.letters) {
      long v = getFrecuencyOfLetter(l, l.equals(this.firstLetter) ? true : false,
          l.equals(this.lastLetter) ? true : false);

      if (v < min)
        min = v;
    }
    return min;
  }

  public long getFrecuencyOfLetter(String s, boolean first, boolean last) {
    long frec = 0;
    for (Map.Entry<String, Long> set : this.formula.entrySet()) {
      if (set.getKey().contains(s)) {
        frec = frec + set.getValue();
      }
      if (set.getKey().contains(s + s)) {
        frec = frec + set.getValue();
      }
    }
    return frec / 2 + (first ? 1 : 0) + (last ? 1 : 0);
  }

  public void countLetters() {
    ArrayList<String> al = new ArrayList<String>();
    for (Map.Entry<String, String> set : this.instructions.entrySet()) {
      if (!al.contains(set.getValue())) {
        al.add(set.getValue());
      }
    }
    this.letters = al;
  }

  public String toString() {
    return formula.toString();
  }
}
import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

import aoc.*;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> input = AoC.getMultiLineInputStringArray();

    // System.out.println(input);

    String imageString = input.get(0);
    input.remove(0);
    input.remove(0);

    for (int i = 0; i < input.size(); i++) {
      input.set(i, input.get(i).trim());
    }

    String inf = ".";
    input.add(0, new String(new char[input.get(1).length()]).replace("\0", inf));
    input.add(input.size(), new String(new char[input.get(1).length()]).replace("\0", inf));
    for (int i = 0; i < input.size(); i++) {
      input.set(i, inf + input.get(i) + inf);
    }

    // System.out.println(input);
    // System.out.println(imageString);

    printFirstStarSolution(input, imageString);
    printSecondStarSolution(input, imageString);
  }

  public static void printFirstStarSolution(List<String> image, String imageString) {
    for (int i = 0; i < 2; i++) {
      image = Main.takeStepOnImage(image, imageString);
    }
    int count = 0;
    for (String s : image) {
      count = count + s.split("#", -1).length - 1;
    }
    System.out.print("1st Star: ");
    System.out.println(count);
  }

  public static void printSecondStarSolution(List<String> image, String imageString) {
    for (int i = 0; i < 50; i++) {
      image = Main.takeStepOnImage(image, imageString);
    }
    int count = 0;
    for (String s : image) {
      count = count + s.split("#", -1).length - 1;
    }
    System.out.print("2nd Star: ");
    System.out.println(count);
  }

  public static int getIndexFromCoords(int x, int y, List<String> image) {
    ArrayList<int[]> indexes = AoC.getAllAdyacentIndexes(x, y, 100 * x, 100 * y);
    int[] aux = { x, y };
    indexes.add(4, aux);
    String s = "";
    for (int[] ind : indexes) {
      String c = "";
      if (image.get(ind[0]).substring(ind[1], ind[1] + 1).equals("#")) {
        c = "1";
      } else {
        c = "0";
      }
      s = s + c;
    }

    return Integer.parseInt(s, 2);
  }

  public static List<String> takeStepOnImage(List<String> image, String imageString) {
    String inf = image.get(0).substring(0, 1);

    for (int x = 0; x < 2; x++) {
      image.add(0, new String(new char[image.get(1).length()]).replace("\0", inf));
      image.add(image.size(), new String(new char[image.get(1).length()]).replace("\0", inf));
      for (int i = 0; i < image.size(); i++) {
        image.set(i, inf + image.get(i) + inf);
      }
    }

    List<String> output = new ArrayList<String>();
    for (int i = 1; i < image.size() - 1; i++) {
      String s = "";
      for (int j = 1; j < image.get(i).length() - 1; j++) {
        int index = Main.getIndexFromCoords(i, j, image);
        s = s + imageString.substring(index, index + 1);
      }
      output.add(s);
    }
    return output;
  }

}
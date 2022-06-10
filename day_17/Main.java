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

    System.out.println(input);

    int maxYPosition = (Math.abs(-163) - 1) * (Math.abs(-163)) / 2;
		System.out.println(maxYPosition);

    printFirstStarSolution();

  }

  public static void printFirstStarSolution() {
    int maxY = 0;
    for (int y = 0; y < 15000; y++) {
      for (int x = 0; x < 1000; x++) {

        Probe p = new Probe(x, y, 85, 145, -163, -108);

        while (p.checkIfCanMakeIt()) {
          p.takeStep();
          if (p.checkIfInBounds()) {
            maxY = Math.max(y, maxY);
            break;
          }
        }
      }
    }
    System.out.print("1st Star: ");
    System.out.println(maxY);
  }

  public static void printSecondStarSolution() {
    System.out.print("2nd Star: ");

  }

}

class Probe {
  int xVel;
  int yVel;
  int leftBound;
  int rightBound;
  int upperBound;
  int lowerBound;
  int x;
  int y;

  public Probe(int xVel, int yVel, int leftBound, int rightBound, int lowerBound, int upperBound) {
    this.xVel = xVel;
    this.yVel = yVel;
    this.leftBound = leftBound;
    this.rightBound = rightBound;
    this.upperBound = upperBound;
    this.lowerBound = lowerBound;
    this.x = 0;
    this.y = 0;
  }

  public boolean checkIfInBounds() {
    return (this.leftBound <= this.x && this.x <= this.rightBound)
        && (this.lowerBound <= this.y && this.y <= this.upperBound);
  }

  public boolean checkIfCanMakeIt() {
    if (this.xVel <= 0 && this.leftBound > this.x ) {
      return false;
    }

    if (this.xVel >= 0 && this.x > this.rightBound) {
      return false;
    }

    if (this.yVel <= 0 && this.lowerBound > this.y) {
      return false;
    }
    return true;
  }

  public void takeStep() {
    this.x = this.x + this.xVel;
    this.y = this.y + this.yVel;

    if (this.xVel > 0)
      this.xVel = this.xVel - 1;
    if (this.xVel < 0)
      this.xVel = this.xVel + 1;

    this.yVel = this.yVel - 1;
  }
}

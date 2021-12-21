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
    int posP1 = Integer.parseInt(input.get(0).split(":")[1].trim());
    int posP2 = Integer.parseInt(input.get(1).split(":")[1].trim());

    int[] p = { posP1, posP2 };
    printFirstStarSolution(p);
    printSecondStarSolution();
  }

  public static void printFirstStarSolution(int[] p) {
    Game g = new Game(p);
    int count = g.playDeterministicGame();
    System.out.print("1st Star: ");
    System.out.println(count);
  }

  public static void printSecondStarSolution() {
    int count = 0;
    System.out.print("2nd Star: ");
    System.out.println(count);
  }

}

class Game {
  int die;
  int diceThrown;
  int playerTurn;
  ArrayList<Player> players;

  public Game(int[] startingPositions) {
    this.die = 0;
    this.diceThrown = 0;

    this.players = new ArrayList<Player>();
    for (int i = 0; i < startingPositions.length; i++) {
      this.players.add(new Player(i, startingPositions[i]));
    }
  }

  public int playDeterministicGame() {
    while (true) {
      this.takeTurn();
      int score = this.players.get(this.playerTurn).getScore();
      this.playerTurn = (this.playerTurn + 1) % this.players.size();

      if (score >= 1000) {
        break;
      }

    }

    return this.diceThrown * this.players.get(this.playerTurn).getScore();
  }

  public void takeTurn() {
    this.diceThrown = this.diceThrown + 3;
    int sum = (this.die + 1) * 3 + 3;
    this.die = (this.die + 3) % 100;

    this.players.get(this.playerTurn).move(sum);
  }

}

class Player {
  int name;
  int space;
  int score;

  public Player(int name, int initialSpace) {
    this.name = name;
    this.space = initialSpace - 1;
    this.score = 0;
  }

  public void move(int spaces) {
    this.space = ((this.space + spaces) % 10);
    this.score = this.score + this.space + 1;
  }

  public int getScore() {
    return this.score;
  }
}
import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;
import aoc.*;
import java.util.Map.Entry;

class Main {
  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<Integer>> input = AoC.getIntMatrixList();

    System.out.println(input);

    printFirstStarSolution();

  }

  public static void printFirstStarSolution() {
    System.out.print("1st Star: ");
  }

  public static void printSecondStarSolution() {
    System.out.print("2nd Star: ");
  }

}

class Graph {

  public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
    source.setDistance(0);

    Set<Node> settledNodes = new HashSet<>();
    Set<Node> unsettledNodes = new HashSet<>();

    unsettledNodes.add(source);

    while (unsettledNodes.size() != 0) {
      Node currentNode = getLowestDistanceNode(unsettledNodes);
      unsettledNodes.remove(currentNode);
      for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
        Node adjacentNode = adjacencyPair.getKey();
        Integer edgeWeight = adjacencyPair.getValue();
        if (!settledNodes.contains(adjacentNode)) {
          calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
          unsettledNodes.add(adjacentNode);
        }
      }
      settledNodes.add(currentNode);
    }
    return graph;
  }

  private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
    Node lowestDistanceNode = null;
    int lowestDistance = Integer.MAX_VALUE;
    for (Node node : unsettledNodes) {
      int nodeDistance = node.getDistance();
      if (nodeDistance < lowestDistance) {
        lowestDistance = nodeDistance;
        lowestDistanceNode = node;
      }
    }
    return lowestDistanceNode;
  }

  private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
    Integer sourceDistance = sourceNode.getDistance();
    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
      evaluationNode.setDistance(sourceDistance + edgeWeigh);
      LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
      shortestPath.add(sourceNode);
      evaluationNode.setShortestPath(shortestPath);
    }
  }

}

class Node {
  private int x;
  private int y;
  private int cost;
  private int distance = Integer.MAX_VALUE;
  private List<Node> shortestPath = new LinkedList<>();

  public Node(int x, int y, int cost) {
    this.x = x;
    this.y = y;
    this.cost = cost;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getDistance() {
    return this.distance;
  }

  public void setDistance(int d) {
    this.distance = d;
  }

  public List<Node> getShortestPath() {
    return this.shortestPath;
  }

  public void setShortestPath(List<Node> s) {
    this.shortestPath = s;
  }

  public boolean equals(Node n) {
    return this.x == n.getX() && this.y == n.getY();
  }
}

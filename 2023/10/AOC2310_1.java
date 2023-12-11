import java.io.*;
import java.util.*;
import java.nio.file.*;
//import java.awt.Point;

class Point {
  int x;
  int y;
  int distance;
  public Point(int x, int y, int distance) {
    this.x = x;
    this.y = y;
    this.distance = distance;
  }
  public int getX() { return x; }
  public int getY() { return y; }
  public int getDistance() { return distance; }
  public String toString() {
    return String.format("(%d, %d) [%d]", x, y, distance);
  }
  public boolean equals(Object o) {
    if (!(o instanceof Point)) { return false; }
    Point p = (Point) o;
    return (this.x == p.getX()) && (this.y == p.getY());
  }
}
public class AOC2310_1 {

  public static final Stack<Point> visited = new Stack<>();
  public static final  Queue<Point> needToVisit = new LinkedList<>();
  public static char[][] pipes;

  public static void main(String[] args) throws Exception {

    List<String> lines = Files.readAllLines(Paths.get(args[0]));

    // create a 2d array to store the pipe map [cols][rows]
    //char[][] pipes = new char[lines.get(0).length()][lines.size()];
    pipes = new char[lines.get(0).length()][lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      for (int j = 0; j < line.length(); j++) {
        pipes[j][i] = line.charAt(j);
      }
    }

    Point startingPoint = null;
    char coveredSymbol = ' ';
    for (int i = 0; i < pipes[0].length; i++) {
      //System.out.println(Arrays.toString(pipes[i]));
      for (int j = 0; j < pipes.length; j++) {
        System.out.printf("%s", pipes[j][i]);
        if (pipes[j][i] == 'S') {
          startingPoint = new Point(j, i, 0);
          char north = i > 0 ? pipes[j][i-1] : ' ';
          char south = i < pipes[j].length - 2 ? pipes[j][i+1] : ' ';
          char west  = j > 0 ? pipes[j-1][i] : ' ';
          char east  = j < pipes.length - 2 ? pipes[j+1][i] : ' ';
          // figure out what symbol goes below S
          if ((north == '|' || north == '7' || north == 'F') && (south == '|' || south == 'L' || south == 'J')) pipes[j][i] = '|';
          if ((west == '-' || west == 'L' || west == 'F') && (east == '-' || east == 'J' || east == '7')) pipes[j][i] = '-';
          if ((north == '|' || north == '7' || north == 'F') && (east == '-' || east == 'J' || east == '7')) pipes[j][i] = 'L';
          if ((west == '-' || west == 'L' || west == 'F') && (north == '|' || north == '7' || north == 'F')) pipes[j][i] = 'J';
          if ((west == '-' || west == 'L' || west == 'F') && (south == '|' || south == 'L' || south == 'J')) pipes[j][i] = '7';
          if ((east == '-' || east == 'J' || east == '7') && (south == '|' || south == 'L' || south == 'J')) pipes[j][i] = 'F';
          coveredSymbol = pipes[j][i];
        }
      }
      System.out.println();
    }

    System.out.println("S is at " + startingPoint + " with " + coveredSymbol);

    getMaxDistance(startingPoint, startingPoint.getDistance() + 1);
    int max = 0;
    for (Point p : visited) {
      if (p.getDistance() > max) max = p.getDistance();
    }
    System.out.println(max);
  }

  public static void getMaxDistance(Point startingPoint, int distance) {

    Point currentLocation = startingPoint;

    //System.out.printf("[DEBUG] visting Point %s using distance %d\n", currentLocation, distance);

    // from current location, get char north/south/east/west 
    int x = (int) currentLocation.getX();
    int y = (int) currentLocation.getY();

    switch (pipes[x][y]) {
      case '|':
        Point p1 = new Point(x, y - 1, distance);
        Point p2 = new Point(x, y + 1, distance);
        if (!visited.contains(p1)) needToVisit.add(p1);
        if (!visited.contains(p2)) needToVisit.add(p2);
        break;
      case '-':
        p1 = new Point(x - 1, y, distance);
        p2 = new Point(x + 1, y, distance);
        if (!visited.contains(p1)) needToVisit.add(p1);
        if (!visited.contains(p2)) needToVisit.add(p2);
        break;
      case 'L':
        p1 = new Point(x, y - 1, distance);
        p2 = new Point(x + 1, y, distance);
        if (!visited.contains(p1)) needToVisit.add(p1);
        if (!visited.contains(p2)) needToVisit.add(p2);
        break;
      case 'J':
        p1 = new Point(x, y - 1, distance);
        p2 = new Point(x - 1, y, distance);
        if (!visited.contains(p1)) needToVisit.add(p1);
        if (!visited.contains(p2)) needToVisit.add(p2);
        break;
      case '7':
        p1 = new Point(x - 1, y, distance);
        p2 = new Point(x, y + 1, distance);
        if (!visited.contains(p1)) needToVisit.add(p1);
        if (!visited.contains(p2)) needToVisit.add(p2);
        break;
      case 'F':
        p1 = new Point(x + 1, y, distance);
        p2 = new Point(x, y + 1, distance);
        if (!visited.contains(p1)) needToVisit.add(p1);
        if (!visited.contains(p2)) needToVisit.add(p2);
        break;
    }

    visited.push(currentLocation);

    while (!needToVisit.isEmpty()) {
      //System.out.println("[DEBUG] " + Arrays.toString(visited.toArray()));
      //System.out.println("[DEBUG] " + Arrays.toString(needToVisit.toArray()));
      Point p = needToVisit.remove();
      getMaxDistance(p, p.getDistance() + 1);
    }

    //return maxDistance;

    //System.out.println(Arrays.toString(needToVisit.toArray()));

  }
}

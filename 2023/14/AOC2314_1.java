import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.awt.Point;

public class AOC2314_1 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    char[][] platform = new char[lines.get(0).length()][lines.size()];
    List<Point> roundRocks = new ArrayList<>();
    for (int y = 0; y < platform[0].length; y++) {
      String line = lines.get(y);
      for (int x = 0; x < platform.length; x++) {
        platform[x][y] = line.charAt(x);
        if (platform[x][y] == 'O') {
          Point p = new Point(x, y);
          roundRocks.add(p);
        }
      }
    }
    System.out.println("found " + roundRocks.size() + " round rock(s).");
    // move the rocks
    for (int y = 0; y < platform[0].length; y++) {
      for (int x = 0; x < platform.length; x++) {
        char c = platform[x][y];
        if (c == 'O') {
          // move rock as up as it can go
          System.out.println("found rock at " + x + "," + y);
          System.out.println("\tmoving...");
          for (int i = y; i > 0; i--) {
            char c1 = platform[x][i - 1];
            System.out.print("\tposition above rock is " + c1 + ": ");
            if (c1 == 'O' || c1 == '#') {
              // cannot move rock north
              System.out.println("cannot move rock.");
              break;
            } else { // c1 == '.'
              // move rock north
              System.out.println("moving rock.");
              platform[x][i-1] = 'O';
              platform[x][i] = '.'; 
            }
          }
        }
      }
    }

    // display the platform and calculate sum
    int sum = 0;
    for (int y = 0; y < platform[0].length; y++) {
      int multiple = platform[0].length - y;
      int rockCount = 0;
      System.out.print(multiple + "\t");
      for (int x = 0; x < platform.length; x++) {
        char c = platform[x][y];
        System.out.print(c);
        if (c == 'O') rockCount++;
      }
      System.out.println();
      sum += (rockCount * multiple);
    }
    System.out.println(sum);
  }
}

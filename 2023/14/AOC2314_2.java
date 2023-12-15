import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.awt.Point;

public class AOC2314_2 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    char[][] initial = new char[lines.get(0).length()][lines.size()];
    char[][] platform = new char[lines.get(0).length()][lines.size()];
    List<Point> roundRocks = new ArrayList<>();
    for (int y = 0; y < platform[0].length; y++) {
      String line = lines.get(y);
      for (int x = 0; x < platform.length; x++) {
        platform[x][y] = line.charAt(x);
        initial[x][y] = line.charAt(x);
        if (platform[x][y] == 'O') {
          Point p = new Point(x, y);
          roundRocks.add(p);
        }
      }
    }
    System.out.println("found " + roundRocks.size() + " round rock(s).");

    int cycles = Integer.parseInt(args[1]);
    for (int cycle = 0; cycle < cycles; cycle++) {

      //System.out.println("before cycle " + (cycle + 1));
      //displayPlatform(platform);


    // move the rocks north
    for (int y = 0; y < platform[0].length; y++) {
      for (int x = 0; x < platform.length; x++) {
        char c = platform[x][y];
        if (c == 'O') {
          // move rock as up as it can go
          //System.out.println("found rock at " + x + "," + y);
          //System.out.println("\tmoving...");
          for (int i = y; i > 0; i--) {
            char c1 = platform[x][i-1];
            //System.out.print("\tposition above rock is " + c1 + ": ");
            if (c1 == 'O' || c1 == '#') {
              // cannot move rock north
              //System.out.println("cannot move rock.");
              break;
            } else { // c1 == '.'
              // move rock north
              //System.out.println("moving rock.");
              platform[x][i-1] = 'O';
              platform[x][i] = '.'; 
            }
          }
        }
      }
    }
      //System.out.println("after tilting north");
      //displayPlatform(platform);

    // move the rocks west
    for (int y = 0; y < platform[0].length; y++) {
      for (int x = 0; x < platform.length; x++) {
        char c = platform[x][y];
        if (c == 'O') {
          // move rock as west as it can go
          //System.out.println("found rock at " + x + "," + y);
          //System.out.println("\tmoving...");
          for (int i = x; i > 0; i--) {
            char c1 = platform[i-1][y];
            //System.out.print("\tposition west of rock is " + c1 + ": ");
            if (c1 == 'O' || c1 == '#') {
              // cannot move rock west
              //System.out.println("cannot move rock.");
              break;
            } else { // c1 == '.'
              // move rock west
              //System.out.println("moving rock.");
              platform[i-1][y] = 'O';
              platform[i][y] = '.'; 
            }
          }
        }
      }
    }
      //System.out.println("after tilting west");
      //displayPlatform(platform);

    // move the rocks south
    for (int y = platform[0].length - 1; y >= 0; y--) {
      for (int x = 0; x < platform.length; x++) {
        char c = platform[x][y];
        if (c == 'O') {
          // move rock as south as it can go
          //System.out.println("found rock at " + x + "," + y);
          //System.out.println("\tmoving...");
          for (int i = y; i < platform[0].length - 1; i++) {
            char c1 = platform[x][i+1];
            //System.out.print("\tposition south of rock is " + c1 + ": ");
            if (c1 == 'O' || c1 == '#') {
              // cannot move rock south
              //System.out.println("cannot move rock.");
              break;
            } else { // c1 == '.'
              // move rock south
              //System.out.println("moving rock.");
              platform[x][i+1] = 'O';
              platform[x][i] = '.'; 
            }
          }
        }
      }
    }
      //System.out.println("after tilting south");
      //displayPlatform(platform);

    // move the rocks east
    for (int y = 0; y < platform[0].length; y++) {
      for (int x = platform.length - 1; x >= 0; x--) {
        char c = platform[x][y];
        if (c == 'O') {
          // move rock as east as it can go
          //System.out.println("found rock at " + x + "," + y);
          //System.out.println("\tmoving...");
          for (int i = x; i < platform.length - 1; i++) {
            char c1 = platform[i+1][y];
            //System.out.print("\tposition east of rock is " + c1 + ": ");
            if (c1 == 'O' || c1 == '#') {
              // cannot move rock east
              //System.out.println("cannot move rock.");
              break;
            } else { // c1 == '.'
              // move rock east
              //System.out.println("moving rock.");
              platform[i+1][y] = 'O';
              platform[i][y] = '.'; 
            }
          }
        }
      }
    }
      //System.out.println("after tilting east");
      //displayPlatform(platform);

      // at the end of a cycle, see if the rocks ever return to their original positions
      if (comparePlatforms(initial, platform)) {
        System.out.println("platform is back at original state after cycle " + cycle);
      }


    } // end cycles

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

  public static void displayPlatform(char[][] platform) {
    for (int y = 0; y < platform[0].length; y++) {
      for (int x = 0; x < platform.length; x++) {
        char c = platform[x][y];
        System.out.print(c);
      }
      System.out.println();
    }
  }

  public static boolean comparePlatforms(char[][] p1, char[][] p2) {
    for (int y = 0; y < p1[0].length; y++) {
      for (int x = 0; x < p1.length; x++) {
        if (p1[x][y] != p2[x][y]) return false;
      }
    }
    return true;
  }
}

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC2313_1 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    List<List<String>> patterns = new ArrayList<>();
    List<String> pattern = new ArrayList<>();
    for (String line : lines) {
      if (line.strip().length() == 0) {
        patterns.add(pattern);
        pattern = new ArrayList<>();
      } else {
        pattern.add(line);
      }
    }
    // account for last pattern assuming file doesn't end with blank line
    if (pattern.size() > 0) patterns.add(pattern);

    System.out.println("loaded " + patterns.size() + " pattern(s).");

    // for each pattern, create a 2d array to represent the grid
    int sum = 0; // this will store the final answer
    for (List<String> l : patterns) {
      char[][] grid = new char[l.get(0).length()][l.size()];
      for (int y = 0; y < grid[0].length; y++) {
        String s = l.get(y);
        for (int x = 0; x < grid.length; x++) {
          grid[x][y] = s.charAt(x);
        }
      }
      // print the grid for fun
      for (int y = 0; y < grid[0].length; y++) {
        for (int x = 0; x < grid.length; x++) {
          System.out.print(grid[x][y]);
        }
        System.out.println();
      }
      System.out.println();

      // now let's look for horizontal lines because it's easier
      int realHorizontalReflections = 0;
      for (int y = 0; y < grid[0].length - 1; y++) {
        boolean reflection = true;
        for (int x = 0; x < grid.length; x++) {
          if (grid[x][y] != grid[x][y+1]) {
            reflection = false;
            break;
          }
        }
        if (reflection) {
          System.out.printf("found horizontal reflection between y=%d and y=%d\n", y, y + 1);
          // now check if true reflection
          int i = y - 1;
          int j = y + 2;
          while (i >= 0 && j < grid[0].length) {
            for (int k = 0; k < grid.length; k++) {
              if (grid[k][i] != grid[k][j]) {
                reflection = false;
              }
            }
            i--;
            j++;
          }
          if (reflection) {
            //System.out.println("this is a real reflection");
            realHorizontalReflections++;
            sum += ((y + 1) * 100);
          } else {
            //System.out.println("this is a fake reflection");
          }
        }
      }

      // now let's look for vertical reflections
      int realVerticalReflections = 0;
      for (int x = 0; x < grid.length - 1; x++) {
        boolean reflection = true;
        for (int y = 0; y < grid[0].length; y++) {
          if (grid[x][y] != grid[x+1][y]) {
            reflection = false;
            break;
          }
        }
        if (reflection) {
          System.out.printf("found vertical reflection between x=%d and x=%d\n", x, x + 1);
          // now check if true reflection
          int i = x - 1;
          int j = x + 2;
          while (i >= 0 && j < grid.length) {
            for (int k = 0; k < grid[0].length; k++) {
              if (grid[i][k] != grid[j][k]) {
                reflection = false;
              }
            }
            i--;
            j++;
          }
          if (reflection) {
            //System.out.println("this is a real reflection");
            realVerticalReflections++;
            sum += (x + 1);
          } else {
            //System.out.println("this is a fake reflection");
          }
        }
      }

      System.out.println("found " + realHorizontalReflections + " real horizonal reflections");
      System.out.println("found " + realVerticalReflections + " real vertical reflections");
      if (realHorizontalReflections + realVerticalReflections > 1) {
        System.out.println("oops something went wrong");
        System.exit(-1);
      }
    }
    System.out.println(sum);
  }
}

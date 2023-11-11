import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class AOC2303_1 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    // char[col][row]
    char[][] schematic = new char[lines.get(0).length()][lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      for (int j = 0; j < line.length(); j++) {
        // i is row index, j is column index
        schematic[j][i] = line.charAt(j);
      }
    }
    /*
    for (int i = 0; i < schematic.length; i++) {
      for (int j = 0; j < schematic[i].length; j++) {
        System.out.print(schematic[j][i]);
      }
      System.out.println();
    }
    */
    int sum = 0;
    Pattern p = Pattern.compile("(\\d+)");
    int row = 0;
    for (String line : lines) {
      Matcher m = p.matcher(line);
      while (m.find()) {
        int start = m.start();
        int end = m.end();
        int n = Integer.parseInt(m.group(1));
        System.out.printf("Found %d in row %d which starts at %d and ends at %d\n", n, row, start, end);
        System.out.printf("Searching for an adjacent symbol...");

        for (int i = start; i < end; i++) {
          // look for symbol
          // if (!Character.isDigit(c) && c != '.') then must be a special character
          // i = x coord, row = y coord
          // check row above
          if (row != 0) {
            char c = '.';
            if (i != 0) {
              // check upper left
              c = schematic[i-1][row-1];
              if (!Character.isDigit(c) && c != '.') {
                sum += n;
                System.out.println("found.");
                break;
              }
            }
            // check right above
            c = schematic[i][row-1];
            if (!Character.isDigit(c) && c != '.') {
              sum += n;
              System.out.println("found.");
              break;
            }
            
            if (i != line.length() - 1) {
              // check upper right
              c = schematic[i+1][row-1];
              if (!Character.isDigit(c) && c != '.') {
                sum += n;
                System.out.println("found.");
                break;
              }
            }
          }
          // check same row
          if (i != 0) {
            // check left of char
            char c = schematic[i-1][row];
            if (!Character.isDigit(c) && c != '.') {
              sum += n;
              System.out.println("found.");
              break;
            }
          }
          if (i != line.length() - 1) {
            // check right of char
            char c = schematic[i+1][row];
            if (!Character.isDigit(c) && c != '.') {
              sum += n;
              System.out.println("found.");
              break;
            }

          }
          // check row below
          if (row != lines.size() - 1) {
            char c = '.';
            if (i != 0) {
              // check lower left
              c = schematic[i-1][row+1];
              if (!Character.isDigit(c) && c != '.') {
                sum += n;
                System.out.println("found.");
                break;
              }
            }
            // check right below
            c = schematic[i][row+1];
            if (!Character.isDigit(c) && c != '.') {
              sum += n;
              System.out.println("found.");
              break;
            }
            
            if (i != line.length() - 1) {
              // check lower right
              c = schematic[i+1][row+1];
              if (!Character.isDigit(c) && c != '.') {
                sum += n;
                System.out.println("found.");
                break;
              }
            }

          }
        }
        // if found an adjacent symbol, we break to here
      }
      row++;
    }
    System.out.println(sum);
  }
}

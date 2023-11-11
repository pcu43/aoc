import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class AOC2303_2 {
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
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      for (int j = 0; j < line.length(); j++) {
        // look for gears (*)
        char c = line.charAt(j);
        if (c == '*') {
          System.out.printf("found potential gear at (%d,%d)\n", j, i);
          /*
            i-1 [ ][ ][ ]
            i   [ ][*][ ]
            i+1 [ ][ ][ ]

            look for numbers in string above and check if adjacent
              in order to be adjacent, * j position must fall between start and end of number inclusive
            look for numbers in string and check if adjacent
              in order to be adjacent, number must start at j+1 or end at j-1 position
            look for numbers in string below and check if adjacent
              in order to be adjacent, * j position must fall between start and end of number inclusive

            we need to find 2 numbers in order for a * to be a gear

          */
          List<Integer> adjacentNumbers = new ArrayList<Integer>();
          String s = "";
          Matcher m = null;
          if (i > 0) {
            // check row above
            s = lines.get(i-1);
            m = p.matcher(s);
            while (m.find()) {
              int start = m.start();
              int end = m.end();
              if (start >= j-1 && start <= j+1) {
                adjacentNumbers.add(Integer.parseInt(m.group(1)));
              } else if (end >= j && end <= j+1) {
                adjacentNumbers.add(Integer.parseInt(m.group(1)));
              }
            }
          }
          // check same line
          s = lines.get(i);
          m = p.matcher(s);
          while (m.find()) {
            int start = m.start();
            int end = m.end();
            if (end == j || start == j+1) {
              adjacentNumbers.add(Integer.parseInt(m.group(1)));
            }
          }

          if (i != lines.size() - 1) {
            // check row below
            s = lines.get(i+1);
            m = p.matcher(s);
            while (m.find()) {
              int start = m.start();
              int end = m.end();
              if (start >= j-1 && start <= j+1) {
                adjacentNumbers.add(Integer.parseInt(m.group(1)));
              } else if (end >= j && end <= j+1) {
                adjacentNumbers.add(Integer.parseInt(m.group(1)));
              }
            }

          }
          // after all checks, verfiy adjacentNumbers.size == 2. if it is less than 2, it is not a gear. if it is greater than 2, then ????
          if (adjacentNumbers.size() == 2) {
            System.out.printf("adjacentNumbers.size()=%d, %d, %d\n", adjacentNumbers.size(), adjacentNumbers.get(0), adjacentNumbers.get(1));
            int gearRatio = adjacentNumbers.get(0) * adjacentNumbers.get(1);
            sum += gearRatio;
          } else {
            System.out.printf("adjacentNumbers.size()=%d\n", adjacentNumbers.size());
          }
          
        }
      }
    }
    System.out.println(sum);
  }
}

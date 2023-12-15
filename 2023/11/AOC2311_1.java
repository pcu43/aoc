import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.awt.Point;

public class AOC2311_1 {
  public static void main(String[] args) throws Exception {

    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    char[][] galaxy = new char[lines.get(0).length()][lines.size()];

    for (int y = 0; y < galaxy[0].length; y++) {
      String line = lines.get(y);
      for (int x = 0; x < galaxy.length; x++) {
        galaxy[x][y] = line.charAt(x);
      }
    }
    
    List<Integer> yExpands = new ArrayList<>();
    for (int y = 0; y < galaxy[0].length; y++) {
      boolean noGalaxies = true;
      for (int x = 0; x < galaxy.length; x++) {
        if (galaxy[x][y] == '#') {
          noGalaxies = false;
          break;
        }
      }
      if (noGalaxies) yExpands.add(y);
    }

    List<Integer> xExpands = new ArrayList<>();
    for (int x = 0; x < galaxy.length; x++) {
      boolean noGalaxies = true;
      for (int y = 0; y < galaxy[0].length; y++) {
        if (galaxy[x][y] == '#') {
          noGalaxies = false;
          break;
        }
      }
      if (noGalaxies) xExpands.add(x);
    }

    System.out.println("xExpands=" + Arrays.toString(xExpands.toArray()));
    System.out.println("yExpands=" + Arrays.toString(yExpands.toArray()));

    // expand y by adding empty row twice
    int offset = 0;
    for (int i : yExpands) {
      String blankLine = lines.get(i + offset);
      lines.add(i + offset, blankLine);
      offset++;
    }

    // expand x using StringBuilder
    for (int y = 0; y < lines.size(); y++) {
      StringBuilder sb = new StringBuilder(lines.get(y));
      offset = 0;
      for (int i : xExpands) {
        sb.insert(i + offset, '.');
        offset++;
      }
      lines.set(y, sb.toString());
    }

    for (String line : lines) {
      System.out.println(line);
    }

    galaxy = new char[lines.get(0).length()][lines.size()];
    for (int y = 0; y < galaxy[0].length; y++) {
      String line = lines.get(y);
      for (int x = 0; x < galaxy.length; x++) {
        galaxy[x][y] = line.charAt(x);
      }
    }

    // find all the galaxies
    List<Point> galaxies = new ArrayList<>();
    for (int y = 0; y < galaxy[0].length; y++) {
      for (int x = 0; x < galaxy.length; x++) {
        if (galaxy[x][y] == '#') {
          Point p = new Point(x, y);
          galaxies.add(p);
        }
      }
    }

    System.out.println("galaxies.size()=" + galaxies.size());
    int sum = 0;
    for (int i = 0; i < galaxies.size(); i++) {
      Point from = galaxies.get(i);
      for (int j = i + 1; j < galaxies.size(); j++) {
        Point to = galaxies.get(j);
        sum += (Math.abs((int)from.getX() - (int)to.getX()) + Math.abs(from.getY() - to.getY()));
      }
    }
    System.out.println("sum=" + sum);

  }
}

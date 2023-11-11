import java.util.*;
import java.nio.file.*;
import java.io.IOException;
public class AOC2302_2 {
  public static final int MAX_RED = 12;
  public static final int MAX_GREEN = 13;
  public static final int MAX_BLUE = 14;
  public static void main(String[] args) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    int sum = 0;
    for (String line : lines) {
      String[] game = line.split("[:]");
      String[] games = game[1].split("[;]");
      sum += minPossible(games);
    }
    System.out.println(sum);
  }
  public static int minPossible(String[] games) {
    int minRed = 0;
    int minGreen = 0;
    int minBlue = 0;
    for (int i = 0; i < games.length; i++) {
      String[] tokens = games[i].split("[,]");
      for (int j = 0; j < tokens.length; j++) {
        // each token is a color result
        String[] s = tokens[j].strip().split("[ ]");
        // s[0] = number
        // s[1] = color
        int n = Integer.parseInt(s[0]);
        switch (s[1]) {
          case "red":
            if (n > minRed) minRed = n;
            break;
          case "green":
            if (n > minGreen) minGreen = n;
            break;
          case "blue":
            if (n > minBlue) minBlue = n;
            break;
        }
      }
    }
    return minRed * minGreen * minBlue;
  }
}

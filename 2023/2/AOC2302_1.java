import java.util.*;
import java.nio.file.*;
import java.io.IOException;
public class AOC2302_1 {
  public static final int MAX_RED = 12;
  public static final int MAX_GREEN = 13;
  public static final int MAX_BLUE = 14;
  public static void main(String[] args) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    int sum = 0;
    for (String line : lines) {
      String[] game = line.split("[:]");
      String[] games = game[1].split("[;]");
      int gameid = Integer.parseInt(game[0].split("[ ]")[1]);
      //System.out.printf("game %d played %d games\n", gameid, games.length);
      if (possible(games)) {
        sum += gameid;
      }
    }
    System.out.println(sum);
  }
  public static boolean possible(String[] games) {
    for (int i = 0; i < games.length; i++) {
      String[] tokens = games[i].split("[,]");
      for (int j = 0; j < tokens.length; j++) {
        // each token is a color result
        String[] s = tokens[j].strip().split("[ ]");
        // s[0] = number
        // s[1] = color
        //System.out.printf("%s=%s\n", s[1], s[0]);
        //System.out.println(tokens[j]);
        int n = Integer.parseInt(s[0]);
        switch (s[1]) {
          case "red":
            if (n > MAX_RED) return false;
            break;
          case "green":
            if (n > MAX_GREEN) return false;
            break;
          case "blue":
            if (n > MAX_BLUE) return false;
            break;
        }
      }
    }
    return true;
  }
}

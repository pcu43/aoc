import java.util.*;
import java.io.*;
import java.nio.file.*;

public class AOC2306_1 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    int[] times = null;
    int[] distances = null;
    for (String line : lines) {
      String[] tokens = line.split("\\s+");
      if (tokens[0].equals("Time:")) {
        int numRaces = tokens.length - 1;
        times = new int[numRaces];
        for (int i = 0; i < numRaces; i++) {
          times[i] = Integer.parseInt(tokens[i+1]);
        }
      } else if (tokens[0].equals("Distance:")) {
        int numRaces = tokens.length - 1;
        distances = new int[numRaces];
        for (int i = 0; i < numRaces; i++) {
          distances[i] = Integer.parseInt(tokens[i+1]);
        }
      } else {
        System.out.println("oops something went wrong.");
        System.exit(-1);
      }

    }
    System.out.printf("times: %s\n", Arrays.toString(times));
    System.out.printf("distances: %s\n", Arrays.toString(distances));
    int[] totalWins = new int[times.length];
    for (int i = 0; i < times.length; i++) {
      int t = times[i];
      int min = distances[i]; // need to beat this distance
      int speed = 0;
      int wins = 0;
      // d = (speed * timeRemaining)
      while (t > 0) {
        speed++;
        t--;
        int d = speed * t;
        System.out.printf("speed=%d, t=%d, d=%d...", speed, t, d);
        if (d > min) {
          wins++;
          System.out.println("win");
        } else {
          System.out.println("lose");
        }
      }
      totalWins[i] = wins;
    }
    System.out.println(Arrays.toString(totalWins));
    int v = 1;
    for (int i = 0; i < totalWins.length; i++) {
      v *= totalWins[i];
    }
    System.out.println(v);
  }
}

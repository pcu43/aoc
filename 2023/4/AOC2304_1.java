import java.util.*;
import java.io.*;
import java.nio.file.*;
public class AOC2304_1 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    int sum = 0;
    for (String line : lines) {
      String[] tokens = line.split("[:|]");
      //System.out.printf("%s %s %s\n", tokens[0], tokens[1], tokens[2]);
      List<String> winningNumbers = Arrays.asList(tokens[1].strip().split("\\s+"));
      List<String> numbers = Arrays.asList(tokens[2].strip().split("\\s+"));
      //System.out.printf("%d %d\n", winningNumbers.size(), numbers.size());
      int i = 0;
      for (String number : numbers) {
        if (winningNumbers.contains(number)) {
          if (i == 0) i = 1; else i *= 2;
        }
      }
      sum += i;
    }
    System.out.println(sum);
  }
}

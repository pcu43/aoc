import java.io.IOException;
import java.nio.file.*;
import java.util.*;
public class AOC2301 {
  public static void main(String[] args) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    int sum = 0;
    for (String line : lines) {
      int first = 0;
      int last = 0;  
      for (int i = 0; i < line.length(); i++) {
        if (Character.isDigit(line.charAt(i))) {
          first = line.charAt(i);
          break;
        }
      }
      for (int i = line.length() - 1; i >= 0; i--) {
        if (Character.isDigit(line.charAt(i))) {
          last = line.charAt(i);
          break;
        }
      }
      String n = Character.toString(first) + Character.toString(last);
      System.out.println(n);
      sum += Integer.parseInt(n);
    }
    System.out.println(sum);
  }
}

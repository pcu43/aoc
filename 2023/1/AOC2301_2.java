import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
public class AOC2301_2 {
  public static final Map<String,Integer> numbers = new HashMap<>();
  static {
    numbers.put("one",1);
    numbers.put("two",2);
    numbers.put("three",3);
    numbers.put("four",4);
    numbers.put("five",5);
    numbers.put("six",6);
    numbers.put("seven",7);
    numbers.put("eight",8);
    numbers.put("nine",9);
    numbers.put("1",1);
    numbers.put("2",2);
    numbers.put("3",3);
    numbers.put("4",4);
    numbers.put("5",5);
    numbers.put("6",6);
    numbers.put("7",7);
    numbers.put("8",8);
    numbers.put("9",9);
  }
  public static void main(String[] args) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    int sum = 0;
    Pattern p = Pattern.compile("([1-9]|one|two|three|four|five|six|seven|eight|nine)");
    for (String line : lines) {
      Matcher m = p.matcher(line);
      String first = null;
      String last = null;
      String s = "";
      int i = 0;
      while (m.find(i)) {
        if (first == null) {
          first = m.group();
        }
        last = m.group();
        i = m.start() + 1;
      }

        System.out.printf("first=%s, last=%s\n", first, last);
        if (first != null) {
          if (numbers.containsKey(first)) {
            int n1 = numbers.get(first);
            s = n1 + "";
          } else { System.out.println("map doens't cotain key " + first); }
        }
        if (last != null) {
          if (numbers.containsKey(last)) {
            int n1 = numbers.get(last);
            s += n1;
          } else { System.out.println("map doens't cotain key " + last); }
        }
        if (s != null && s.length() > 0) {
          System.out.println("adding " + s + " to sum.");
          sum += Integer.parseInt(s);
        }
        System.out.println("sum="+sum);
      }
    System.out.println(sum);
  }
}

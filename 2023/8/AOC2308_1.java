import java.time.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.nio.file.*;

class Element {
  String id;
  String left;
  String right;
  public Element(String id, String left, String right) {
    this.id = id;
    this.left = left;
    this.right = right;
  }
  public String getId() { return id; }
  public String getLeft() { return left; }
  public String getRight() { return right; }
}
public class AOC2308_1 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    String map = lines.get(0);
    // lines.get(1) should be a blank line
    Pattern p = Pattern.compile("([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)");
    Map<String,Element> elements = new HashMap<>();
    String currentElement = null;
    for (int i = 2; i < lines.size(); i++) {
      String line = lines.get(i);
      Matcher m = p.matcher(line);
      while (m.find()) {
        Element e = new Element(m.group(1), m.group(2), m.group(3));
        elements.put(e.getId(), e);
        if (i == 2) currentElement = e.getId();
        System.out.printf("%s %s %s\n", e.getId(), e.getLeft(), e.getRight());
      }
    }
    int step = 0;
    int mapLength = map.length();
System.out.println("**** " + elements.size() + " element(s) ****");
System.out.println("**** starting at " + currentElement + " ****");
    while (!currentElement.equals("ZZZ")) {
      char dir = map.charAt(step % mapLength);
//      System.out.printf("step=%d, mapLength=%d, step %% mapLength=%d, dir=%s\n", step, mapLength, step%mapLength, dir);
      Element e = elements.get(currentElement);
      if (dir == 'L') {
        currentElement = e.getLeft();
      } else if (dir == 'R') {
        currentElement = e.getRight();
      } else {
        System.out.println("oops something went wrong");
        System.exit(-1);
      }
      step++;
      //System.out.println(currentElement);
      if (step % 1000000000 == 0) System.out.println(LocalDateTime.now());
    }
    System.out.println(step);
  }
}

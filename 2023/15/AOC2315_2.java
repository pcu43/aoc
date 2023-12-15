import java.io.*;
import java.nio.file.*;
import java.util.*;

class Lens {
  String id;
  int val;
  public Lens(String id, int val) {
    this.id = id;
    this.val = val;
  }
  public String getId() { return id; }
  public int getVal() { return val; }
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Lens)) return false;
    Lens other = (Lens) o;
    return this.id.equals(other.getId());
  }
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  @Override
  public String toString() {
    return String.format("%s %d", id, val);
  }
}
public class AOC2315_2 {
  public static void main(String[] args) throws Exception {
    String line = new String(Files.readAllBytes(Paths.get(args[0])));
    String[] tokens = line.strip().split("[,]");
    //System.out.println(tokens.length);
    int sum = 0;
    ArrayList<Lens>[] boxes = new ArrayList[256];
    for (int i = 0; i < boxes.length; i++) {
      boxes[i] = new ArrayList<Lens>();
    }
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].endsWith("-")) {
        String label = tokens[i].substring(0, tokens[i].length() - 1);
        int box = HASH(label);
        System.out.println("we need to remove " + label + " from box " + box);
        Lens l = new Lens(label, 0);
        if (boxes[box].contains(l)) {
          boxes[box].remove(l);
        }
      } else {
        String[] subtokens = tokens[i].split("[=]");
        String label = subtokens[0];
        int box = HASH(subtokens[0]);
        System.out.println("we need to add " + label + " " + subtokens[1] + " to box " + box);
        Lens l = new Lens(label, Integer.parseInt(subtokens[1]));
        if (boxes[box].contains(l)) {
          int index = boxes[box].indexOf(l);
          boxes[box].set(index, l);
        } else {
          boxes[box].add(l);
        }
      }
      //int val = HASH(tokens[i]);
      //System.out.println(val);
      //sum += val;
    }
    //System.out.println(sum);
    // display our boxes
    for (int i = 0; i < boxes.length; i++) {
      if (boxes[i].size() > 0) { // ArrayList has at least 1 element
        System.out.printf("[%d] %s\n", i, Arrays.toString(boxes[i].toArray()));
        for (int j = 0; j < boxes[i].size(); j++) {
          int a = i + 1; // box number of lens + 1
          int b = j + 1; // slot number starting from 1
          int c = boxes[i].get(j).getVal(); // focal length of lens
          int d = a * b * c;
          System.out.println("power=" + d);
          sum += d;
        }
      }
    }
    System.out.println(sum);
  }
  public static int HASH(String s) {
    int currentValue = 0;
    for (int i = 0; i < s.length(); i++) {
      int asciiVal = (int) s.charAt(i);
      currentValue += asciiVal;
      currentValue *= 17;
      currentValue %= 256;
    }
    return currentValue;
  }
}

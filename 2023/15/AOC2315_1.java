import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC2315_1 {
  public static void main(String[] args) throws Exception {
    String line = new String(Files.readAllBytes(Paths.get(args[0])));
    String[] tokens = line.strip().split("[,]");
    //System.out.println(tokens.length);
    int sum = 0;
    for (int i = 0; i < tokens.length; i++) {
      System.out.print("HASH(" + tokens[i] + ")=");
      int val = HASH(tokens[i]);
      System.out.println(val);
      sum += val;
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

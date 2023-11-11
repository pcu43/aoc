import java.util.regex.*;
public class Dog {
  public static void main(String[] args) {
    String line = args[0];
    Pattern p = Pattern.compile("([0-9]).*([0-9])");
    Matcher m = p.matcher(line);
    while (m.find()) {
      System.out.println(m.group() + "," + m.group(1) + "," + m.group(2)); 
    }
  }
}

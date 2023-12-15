import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class AOC2312_1 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get(args[0]));

System.out.println("******************************************************************************************");
System.out.println("******************************************************************************************");
System.out.println("******************************************************************************************");
System.out.println("******************************************************************************************");
    int sum = 0;
    for (String line : lines) {
      String[] tokens = line.split("\\s+");
      String[] values = tokens[1].split("[,]");
      List<Integer> intValues = Arrays.asList(values).stream().map(Integer::parseInt).collect(Collectors.toList());
      System.out.printf("%s : %s\n", line, Arrays.toString(intValues.toArray()));
      Stack<Integer> broke = new Stack<>();
      for (int i = intValues.size() - 1; i >= 0; i--) {
        int j = intValues.get(i);
        broke.push(j);
      }
      int matches =  matchme(tokens[0], broke);
      sum += matches;
      System.out.printf("[%d]\n", matches);
    }
    System.out.println(sum);

    // TESTING
    /*
    String s = "?###????????";
    Pattern p = Pattern.compile("[?#]{3}[?.]+[?#]{2}[?.]+[?#]{1}");
    Matcher m = p.matcher(s);
    if (m.find()) {
      do {
        System.out.println(m.group());
      } while (m.find(m.start() + 1));
    }
    */
  }

  public static int matchme(String s, Stack<Integer> broke) {
    int n = broke.pop();
    System.out.printf("looking for match %d in %s, %d group(s) left.\n", n, s, broke.size());
    int matches = 0;
    int match = 0;
    int matchStart = 0;
    for (int i = 0; i < s.length(); i++) {
      //System.out.printf("[DEBUG] i=%d, match=%d\n", i, match);
      // can we find a match starting from s[i]
      char c = s.charAt(i);
      //System.out.printf("current char is '%s'\n", c);
      if (c == '.') {
        // cannot use this, seq is broke
        match = 0;
        continue;
      } else if (c == '#' || c == '?') {
        // if pattern start, make sure char at i - 1 is not a # sign
        if (match == 0) {
          if (i > 0 && s.charAt(i-1) == '#') {
            // cannot start here because it would join previous group
            System.out.println("match cannot start here because previous char is a #");
            continue;
          } else {
            matchStart = i;
          }
        }
        match++;
        if (match == n) {
          // we found a fit
          if (i == s.length() - 1 || (s.charAt(i+1) == '.' || s.charAt(i+1) == '?')) {
            // fit is good. do we need to match more?
            System.out.printf("\twe found a fit that is at the end of the string or can be followed by a .\n");
            if (!broke.empty()) {
              // recurse
              System.out.printf("\tstill %d more group(s) to go so recursing.\n", broke.size());
              if (i + 2 < s.length()) {
                int m = matchme(s.substring(i+2), broke);
                matches += m;
              }
              match = 0;
              i = matchStart;
            } else {
              System.out.println("no more groups so this is a fit.");
              matches++;
              System.out.println("matches is now " + matches);
              match = 0;
              i = matchStart;
            }
          } else {
            // match is too long, won't fit
            match = 0;
            i = matchStart;
          }
        }
      }
    }
    broke.push(n);
    return matches;
  }
}

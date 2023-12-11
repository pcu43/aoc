import java.math.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class AOC2306_2 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    String strTime = "";
    String strDistance = "";
    for (String line : lines) {
      String[] tokens = line.split("\\s+");
      if (tokens[0].equals("Time:")) {
        for (int i = 1; i < tokens.length; i++) {
          strTime += tokens[i];
        }
      } else if (tokens[0].equals("Distance:")) {
        for (int i = 1; i < tokens.length; i++) {
          strDistance += tokens[i];
        }
      } else {
        System.out.println("oops something went wrong.");
        System.exit(-1);
      }
    }
    System.out.printf("time=%s, distance=%s\n", strTime, strDistance);
    BigInteger t = new BigInteger(strTime);
    BigInteger goal = new BigInteger(strDistance);
    BigInteger speed = BigInteger.ZERO;
    long wins = 0;
    while (!t.equals(BigInteger.ZERO)) {
      t = t.subtract(BigInteger.ONE);
      speed = speed.add(BigInteger.ONE);
      BigInteger d = t.multiply(speed);
      if (d.compareTo(goal) == 1) wins++;
    }
    System.out.println(wins);
  }
}

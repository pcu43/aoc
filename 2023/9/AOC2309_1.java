import java.io.*;
import java.util.*;
import java.nio.file.*;

public class AOC2309_1 {
  public static void main(String[] args) throws Exception {

    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    int sum = 0;

    for (String line : lines) {

      //String line = lines.get(0);
      System.out.println("----------------------------------------");
      System.out.println(line);
      System.out.println("----------------------------------------");
      Stack<List<Integer>> allLayers = new Stack<>();
      Stack<List<Integer>> completedLayers = new Stack<>();

      // load first layer
      String[] tokens = line.split("\\s+");
      List<Integer> l = new ArrayList<>();
      for (int i = 0; i < tokens.length; i++) {
        l.add(Integer.parseInt(tokens[i]));
      }
      allLayers.push(l);
      //System.out.println(Arrays.toString(l.toArray()));

      // calculate following layers
      l = allLayers.peek();
      //System.out.println("all zeros? " + allZeros(l));

      while (!allZeros(l)) {

        // calculate next layer
        List<Integer> newLayer = new ArrayList<Integer>();
        for (int i = 0; i < l.size() - 1; i++) {
          int a = l.get(i);
          int b = l.get(i+1);
          int diff = b - a;
          newLayer.add(diff);
        }

        // push new layer
        allLayers.push(newLayer);

        l = allLayers.peek();

      }

      // test
      //while (!allLayers.empty()) {
      //  l = allLayers.pop();
      //  System.out.println(Arrays.toString(l.toArray()));
      //}

      // once the last layer is all zeros, now we calculate the extrapolated values
      l = allLayers.pop(); // pop off all zero layer
      l.add(0); // we already know the extrapolated value is 0
      completedLayers.push(l); // all zero layer is complete

      int lastExtrapolated = 0;
      while (!allLayers.empty()) {
        l = completedLayers.peek();
        List<Integer> currentLayer = allLayers.pop();
        int diff = l.get(l.size() - 1); // last element of last completed layer
        int v1 = currentLayer.get(currentLayer.size() - 1); // last element of current layer to complete
        // x - v1 = diff
        int x = diff + v1;
        currentLayer.add(x);
        completedLayers.push(currentLayer);
        lastExtrapolated = x;
      }

      // test
      while (!completedLayers.empty()) {
        l = completedLayers.pop();
        System.out.println(Arrays.toString(l.toArray()));
      }
      sum += lastExtrapolated;

    }
    System.out.println(sum);

  }
  public static final boolean allZeros(List<Integer> list) {
    for (int i : list) {
      if (i != 0) return false;
    }
    return true;
  }
}

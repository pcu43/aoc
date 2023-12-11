import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class SeedObj {
  String id;
  long start;
  long range;
  public SeedObj(long start, long range) {
    this.id = Long.toString(start);
    this.start = start;
    this.range = range;
  }
  public String toString() {
    return String.format("id=%s, start=%d, range=%d", id, start, range);
  }
  public boolean contains(long l) {
    if (l >= start && l < start + range) {
      return true;
    }
    return false;
  }
}

class MapObj {
  String dst;
  String src;
  String range;
  public MapObj(String dst, String src, String range) {
    this.dst = dst;
    this.src = src;
    this.range = range;
  }
  public String getMapping(String obj) {
    long l = Long.parseLong(obj);
    long srcStart = Long.parseLong(src);
    long dstStart = Long.parseLong(dst);
    long rng = Long.parseLong(range);
    if (l >= srcStart && l < srcStart + rng) {
      long delta = l - srcStart;
      return Long.toString(dstStart + delta);
    }
    return null;
  }
  public String getReverseMapping(String obj) {
    long l = Long.parseLong(obj);
    long srcStart = Long.parseLong(src);
    long dstStart = Long.parseLong(dst);
    long rng = Long.parseLong(range);
    if (l >= dstStart && l < dstStart + rng) {
      long delta = l - dstStart;
      return Long.toString(srcStart + delta);
    }
    return null;
  }
  public String toString() {
    return String.format("dst=%s, src=%s, range=%s", dst, src, range);
  }
}

public class AOC2305_2 {

  public static final int SEED_TO_SOIL = 0;
  public static final int SOIL_TO_FERTILIZER = 1;
  public static final int FERTILIZER_TO_WATER = 2;
  public static final int WATER_TO_LIGHT = 3;
  public static final int LIGHT_TO_TEMPERATURE = 4;
  public static final int TEMPERATURE_TO_HUMIDITY = 5;
  public static final int HUMIDITY_TO_LOCATION = 6;

  public static void main(String[] args) throws Exception {

    List<String> lines = Files.readAllLines(Paths.get("input.txt"));

    List<SeedObj> seeds = new ArrayList<>();

    // 7 maps
    Map<Integer,List<MapObj>> maps = new HashMap<>();

    // build maps
    int mapIndex = 0;
    for (String line : lines) {
      if (line.startsWith("seeds: ")) {
        String[] tokens = line.split("[\\s+]");
        for (int i = 1; i < tokens.length; i+=2) {
          SeedObj so = new SeedObj(Long.parseLong(tokens[i]), Long.parseLong(tokens[i+1]));
          seeds.add(so);
        }
      } else if (line.startsWith("seed-to-soil map:")) {
        mapIndex = SEED_TO_SOIL;
      } else if (line.startsWith("soil-to-fertilizer map:")) {
        mapIndex = SOIL_TO_FERTILIZER;
      } else if (line.startsWith("fertilizer-to-water map:")) {
        mapIndex = FERTILIZER_TO_WATER;
      } else if (line.startsWith("water-to-light map:")) {
        mapIndex = WATER_TO_LIGHT;
      } else if (line.startsWith("light-to-temperature map:")) {
        mapIndex = LIGHT_TO_TEMPERATURE;
      } else if (line.startsWith("temperature-to-humidity map:")) {
        mapIndex = TEMPERATURE_TO_HUMIDITY;
      } else if (line.startsWith("humidity-to-location map:")) {
        mapIndex = HUMIDITY_TO_LOCATION;
      } else {
        if (line != null && line.strip().length() > 0) {
          String[] tokens = line.split("[\\s+]");
          if (tokens.length != 3) {
            System.out.println("huh");
          } else {
            String dstStart = tokens[0];
            String srcStart = tokens[1];
            String range    = tokens[2];
            List<MapObj> objs = maps.get(mapIndex);
            if (objs == null) objs = new ArrayList<MapObj>();
            objs.add(new MapObj(dstStart, srcStart, range));
            maps.put(mapIndex, objs);
          }
        }
      }
    }


    for (int i = 0; i < 7; i++) {
      System.out.println(Arrays.toString(maps.get(i).toArray()));
    }

    System.out.printf("seeds: %s\n", Arrays.toString(seeds.toArray()));

    // start from 0 until seed is found
    boolean found = false;
    long dog = 0; // this is the location that we need to reverse map to the seed
    while (!found) {

      String next = Long.toString(dog);

//      System.out.printf("*** reverse mapping seed %s ***\n", next);

        for (int i = 6; i >= 0; i--) {
          List<MapObj> objs = maps.get(i);
          for (MapObj mo : objs) {
            String s = mo.getReverseMapping(next);
            if (s != null) {
              // set next
//            System.out.printf("%s maps to %s\n", next, s);
              next = s;
              // break out of loop and move to next map
              break;
            }
          }
        }

      // now we're done with the maps
      // check if next is a valid seed
      long n = Long.parseLong(next);
      for (SeedObj so : seeds) {
        if (so.contains(n)) {
          found = true;
          System.out.println(n);
        }
      }
      dog++;
    }
  }
}

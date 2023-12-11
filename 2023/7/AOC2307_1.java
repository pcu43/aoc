import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Hand {

  public static final int FIVE_OF_A_KIND = 7;
  public static final int FOUR_OF_A_KIND = 6;
  public static final int FULL_HOUSE = 5;
  public static final int THREE_OF_A_KIND = 4;
  public static final int TWO_PAIR = 3;
  public static final int ONE_PAIR = 2;
  public static final int HIGH_CARD = 1;

  String cards;
  int bid;
  public Hand(String cards, int bid) {
    this.cards = cards;
    this.bid = bid;
  }
  public String getCards() { return cards; }
  public int getBid() { return bid; }
  public int getHandStrength() {
    Map<Character,Long> cardMap = cards.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    if (cardMap.size() == 1) {
      return FIVE_OF_A_KIND;
    }
    if (cardMap.size() == 2) {
      // could either be 4 of a kind or full house
      for (Map.Entry<Character,Long> entry : cardMap.entrySet()) {
        if (entry.getValue() == 4) {
          return FOUR_OF_A_KIND;
        }
      }
      return FULL_HOUSE;
    }
    if (cardMap.size() == 3) {
      // could either be two pair or three of a kind
      for (Map.Entry<Character,Long> entry : cardMap.entrySet()) {
        if (entry.getValue() == 3) {
          return THREE_OF_A_KIND;
        }
      }
      return TWO_PAIR;
    }
    if (cardMap.size() == 4) {
      return ONE_PAIR;
    }
    if (cardMap.size() == 5) {
      return HIGH_CARD;
    }
    return 0;
  }
}

class SortByStrength implements Comparator<Hand> {
  public static final Map<Character,Integer> values = new HashMap<>();
  static {
    values.put('2', 2);
    values.put('3', 3);
    values.put('4', 4);
    values.put('5', 5);
    values.put('6', 6);
    values.put('7', 7);
    values.put('8', 8);
    values.put('9', 9);
    values.put('T', 10);
    values.put('J', 11);
    values.put('Q', 12);
    values.put('K', 13);
    values.put('A', 14);
  }
  public int compare(Hand h1, Hand h2) {

    int hs1 = h1.getHandStrength();
    int hs2 = h2.getHandStrength();
    if (hs1 - hs2 != 0) return hs1 - hs2;

    // same hand so compare cards
    String s1 = h1.getCards();
    String s2 = h2.getCards();

    for (int i = 0; i < s1.length(); i++) {
      char c1 = s1.charAt(i);
      char c2 = s2.charAt(i);
      int v1 = values.get(c1);
      int v2 = values.get(c2);
      if (v1 - v2 != 0) return v1 - v2;
    }

    return 0; // they are the exact same
  }
}

public class AOC2307_1 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    List<Hand> hands = new ArrayList<>();
    for (String line : lines) {
      String[] tokens = line.split("\\s+");
      Hand h = new Hand(tokens[0], Integer.parseInt(tokens[1]));
      hands.add(h);
    }
    Collections.sort(hands, new SortByStrength());
    int totalWinnings = 0;
    for (int i = 0; i < hands.size(); i++) {
      int rank = i + 1;
      Hand h = hands.get(i);
      int winnings = h.getBid() * rank;
      totalWinnings += winnings;
    }
    System.out.println(totalWinnings);
  }
}

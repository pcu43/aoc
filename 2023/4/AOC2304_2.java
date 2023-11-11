import java.util.*;
import java.io.*;
import java.nio.file.*;
public class AOC2304_2 {
  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("input.txt"));
    int totalCardsWon = 0;
    Queue<Integer> wonCards = new LinkedList<>();
    for (String line : lines) {
      String[] tokens = line.split("[:|]");
      int cardNumber = Integer.parseInt(tokens[0].strip().split("\\s+")[1]);
      //System.out.println("Processing card " + cardNumber);
      List<String> winningNumbers = Arrays.asList(tokens[1].strip().split("\\s+"));
      List<String> numbers = Arrays.asList(tokens[2].strip().split("\\s+"));
      int i = 0;
      for (String number : numbers) {
        if (winningNumbers.contains(number)) {
          i++;
        }
      }
      for (int j = 1; j <= i; j++) {
        wonCards.add(cardNumber + j);
      }
      totalCardsWon++;
    }
    System.out.println("total cards won after first round: " + totalCardsWon);
    // now process all the cards in the queue
    while (!wonCards.isEmpty()) {
      int currentCard = wonCards.remove();
      // 1 = 0, 2 = 1, etc...
      String line = lines.get(currentCard - 1);
      String[] tokens = line.split("[:|]");
      int cardNumber = Integer.parseInt(tokens[0].strip().split("\\s+")[1]);
      //System.out.println("Processing card " + cardNumber);
      List<String> winningNumbers = Arrays.asList(tokens[1].strip().split("\\s+"));
      List<String> numbers = Arrays.asList(tokens[2].strip().split("\\s+"));
      int i = 0;
      for (String number : numbers) {
        if (winningNumbers.contains(number)) {
          i++;
        }
      }
      for (int j = 1; j <= i; j++) {
        wonCards.add(cardNumber + j);
      }
      totalCardsWon++;
    }
    System.out.println(totalCardsWon);
  }
}

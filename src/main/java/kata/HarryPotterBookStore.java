package kata;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class HarryPotterBookStore {

    private static final Map<Integer, String> books;
    private static final Map<Integer, Double> discounts;
    private static final double BOOK_COST = 8;

    static {
        books = new HashMap<>();
        books.put(0, "The Philosopher's Stone");
        books.put(1, "The Chamber of Secrets ");
        books.put(2, "The Prisoner of Azkaban");
        books.put(3, "The Goblet of Fire");
        books.put(4, "The Order of the Phoenix");

        discounts = new HashMap<>();
        discounts.put(0, 0.0);
        discounts.put(1, 0.0);
        discounts.put(2, 0.05);
        discounts.put(3, 0.10);
        discounts.put(4, 0.20);
        discounts.put(5, 0.25);
    }

    public static double checkoutJava8Streams(List<Integer> books) {
        Map<Integer, Long> bookToOccurrences = books.stream().collect(groupingBy(book -> book, counting()));
        int maxDuplicateCount = bookToOccurrences.entrySet().stream().map(Map.Entry::getValue).mapToInt(Long::intValue).max().orElse(0);

        return IntStream.rangeClosed(1, maxDuplicateCount).mapToDouble(index -> {
            int bookCount = (int) bookToOccurrences.entrySet().stream().filter(entry -> entry.getValue() >= index).count();
            return (bookCount * BOOK_COST) * (1 - discounts.get(bookCount));
        }).sum();
    }

    public static double checkout(List<Integer> books) {
        Map<Integer, Integer> bookToOccurrences = new HashMap<>();
        Set<Map.Entry<Integer, Integer>> entries = bookToOccurrences.entrySet();
        int maxDuplicateCount = 0;
        double totalPrice = 0.0;

        for(int book : books) {
            if(!bookToOccurrences.containsKey(book)) {
                bookToOccurrences.put(book, 1);
            } else {
                bookToOccurrences.put(book, bookToOccurrences.get(book) + 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : entries) {
            if(entry.getValue() > maxDuplicateCount) {
                maxDuplicateCount = entry.getValue();
            }
        }

        for(int loopCount = 1;loopCount <= maxDuplicateCount;loopCount++) {
            int bookCount = 0;
            for(Map.Entry<Integer, Integer> entry : entries) {
                if(entry.getValue() >= loopCount) {
                    bookCount++;
                }
            }

            totalPrice += (bookCount * BOOK_COST) * (1 - discounts.get(bookCount));
        }
        return totalPrice;
    }
}

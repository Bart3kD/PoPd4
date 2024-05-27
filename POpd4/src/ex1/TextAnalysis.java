package ex1;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalysis {

    static long getWordCount(String text) {
        return Stream.of(text.split("\\s+")).count();
    }

    static long getUniqueWordCount(String text) {
        return Stream.of(text.split("\\s+")).distinct().count();
    }

    static double getAverageWordLength(String text) {
        return Stream.of(text.split("\\s+")).map(word -> word.replaceAll("[^a-zA-Z]", "")).mapToInt(String::length).average().orElse(0);
    }

    static String getLongestWord(String text) {
        return Stream.of(text.split("\\s+"))
                .max((word1, word2) -> Integer.compare(word1.length(), word2.length())) // Compare by word length
                .orElse(""); // Return an empty string if no words are found
    }

    static String getMostFrequentWord(String text) {
        return Stream.of(text.split("\\s+"))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    static Map<String, Long> getWordFrequency(String text) {
        return Stream.of(text.split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }
}

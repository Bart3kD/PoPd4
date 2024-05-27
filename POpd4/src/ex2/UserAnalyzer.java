package ex2;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserAnalyzer {

    private List<User> users;

    public UserAnalyzer(List<User> users) {
        this.users = users;
    }

    public double calculateAverageAge() {
        return users.stream()
                .mapToInt(User::getAge)
                .average().orElse(0.00);
    }

    public List<String> findCountriesWithMostUsers() {
        Map<String, Long> countriesCountMap = users.stream()
                .collect(Collectors.groupingBy(User::getCountry, Collectors.counting()));

        long maxCount = countriesCountMap.values().stream().max(Long::compareTo).orElse(0L);

        return countriesCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public String findMostCommonFirstName() {
        Map<String, Long> firstNameCountMap = users.stream()
                .collect(Collectors.groupingBy(User::getFirstName, Collectors.counting()));

        return firstNameCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public String findMostCommonLastName() {
        Map<String, Long> firstNameCountMap = users.stream()
                .collect(Collectors.groupingBy(User::getLastName, Collectors.counting()));

        return firstNameCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public User findOldestUser() {
        return users.stream()
                .max(Comparator.comparingInt(User::getAge))
                .orElse(null);
    }

    public User findYoungestUser() {
        return users.stream()
                .min(Comparator.comparingInt(User::getAge))
                .orElse(null);
    }

    public List<String> findUniqueCountries() {
        return users.stream()
                .map(User::getCountry)
                .distinct()
                .collect(Collectors.toList());
    }
}

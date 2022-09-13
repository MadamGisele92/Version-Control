/****@author gbrown62**/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
 
public class WordCount {
 
    public static void main(String[] args) throws IOException {
 
        // route to file 
        Path locate = Paths.get("C:\\Users\\gbrown62\\eclipse-workspace\\SDLC\\src\\TheRaven.txt"); 
 
 
        // This counts repeated occurrences
        Map<String, Long> wordMap = Files.lines(locate) // This reads every line in the doc.
                .parallel() 
                .flatMap(line -> Arrays.stream(line.trim().split(" "))) // This splits the words on space
                .map(word -> word.replaceAll("[^a-zA-Z]", "").trim()) // This removes the white-spaces
                .filter(word -> word.length() > 0) // Filters word that have greater length than 1
                .map(word -> new SimpleEntry<>(word, 1)) 
                .collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.counting()));
 
 
        // print to the console
        System.out.println("1. The most occurring words in descending order: ");
        wordMap
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(System.out::println);
    }
}
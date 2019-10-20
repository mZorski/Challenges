package _366_wordfunnel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * [2018-08-20] Challenge #366 [Easy] Word funnel 1
 * https://www.reddit.com/r/dailyprogrammer/comments/98ufvz/20180820_challenge_366_easy_word_funnel_1/
 */

public class WordFunnel {
    private static final Set<String> dictionary = loadDictionary();

    public static void main(String[] args) {

        System.out.println("= = = = = Base test = = = = =");
        String[][] testBase = {
                {"leave", "eave"},
                {"reset", "rest"},
                {"dragoon", "dragon"},
                {"eave", "leave"},
                {"sleet", "lets"},
                {"skiff", "ski"}
        };
        for(String[] values : testBase)
            System.out.println(values[0] + ", " + values[1] + " -> " + isPossibleBase(values[0], values[1]));
        System.out.println();


        System.out.println("= = = = = Bonus1 test = = = = =");
        String[] testBonus1 = {"dragoon", "boats", "affidavit"};
        for(String value : testBonus1) {
            System.out.println(value + " = " + printCollection(possibleWords(value)));
        }
        System.out.println();


        System.out.println("= = = = = Bonus2 test = = = = =");
        Map<String, List<String>> bonus2Result = allPossibleWords();
        for(String base : new TreeSet<>(bonus2Result.keySet())) {
            System.out.println(base + " = " + printCollection(bonus2Result.get(base)));
        }
    }


    // Base
    private static boolean isPossibleBase(String first, String second) {
        if(first.length() - 1 != second.length())
            return false;


        for(int i = 0; i < first.length(); i++) {
            StringBuilder result = new StringBuilder(first);
            result.deleteCharAt(i);
            if(result.toString().equals(second))
                return true;
        }
        return false;
    }


    // Bonus 1
    private static Set<String> possibleWords(String word) {
        Set<String> found = new HashSet<>();

        for(int i = 0; i < word.length(); i++) {
            String result = word.substring(0, i) + word.substring(i + 1);
            if(dictionary.contains(result))
                found.add(result);
        }
        return found;
    }


    // Bonus 2
    private static Map<String, List<String>> allPossibleWords() {
        long start = System.currentTimeMillis();

        // Put words that have at least 5 characters (lesser won't be able to match requirements)
        Map<String, List<String>> words = dictionary
                .stream()
                .filter(word -> word.length() > 4)
                .collect(Collectors.toMap(String::toString, word -> new ArrayList<>(possibleWords(word))));

        // Filter entries that does not have 5 found words
        Map<String, List<String>> result = words
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == 5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        long stop = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (stop - start) + " ms");
        return result;
    }


    // Helper method to load dictionary
    private static Set<String> loadDictionary() {
        Set<String> dictionary = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("files/easy/366_wordFunnel.txt"))) {
            String line;
            while((line = reader.readLine()) != null)
                dictionary.add(line);
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully read " + dictionary.size() + " words!");
        return dictionary;
    }

    // Helper method to pretty print string collection as an array
    private static String printCollection(Collection<String> collection) {
        if(collection.isEmpty())
            return "{empty}";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for(String word : collection) {
            stringBuilder.append(word).append(", ");
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

}

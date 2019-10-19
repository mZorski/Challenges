package _366_wordfunnel;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class WordFunnel {
    private static final Set<String> dictionary = new HashSet<>();

    public static void main(String[] args) {
        String[][] test = {
                {"leave", "eave"},
                {"reset", "rest"},
                {"dragoon", "dragon"},
                {"eave", "leave"},
                {"sleet", "lets"},
                {"skiff", "ski"}
        };
        for(String[] values : test)
            System.out.println(isPossibleBase(values));
    }

    // Base
    private static boolean isPossibleBase(String first, String second) {
        if(first.length() <= second.length())
            return false;

        for(int i = 0; i < first.length(); i++) {
            char[] firstArray = first.toCharArray();
            firstArray[i] = ' ';
            String result = new String(firstArray).replaceAll(" ", "");
            if(result.equals(second))
                return true;
        }
        return false;
    }

    private static boolean isPossibleBase(String[] values) {
        return isPossibleBase(values[0], values[1]);
    }

    // Bonus 1
    private static String[] possibleWords(String word) {
        Set<String> found = new HashSet<>();

        for(int i = 0; i < word.length(); i++) {
            char[] wordArray = word.toCharArray();
            wordArray[i] = ' ';
            String result = new String(wordArray).replaceAll(" ", "");
            if(dictionary.contains(result))
                found.add(result);
        }
        return (String[]) found.toArray();
    }




    // Helper method to load dictionary
    private static void loadDictionary() {
        try(BufferedReader reader = new BufferedReader(new FileReader("WordFunnel1.txt"))) {
            String line;
            while((line = reader.readLine()) != null)
                dictionary.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

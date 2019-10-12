package perfectlybalanced;

import java.util.HashMap;
import java.util.Map;

/**
 * [2019-01-14] Challenge #372 [Easy] Perfectly balanced
 * https://www.reddit.com/r/dailyprogrammer/comments/afxxca/20190114_challenge_372_easy_perfectly_balanced/
 */

public class PerfectlyBalanced {
    public static void main(String[] args) {

        System.out.println(isBalancedBase("xxxyyy"));
        System.out.println(isBalancedBase("yyyxxx"));
        System.out.println(isBalancedBase("xxxyyyy"));
        System.out.println(isBalancedBase("yyxyxxyxxyyyyxxxyxyx"));
        System.out.println(isBalancedBase("xyxxxxyyyxyxxyxxyy"));
        System.out.println(isBalancedBase(""));
        System.out.println(isBalancedBase("x"));
        System.out.println("************");


        System.out.println(isBalancedBonus("xxxyyyzzz"));
        System.out.println(isBalancedBonus("abccbaabccba"));
        System.out.println(isBalancedBonus("xxxyyyzzzz"));
        System.out.println(isBalancedBonus("abcdefghijklmnopqrstuvwxyz"));
        System.out.println(isBalancedBonus("pqq"));
        System.out.println(isBalancedBonus("fdedfdeffeddefeeeefddf"));
        System.out.println(isBalancedBonus("www"));
        System.out.println(isBalancedBonus("x"));
        System.out.println(isBalancedBonus(""));
        System.out.println("************");
    }

    // base
    private static boolean isBalancedBase(String string) {
        int counter = 0;

        for(char ch : string.toCharArray()) {
            if(ch == 'x')
                counter++;
            else
                counter--;
        }
        return counter == 0;
    }


    // bonus
    private static boolean isBalancedBonus(String string) {
        if(string.length() == 0)
            return true;

        Map<Character, Integer> letters = new HashMap<>();
        int max = 1;

        for(int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if(letters.containsKey(currentChar)) {
                int value = letters.get(currentChar) + 1;
                letters.put(currentChar, value);
                if(value > max)
                    max = value;
            } else {
                letters.put(currentChar, 1);
            }
        }
        return (string.length() / letters.size() == max);
    }
}

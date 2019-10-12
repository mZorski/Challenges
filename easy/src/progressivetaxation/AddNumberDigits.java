package progressivetaxation;

import java.util.Random;

/**
 * [2019-02-11] Challenge #375 [Easy] Print a new number by adding one to each of its digit
 * https://www.reddit.com/r/dailyprogrammer/comments/aphavc/20190211_challenge_375_easy_print_a_new_number_by/
 */

public class AddNumberDigits {
    public static void main(String[] args) {
        int[] numbers = {0, 9, 998, 999, 1893, 143321};

        for(int number : numbers) {
            System.out.println("Number: " + number);
            addDigitsBase(number);
            addDigitsBonus(number);
            System.out.println("**********");
        }
    }


    // base
    private static void addDigitsBase(Integer number) {
        StringBuilder result = new StringBuilder();
        for(char ch : number.toString().toCharArray())
            result.append(ch == '9' ? "10" : ++ch);
        System.out.println("Base: " + result);
    }


    // bonus
    private static void addDigitsBonus(int number) {
        long result = 0;
        int divider = 1;
        int powerCounter = 0;
        int lastModResult = 0;

        int divisionResult;
        while((divisionResult = number / divider) != 0) {
            int modResult = divisionResult % 10;
            int currentDigit = modResult + 1;

            if(lastModResult == 9)
                powerCounter++;

            currentDigit *= Math.pow(10, powerCounter);
            powerCounter++;
            result += currentDigit;

            lastModResult = modResult;
            divider *= 10;
        }
        System.out.println("Bonus: " + result);
    }
}

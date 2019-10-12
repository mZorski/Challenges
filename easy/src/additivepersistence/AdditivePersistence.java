package additivepersistence;

/**
 * [2019-01-28] Challenge #374 [Easy] Additive Persistence
 * https://www.reddit.com/r/dailyprogrammer/comments/akv6z4/20190128_challenge_374_easy_additive_persistence/
 */

public class AdditivePersistence {
    public static void main(String[] args) {
        int[] numbers = {13, 1234, 9876, 199, Integer.MAX_VALUE};

        for(int number : numbers) {
            System.out.println("Number: " + number);
            System.out.println("Base: " + addDigitsBase(number));
            System.out.println("Bonus: " + addDigitsBonus(number));
            System.out.println("**********");
        }
    }


    // base
    private static int addDigitsBase(Integer number) {
        String result = number.toString();
        int counter = 0;

        while(result.length() > 1) {
            int remaining = 0;
            for(char ch : result.toCharArray()) {
                remaining += ch - '0';
            }
            result = Integer.toString(remaining);
            counter++;
        }
        return counter;
    }

    // bonus
    private static int addDigitsBonus(int number) {
        int result = number;
        int counter = 0;

        while(result / 10 != 0) {
            int remaining = result;
            result = 0;
            while(remaining != 0) {
                result += remaining % 10;
                remaining /= 10;
            }
            counter++;
        }
        return counter;
    }
}

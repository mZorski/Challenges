package _367_subfactorial;

/**
 * [2018-09-04] Challenge #367 [Easy] Subfactorials - Another Twist on Factorials
 * https://www.reddit.com/r/dailyprogrammer/comments/9cvo0f/20180904_challenge_367_easy_subfactorials_another/
 */

public class Subfactorial {
    public static void main(String[] args) {
//        int[] test = {0, 1, 3, 5, 6, 9, 14};
//
//        for (int value : test)
//            System.out.println("!" + value + " = " + subfactorial(value));

        for(int i = 0; i < 15; i++)
            System.out.println("!" + i + " = " + subfactorial(i));
    }

    private static long subfactorial(int n) {
        if(n < 1)
            return 1;
        return (n - 1) * (subfactorial(n - 1) + subfactorial(n - 2));
    }
}

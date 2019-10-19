package _371_nqueensvalidator;

import java.util.Arrays;

/**
 * [2018-12-31] Challenge #371 [Easy] N queens validator
 * https://www.reddit.com/r/dailyprogrammer/comments/ab9mn7/20181231_challenge_371_easy_n_queens_validator/
 */

public class NQueensValidator {
    public static void main(String[] args) {

        int[][] base = {
                {4, 2, 7, 3, 6, 8, 5, 1},
                {2, 5, 7, 4, 1, 8, 6, 3},
                {5, 3, 1, 4, 2, 8, 6, 3},
                {5, 8, 2, 4, 7, 1, 3, 6},
                {4, 3, 1, 8, 1, 3, 5, 2}};

        System.out.println("BASE");
        System.out.println("+ + + + + + + + + + + + + + +");
        for(int[] array : base) {
            printArray(array);
            System.out.println(isValidBase(array));
            printGrid(array);
            System.out.println("___________________________\n");
        }

        int[][] bonus = {
                {8, 6, 4, 2, 7, 1, 3, 5},
                {8, 5, 1, 3, 6, 2, 7, 4},
                {4, 6, 8, 3, 1, 2, 5, 7},
                {7, 1, 3, 6, 8, 5, 2, 4}};

        System.out.println("\n\nBONUS");
        System.out.println("+ + + + + + + + + + + + + + +");
        for(int[] array : bonus) {
            System.out.println("Before:");
            printArray(array);
            System.out.println("After:");
            printArray(isValidBonus(array));
            System.out.println("___________________________\n");
        }
    }


    // base
    private static boolean isValidBase(int[] queens) {
        for(int i = 0; i < queens.length; i++) {
            for(int j = 0; j < queens.length; j++) {
                if(i != j && (queens[i] == queens[j] || Math.abs(queens[i] - queens[j]) == Math.abs(i - j))) {
                    return false;
                }
            }
        }
        return true;
    }


    // bonus
    private static int[] isValidBonus(int[] queens) {
        for(int i = 0; i < queens.length; i++) {
            for(int j = 0; j < queens.length; j++) {
                int[] swappable = Arrays.copyOf(queens, queens.length);
                int tempValue = swappable[i];
                swappable[i] = swappable[j];
                swappable[j] = tempValue;
                if(isValidBase(swappable))
                    return swappable;
            }
        }
        return new int[0];
    }

    private static void printArray(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for(int i : array)
            stringBuilder.append(i).append(", ");
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append("}");
        System.out.println(stringBuilder);
    }

    private static void printGrid(int[] array) {
        for(int i = array.length - 1; i >= 0; i--) {
            System.out.print((i + 1));
            for(int j = 0; j < array.length; j++) {
                System.out.print("  ");
                if((array[j] - 1) == i)
                    System.out.print("x");
                else System.out.print(".");
            }
            System.out.println();
        }
        System.out.print(" ");
        for(int i = 0; i < array.length; i++) {
            System.out.print("  " + (i + 1));
        }
        System.out.println();

    }
}

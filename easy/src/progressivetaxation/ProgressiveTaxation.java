package progressivetaxation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * [2019-07-15] Challenge #379 [Easy] Progressive taxation
 * https://www.reddit.com/r/dailyprogrammer/comments/cdieag/20190715_challenge_379_easy_progressive_taxation/
 */

public class ProgressiveTaxation {
    private static List<double[]> taxBrackets = readTaxBrackets();
    public static void main(String[] args) {

        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println(tax(0));
        System.out.println(tax(10000));
        System.out.println(tax(10009));
        System.out.println(tax(10010));
        System.out.println(tax(12000));
        System.out.println(tax(56789));
        System.out.println(tax(1234567));

        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println(overallBinary(0.00));
        System.out.println(overallBinary(0.06));
        System.out.println(overallBinary(0.09));
        System.out.println(overallBinary(0.32));
        System.out.println(overallBinary(0.40));
    }


    private static double roundDouble(double number) {
        return Math.round(number  * 100.0) / 100.0;
    }


    // base
    private static double tax(double income) {
        double tax = 0.0;
        for(double[] taxBracket : taxBrackets) {
            double lowerBracket = taxBracket[0];
            double upperBracket = taxBracket[1];
            double taxPercentage = taxBracket[2] / 100.0;
            double valueToTax = 0.0;

            if(income > upperBracket)
                valueToTax = upperBracket - lowerBracket;
            else if(income > lowerBracket)
                valueToTax = income - lowerBracket;

            tax += valueToTax * taxPercentage;
        }
        return roundDouble(tax);
    }


    private static double overallTaxRate(double income) {
        return roundDouble(tax(income) / income);
    }


    // bonus (binary search)
    private static double overallBinary(double taxRate) {
        double maxIncome = taxBrackets.get(taxBrackets.size() - 1)[1];

        if(taxRate == 0.0)
            return 0.0;
        if(overallTaxRate(maxIncome) <= taxRate)
            return -1.0;

        double low = taxBrackets.get(0)[0];
        double high = maxIncome;
        double income = (low + high) / 2.0;
        double overallTax = overallTaxRate(income);

        while(overallTax != taxRate) {
            if(overallTax > taxRate) {
                high = income;
            } else if(overallTax < taxRate) {
                low = income;
            }
            income = roundDouble ((low + high) / 2.0);
            overallTax = overallTaxRate(income);
        }
        return income;
    }


    private static List<double[]> readTaxBrackets() {
        List<double[]> taxBrackets = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("files/easy/taxBrackets.txt"))) {
            String line;
            double lowerBracket = 0;
            double upperBracket;
            double taxPercentage;

            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                upperBracket = Double.parseDouble(values[0]);
                taxPercentage = Double.parseDouble(values[1]);
                taxBrackets.add(new double[]{lowerBracket, upperBracket, taxPercentage});

                lowerBracket = upperBracket;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return taxBrackets;
    }
}

package _370_upccheckdigits;

/**
 * [2018-12-17] Challenge #370 [Easy] UPC check digits
 * https://www.reddit.com/r/dailyprogrammer/comments/a72sdj/20181217_challenge_370_easy_upc_check_digits/
 */

public class UpcCheckDigits {
    public static void main(String[] args) {

        System.out.println(upc(4210000526L));
        System.out.println(upc(3600029145L));
        System.out.println(upc(12345678910L));
        System.out.println(upc(1234567L));
    }

    // bonus
    private static int upc(long code) {
        boolean isEvenIndex = true;
        int evenIndexResult = 0;
        int oddIndexResult = 0;

        for(long i = 1; i < Math.pow(10, 12); i *= 10) {
            long singleDigit = (code / i) % 10;
            if(isEvenIndex) {
                evenIndexResult += singleDigit;
                isEvenIndex = false;
            } else {
                oddIndexResult += singleDigit;
                isEvenIndex = true;
            }
        }
        evenIndexResult *= 3;
        evenIndexResult += oddIndexResult;
        int mod = evenIndexResult % 10;

        return mod == 0 ? mod : 10 - mod;
    }
}

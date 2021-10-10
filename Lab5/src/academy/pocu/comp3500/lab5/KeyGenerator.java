package academy.pocu.comp3500.lab5;

import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator {
    private final static int REPEAT_TIMES = 10;

    public static boolean isPrime(final BigInteger number) {

        // isLessThan 2 ?
        if (number.compareTo(BigInteger.TWO) < 0) {
            return false;
        }

        // isEqualTo 2 ?
        if (number.compareTo(BigInteger.TWO) == 0) {
            return true;
        }

        // isEvenNumber ?
        if (number.divideAndRemainder(BigInteger.TWO)[1].equals(BigInteger.ZERO)) {
            return false;
        }

        return isPossiblyPrimeWithMillerRabin(number, REPEAT_TIMES);
    }

    private static boolean isPossiblyPrimeWithMillerRabin(BigInteger number, int repeatTimes) {

        // evenNumber = number - 1
        boolean isPossiblyPrime = true;
        int i = 0;
        while (i < repeatTimes && isPossiblyPrime == true) {
            isPossiblyPrime = millerRabinMethod(number,
                    randomBigInteger(BigInteger.TWO, number.subtract(BigInteger.ONE)));
            i++;
        }

        return isPossiblyPrime;
    }

    private static boolean millerRabinMethod(BigInteger number, BigInteger a) {
        BigInteger d = number.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            if (a.modPow(d, number).equals(number.subtract(BigInteger.ONE))) {
                return true;
            }
            d = d.divide(BigInteger.TWO);
        }
        BigInteger tmp = a.modPow(d, number);

        return tmp.equals(number.subtract(BigInteger.ONE)) || tmp.equals(BigInteger.ONE);
    }

    private static BigInteger randomBigInteger(BigInteger min, BigInteger max) {
        BigInteger diff = max.subtract(min);
        if (diff.equals(BigInteger.ZERO)) {
            return min;
        }
        Random randNum = new Random();
        int len = max.bitLength();
        BigInteger randomNumber = new BigInteger(len, randNum);
        if (randomNumber.compareTo(min) < 0) {
            randomNumber = randomNumber.add(min);
        }

        if (randomNumber.compareTo(diff) >= 0) {
            randomNumber = randomNumber.mod(diff).add(min);
        }

        return randomNumber;
    }
}

// Java Program to Implement Karatsuba Algorithm

// Importing packahge
import java.util.Random;
import java.math.BigInteger;

// Main class
public class Karatsuba {

    public static long opCount = 0;
    // Main driver method
    public static BigInteger mult(BigInteger x, BigInteger y) {
        opCount++;  //Assignment for method call

        // Checking only if input is within range
        opCount++;  // Comparison
        if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
            // Multiplying the inputs entered
            opCount++; // Multiplication
            return x.multiply(y);
        }

        // Find length of both integer
        // numbers x and y
        int noOneLength = x.toString().length();
        int noTwoLength = y.toString().length();
        opCount += 2; // Two assignment

        // Finding maximum length from both numbers
        int maxNumLength = Math.max(noOneLength, noTwoLength);
        // Rounding up the divided Max length
        int halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);
        opCount += 4; // Max, division, addition, modulo

        // Multiplier
        BigInteger maxNumLengthTen = BigInteger.TEN.pow(halfMaxNumLength);
        opCount++;  // Power operation

        // Splitting: x = a*10^m + b, y = c*10^m + d
        BigInteger a = x.divide(maxNumLengthTen);
        BigInteger b = x.remainder(maxNumLengthTen);
        BigInteger c = y.divide(maxNumLengthTen);
        BigInteger d = y.remainder(maxNumLengthTen);
        opCount += 4; // Four division remainder operations

        // Karatsuba recursive steps
        BigInteger z0 = mult(a, c);
        BigInteger z1 = mult(a.add(b), c.add(d));
        BigInteger z2 = mult(b, d);
        opCount += 2; // Two additions within the z1 call

        // Final formula: ans = z0*10^(2m) + (z1-z0-z2)*10^m + z2
        BigInteger term1 = z0.multiply(BigInteger.TEN.pow(halfMaxNumLength * 2));
        BigInteger term2 = z1.subtract(z0).subtract(z2).multiply(BigInteger.TEN.pow(halfMaxNumLength));
        BigInteger ans = term1.add(term2).add(z2);
        opCount += 7; // Subtractions, additions, powers, and multiplications

        return ans;
    }
}


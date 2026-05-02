import java.util.Random;

/**
 * CPT212 Assignment 1: Part 1 - Simple Multiplication
 *
 * DESIGN FEATURES:
 * 1. Suitable Objects: Uses StringBuilder for O(1) character appending,
 *    essential for handling n=10,000 without memory overflow.
 * 2. Precision Counting: Tracks primitive operations including arithmetic,
 *    assignments, and loop overheads for accurate Big-O analysis.
 * 3. Robustness: Implements manual string-based addition to bypass
 *    the 64-bit limits of primitive 'long' types.
 */
public class SimpleMultiplication {

    // Global counter for primitive operations across the algorithm
    public static long opCount = 0;

    /**
     * Executes the digit-by-digit multiplication as specified.
     * @param multiplicand The first large number string
     * @param multiplier   The second large number string
     * @param showSteps    Boolean to toggle console output for carriers/partials
     * @return The final product as a String
     */
    public static String multiply(String multiplicand, String multiplier, boolean showSteps) {
        int n = multiplicand.length();
        int m = multiplier.length();
        // Array to store each shifted partial product row before final summation
        String[] shiftedRows = new String[m];
        opCount += 3; // Assignment ops for n, m, and shiftedRows array

        // STEP 1: Multiply each digit of the multiplier with the full multiplicand
        for (int i = m - 1; i >= 0; i--) {
            opCount++; // Count loop iteration overhead
            int d2 = multiplier.charAt(i) - '0';
            int carry = 0;
            StringBuilder partialRow = new StringBuilder();
            StringBuilder carrierRow = new StringBuilder();
            opCount += 4; // Assignments for d2, carry, and 2 StringBuilder objects

            for (int j = n - 1; j >= 0; j--) {
                opCount++; // Count inner loop iteration overhead
                int d1 = multiplicand.charAt(j) - '0';

                // Core math: Single-digit multiplication plus existing carry
                int prod = (d1 * d2) + carry;
                int partialDigit = prod % 10;
                carry = prod / 10; // New carry for next digit

                // Prepending digits (Requirement: Step 1 Partial & Carrier)
                partialRow.insert(0, partialDigit);
                carrierRow.insert(0, carry);
                opCount += 6; // Mult, Add, Mod, Div, and 2 Insert operations
            }

            // Handle any remaining carry at the end of the row multiplication
            if (carry > 0) {
                partialRow.insert(0, carry);
                opCount++;
            }

            // Requirement 1a: Display steps for small-scale verification
            if (showSteps) {
                System.out.println("Multiplier Digit [" + d2 + "]:");
                System.out.println("   Partial Product: " + partialRow);
                System.out.println("   Carrier:         " + carrierRow);
            }

            // STEP 2: Apply shifting (multiplying by 10^s)
            StringBuilder shifted = new StringBuilder(partialRow);
            for (int s = 0; s < (m - 1 - i); s++) {
                shifted.append("0"); // Appending '0' is more efficient than math multiplication
                opCount += 2; // Loop overhead and append op
            }
            shiftedRows[m - 1 - i] = shifted.toString();
            opCount++;
        }

        // STEP 3: Summation of all properly shifted rows
        String totalResult = "0";
        opCount++;
        for (String row : shiftedRows) {
            totalResult = addLargeStrings(totalResult, row);
            opCount++; // Loop summation overhead
        }

        return totalResult;
    }

    /**
     * Helper method to add two very large strings digit by digit.
     * Necessary to handle numbers beyond the capacity of BigInteger or Long.
     */
    private static String addLargeStrings(String s1, String s2) {
        StringBuilder result = new StringBuilder();
        int i = s1.length() - 1, j = s2.length() - 1, carry = 0;
        opCount += 4; // Assignments for i, j, carry, and result StringBuilder

        while (i >= 0 || j >= 0 || carry > 0) {
            opCount++; // While-loop condition evaluation
            int v1 = (i >= 0) ? s1.charAt(i--) - '0' : 0;
            int v2 = (j >= 0) ? s2.charAt(j--) - '0' : 0;
            int sum = v1 + v2 + carry;
            result.insert(0, sum % 10);
            carry = sum / 10;
            opCount += 5; // 2 charAt lookups, sum calc, digit insert, carry update
        }
        return result.toString();
    }

    /**
     * Generates a random string of digits of length n.
     * Ensures leading digit is non-zero to maintain exact length n.
     */
    public static String generateRandom(int n) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // Logic to ensure the first digit is 1-9, rest are 0-9
            sb.append(i == 0 ? rand.nextInt(9) + 1 : rand.nextInt(10));
        }
        return sb.toString();
    }
}
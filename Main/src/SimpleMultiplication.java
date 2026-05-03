import java.util.Random;

public class SimpleMultiplication {

    public static long opCount = 0;

    public static String multiply(String multiplicand, String multiplier, boolean showSteps) {
        int n = multiplicand.length();
        int m = multiplier.length();
        String[] shiftedRows = new String[m];
        int[] multiplierDigits = new int[m];
        opCount += 4;

        if (showSteps) {
            System.out.println("[ALGORITHM EXECUTION WORKFLOW]");
            // Widened the header padding to match the new summation layout
            System.out.printf("%25s (A)\n", multiplicand);
            System.out.printf("%25s (B)\n", "x " + multiplier);
            System.out.println("------------------------------------------");
        }

        for (int i = m - 1; i >= 0; i--) {
            opCount++;
            int d2 = multiplier.charAt(i) - '0';
            multiplierDigits[m - 1 - i] = d2;
            int carry = 0;
            StringBuilder partialRow = new StringBuilder();
            StringBuilder carrierRow = new StringBuilder();
            opCount += 5;

            if (showSteps) {
                System.out.printf("\nStep 1.%d: Multiplying (A) by Digit [%d] (Weight: 10^%d)\n",
                        (m - i), d2, (m - 1 - i));
            }

            for (int j = n - 1; j >= 0; j--) {
                opCount++;
                int d1 = multiplicand.charAt(j) - '0';

                int prod = (d1 * d2) + carry;
                int partialDigit = prod % 10;
                int oldCarry = carry;
                carry = prod / 10;

                if (showSteps) {
                    System.out.printf("      (%d * %d) + Carry %d = %-2d | Partial: %d, New Carry: %d\n",
                            d1, d2, oldCarry, prod, partialDigit, carry);
                }

                partialRow.insert(0, partialDigit);
                carrierRow.insert(0, carry);
                opCount += 6;
            }

            if (carry > 0) {
                partialRow.insert(0, carry);
                opCount++;
            }

            StringBuilder shifted = new StringBuilder(partialRow);
            int shiftPower = (m - 1 - i);
            for (int s = 0; s < shiftPower; s++) {
                shifted.append("0");
                opCount += 2;
            }
            shiftedRows[shiftPower] = shifted.toString();
            opCount++;

            if (showSteps) {
                System.out.printf("      ==> Row Carriers: %-12s | Row Partial: %s\n",
                        carrierRow.toString(), partialRow.toString());
            }
        }

        String totalResult = "0";
        opCount++;
        for (String row : shiftedRows) {
            totalResult = addLargeStrings(totalResult, row);
            opCount++;
        }

        if (showSteps) {
            System.out.println("\n      -----------------------------------------------------------------------------------------");
            System.out.println("FINAL SUMMATION (Accumulating Shifted Products):");
            System.out.println("      -----------------------------------------------------------------------------------------");

            for (int s = 0; s < shiftedRows.length; s++) {
                String prefix = (s == 0) ? "  " : "+ ";
                // Clean Label on the left
                String rowLabel = String.format("Partial Row %d * 10^%d", (s + 1), s);

                // %-30s: Label (Left Aligned)
                // %2s:   Operator (+ or space)
                // %50s:  Shifted Result (Right Aligned to create the 'wall' effect)
                System.out.printf("      %-30s %2s %50s\n", rowLabel, prefix, shiftedRows[s]);
            }

            System.out.println("      -----------------------------------------------------------------------------------------");
            System.out.printf("      %-30s %2s %50s \n", "FINAL RESULT:", " ", totalResult);
            System.out.println("      -----------------------------------------------------------------------------------------");
        }

        return totalResult;
    }

    private static String addLargeStrings(String s1, String s2) {
        StringBuilder result = new StringBuilder();
        int i = s1.length() - 1, j = s2.length() - 1, carry = 0;
        opCount += 4;

        while (i >= 0 || j >= 0 || carry > 0) {
            opCount++;
            int v1 = (i >= 0) ? s1.charAt(i--) - '0' : 0;
            int v2 = (j >= 0) ? s2.charAt(j--) - '0' : 0;
            int sum = v1 + v2 + carry;
            result.insert(0, sum % 10);
            carry = sum / 10;
            opCount += 5;
        }
        return result.toString();
    }

    public static String generateRandom(int n) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i == 0 ? rand.nextInt(9) + 1 : rand.nextInt(10));
        }
        return sb.toString();
    }
}
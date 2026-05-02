/**
 * Main Runner for Assignment Evaluation
 * Coordinates dataset generation and performance tracking.
 */
import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        // 1. RANDOM STEP-BY-STEP DEMO (Rubric Requirement)
        // Shows that your code works for ANY random small numbers
        System.out.println("=========================================");
        System.out.println("   PART 1a: RANDOM STEP-BY-STEP DEMO     ");
        System.out.println("=========================================");

        int demoSize = 10; // 10 digits is perfect for a report screenshot
        String randomNum1 = SimpleMultiplication.generateRandom(demoSize);
        String randomNum2 = SimpleMultiplication.generateRandom(demoSize);

        System.out.println("Multiplicand (n=" + demoSize + "): " + randomNum1);
        System.out.println("Multiplier   (n=" + demoSize + "): " + randomNum2);
        System.out.println("-----------------------------------------");

        SimpleMultiplication.multiply(randomNum1, randomNum2, true);
        System.out.println("\n");

        // 2. DATA COLLECTION FOR GRAPH (Part 2 Comparison)
        System.out.println("=========================================");
        System.out.println("    EXPERIMENTAL DATA FOR PLOTTING       ");
        System.out.println("=========================================");
        System.out.println("n (digits)\tSimple_Ops\tKaratsuba_Ops");

        // Use these sizes for your Excel Graph
        int[] sizes = {10, 100, 500, 1000, 2000, 5000, 10000};

        for (int n : sizes) {
            // Generate identical random numbers for a fair comparison
            String num1 = SimpleMultiplication.generateRandom(n);
            String num2 = SimpleMultiplication.generateRandom(n);

            // Benchmark Simple Multiplication
            SimpleMultiplication.opCount = 0;
            SimpleMultiplication.multiply(num1, num2, false);
            long simpleOps = SimpleMultiplication.opCount;

            Karatsuba.opCount = 0;
            // Convert Strings to BigInteger
            BigInteger b1 = new BigInteger(num1);
            BigInteger b2 = new BigInteger(num2);

            Karatsuba.mult(b1, b2);
            long karaOps = Karatsuba.opCount;

            // Formatted for easy copy-paste to Excel[cite: 1]
            System.out.println(n + "\t\t" + simpleOps + "\t\t" + karaOps);
        }
    }
}
/**
 * Main Runner for Assignment Evaluation
 * Coordinates dataset generation and performance tracking.
 */
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
        int[] sizes = {10, 50, 100, 200, 500, 1000, 2000};

        for (int n : sizes) {
            // Generate identical random numbers for a fair comparison
            String num1 = SimpleMultiplication.generateRandom(n);
            String num2 = SimpleMultiplication.generateRandom(n);

            // Benchmark Simple Multiplication
            SimpleMultiplication.opCount = 0;
            SimpleMultiplication.multiply(num1, num2, false);
            long simpleOps = SimpleMultiplication.opCount;

            // Karatsuba result (to be updated by your partner)
            long karaOps = 0;
            /*
            Karatsuba.opCount = 0;
            Karatsuba.mult(num1, num2);
            karaOps = Karatsuba.opCount;
            */

            // Formatted for easy copy-paste to Excel
            System.out.println(n + "\t\t" + simpleOps + "\t\t" + karaOps);
        }

        System.out.println("\nInstructions: Copy the table above into Excel to plot the graph.");
    }
}
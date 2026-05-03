import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   PART 1: STEP-BY-STEP DEMO     ");
        System.out.println("=========================================");

        int demoSize = 10;
        String randomNum1 = SimpleMultiplication.generateRandom(demoSize);
        String randomNum2 = SimpleMultiplication.generateRandom(demoSize);

        System.out.println("Multiplicand (A): (n=10)" + randomNum1);
        System.out.println("Multiplier   (B): (n=10) " + randomNum2);
        System.out.println("-----------------------------------------");

        SimpleMultiplication.multiply(randomNum1, randomNum2, true);
        System.out.println("\n");

        System.out.println("=========================================");
        System.out.println("    EXPERIMENTAL DATA FOR PLOTTING       ");
        System.out.println("=========================================");
        System.out.printf("%-10s\t%-18s\t%-18s\n", "n (digits)", "Simple_Ops", "Karatsuba_Ops");

        int[] sizes = {10, 100, 500, 1000, 2000, 5000, 10000};

        for (int n : sizes) {
            String num1 = SimpleMultiplication.generateRandom(n);
            String num2 = SimpleMultiplication.generateRandom(n);

            SimpleMultiplication.opCount = 0;
            SimpleMultiplication.multiply(num1, num2, false);
            long simpleOps = SimpleMultiplication.opCount;

            Karatsuba.opCount = 0;
            BigInteger b1 = new BigInteger(num1);
            BigInteger b2 = new BigInteger(num2);
            Karatsuba.mult(b1, b2);
            long karaOps = Karatsuba.opCount;

            System.out.printf("%-10d\t%-18d\t%-18d\n", n, simpleOps, karaOps);
        }
    }
}
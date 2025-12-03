package day04;

public class Ex08 {
    public static void main(String[] args) {

        final int NUM = 100;
        boolean isPrime = true;
        double sqrt;

        System.out.printf("Prime numbers between 2 and %d:\n", NUM);

        for (int i = 2; i <= NUM; i++) {
            isPrime = true;

            sqrt = Math.sqrt(i);

            for (int j = 2; j < sqrt; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) System.out.print(i + " ");
        }

        System.out.println();
    }
}

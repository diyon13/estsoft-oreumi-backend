package day03;

import java.util.Scanner;

public class Ex12 {
    public static void main(String[] args) {
        int n1;
        int n2;
        int count = 0;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter two positive integers: ");
        n1 = in.nextInt();
        n2 = in.nextInt();
        System.out.println();

        in.close();

        System.out.printf("Multiples of %d between 1 and %d =", n2, n1);

        for (int i = 1; i <= n1; i++) {
            if (i % n2 == 0) {
                System.out.printf(" %d", i);
                count++;
            }
        }

        System.out.println();
        System.out.printf("Number of multiples of %d = %d\n", n2, count);

    }
}

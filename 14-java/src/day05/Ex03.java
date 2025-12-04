package day05;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        int num;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter a positive integer: ");
        num = in.nextInt();
        System.out.println();

        in.close();

        printDivisors(num);
    }

    public static void printDivisors(int num) {
        System.out.printf("Divisors of %d =", num);

        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                System.out.printf(" %d", i);
            }
        }

        System.out.println();
    }
}

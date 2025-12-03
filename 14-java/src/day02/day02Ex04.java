package day02;

import java.util.Scanner;

public class day02Ex04 {
    public static void main(String[] args) {
        int num;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter a positive integer greater than 1: ");
        num = in.nextInt();
        System.out.println();

        in.close();

        long a = 0;
        long b = 1;
        long c = 0;

        for (int i = 2; i <= num; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        System.out.printf("%dth Fibonacci number = %d\n", num, c);
    }
}

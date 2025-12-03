package day04;

import java.util.Scanner;

public class Ex06 {
    public static void main(String[] args) {
        final int BASE = 19;
        final int LENGHT = 10;
        int num;
        int[] remainders = new int[BASE];

        int count = 0;

        Scanner in = new Scanner(System.in);

        for (int i = 0; i < LENGHT; i++) {
            System.out.print("Enter a positive integer: ");
            num = in.nextInt();

            num %= BASE;

            remainders[num]++;
        }

        in.close();

        for (int i =0; i < BASE; i++) {
            if (remainders[i] != 0) count++;
        }

        System.out.println();
        System.out.printf("number of different remainders: %d\n", count);
    }
}

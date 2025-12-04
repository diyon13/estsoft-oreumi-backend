package day04;

import java.util.Scanner;

public class day04Ex02 {
    public static void main(String[] args) {

        int n;
        int num;
        int remainder = 0;
        String binary;

        Scanner in =  new Scanner(System.in);

        while (true) {
            System.out.print("Enter an integer: ");
            n = in.nextInt();

            if (n == 0) break;

            if (n < 1 || n > 1048575) {
                System.out.println("ERROR: the entered value is out of range.\n");
                continue;
            }

            binary = "";

            for (int i = n; i > 0; i /= 2) {
                remainder = i % 2;
                binary += remainder;
            }

            System.out.printf("The binary number for decimal %d is %s\n", n, binary);
            System.out.println();
        }

        System.out.println("Good bye!");
        in.close();

    }
}

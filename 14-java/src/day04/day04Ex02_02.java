package day04;

import java.util.Scanner;

public class day04Ex02_02 {
    public static void main(String[] args) {
        int num;

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a  positive integer: ");
            num = in.nextInt();

            if (num == 0) break;

            System.out.printf("Binary number for decimal %d = %s\n\n", num, Integer.toBinaryString(num));
        }

        System.out.println("Good bye!");

        in.close();
    }
}

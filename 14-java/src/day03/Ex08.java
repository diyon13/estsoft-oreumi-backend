package day03;

import java.util.Scanner;

public class Ex08 {
    public static void main(String[] args) {
        int num;
        int sum = 0;

        Scanner in = new Scanner(System.in);


        while (true) {
            System.out.print("Enter a positive integer: ");
            num = in.nextInt();

            if (num == 0) break;

            sum += num;
        }
        in.close();
        System.out.println("\nsum = " + sum);
    }
}

package day03;

import java.util.Scanner;

public class day03Ex01 {
    public static void main(String[] args) {
        int num;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a positive integer greater than 1: ");
        num = in.nextInt();

        if (num < 1) {
            System.out.println("ERROR: you must enter a positive integer.");
        } else {

            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    System.out.printf("%d is not a prime number.\n", num);
                    return;
                }
            }
            System.out.printf("%d is a prime number.\n", num);
        }


        in.close();
    }
}

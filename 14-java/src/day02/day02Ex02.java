package day02;

import java.util.Scanner;

public class day02Ex02 {
    public static void main(String[] args) {
        int num;

        Scanner in =  new Scanner(System.in);

        System.out.print("Enter a positive integer: ");
        num = in.nextInt();
        System.out.println();

        in.close();

        for(int i = 1; i <= num; i *= 10){
            System.out.printf("%d's digit = %d\n", i, num / i % 10);
        }

    }
}

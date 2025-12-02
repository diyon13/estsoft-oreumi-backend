package day03;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        int rows;
        int spaces;
        int stars;

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        rows = in.nextInt();
        System.out.println();

        in.close();

        for(int i = 1; i <= rows; i++) {
            spaces = rows - i;
            stars = i;

            for(int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }

            for(int j = 1; j <= stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

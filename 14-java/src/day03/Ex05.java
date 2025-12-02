package day03;

import java.util.Scanner;

public class Ex05 {
    public static void main(String[] args) {
        int rows;
        int columns;

        Scanner in =  new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        rows = in.nextInt();

        System.out.print("Enter the number of columns: ");
        columns = in.nextInt();

        System.out.println();

        in.close();

        int num = 0;

//        for (int i = 1; i <= rows; i++) {
//            for (int j = 1; j <= columns; j++) {
//                System.out.printf("%2d ", j + columns * (i - 1));
//            }
//            System.out.println();

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                num++;
                System.out.printf("%2d ", num);
            }
            System.out.println();
        }
    }
}

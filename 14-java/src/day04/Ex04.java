package day04;

import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        final int LENGTH = 30;
        int[] numbers = new int[LENGTH];
        int length = 0;
        int sum = 0;
        double average = 0;
        int aboveAverage = 0;
        double percentage = 0;

        Scanner in =  new Scanner(System.in);
        /*
        for (int i = 0; i < LENGTH; i++) {
            System.out.print("Enter an integer: ");
            numbers[i] = in.nextInt();

            if (numbers[i] == 0) break;

            length++;
            sum += numbers[i];
        }
        */

        for (; length < LENGTH; length++) {
            System.out.print("Enter an integer: ");
            numbers[length] = in.nextInt();

            if (numbers[length] == 0) break;

            sum += numbers[length];
        }

        in.close();

        average = (double)sum / length;

        for (int i = 0; i <= length; i++) {
            if (numbers[i] > average) aboveAverage++;
        }
        percentage = ((double)aboveAverage / length) * 100;

        System.out.println("average = "+ average);
        System.out.printf("Percentage of integers above the average = %g%%\n", percentage);
    }
}

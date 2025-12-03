package day04;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        final int LENGTH = 10;
        int[] numbers = new int[LENGTH];
        int sum = 0;
        double average = 0;
        int max = 0;

        Scanner in =  new Scanner(System.in);

        for (int i = 0; i < LENGTH; i++) {
            System.out.print("Enter an integer: ");
            numbers[i] = in.nextInt();

//            sum += numbers[i];
//
//            if(numbers[i] > max) {
//                max = numbers[i];
//            }
        }
        in.close();

        max = numbers[0];

        for (int item : numbers) {
            sum += item;
            max = Math.max(max, item);
        }

        average = (double)sum / LENGTH;

        System.out.println();
        System.out.printf("sum = %d\n", sum);
//        System.out.printf("average = %.1f\n", average);
        System.out.println("average = " + average  );
        System.out.printf("max = %d\n", max);
    }
}

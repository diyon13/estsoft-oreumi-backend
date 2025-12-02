package day03;

import java.util.Scanner;

public class Ex11 {
    public static void main(String[] args) {
        int num;
        int maximum = Integer.MIN_VALUE;
        int minimum = Integer.MAX_VALUE;
        int sum = 0;
        int count = 0;

        Scanner in = new Scanner(System.in);

        while(true){
            System.out.print("Enter a positive integer: ");
            num = in.nextInt();

            if (num == 0) break;

            sum += num;
            count++;

//            if (num > maximum) maximum = num;
            maximum = Math.max(maximum, num);

//            if (num < minimum) minimum = num;
            minimum = Math.min(minimum, num);
        }

        in.close();

        System.out.println();

        // 사용자가 자연수를 하나 이상 입력한 경우에만 결과를 출력
        if(count > 0) {
            System.out.println("The maximum number: " + maximum);
            System.out.println("The minimum number: " + minimum);
            System.out.println("The average: " + (double)sum / count);
        } else {
            System.out.println("ERROR: you must enter a positive integers.");
        }
    }
}

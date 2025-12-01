package day02;

import java.util.Scanner;

public class Ex11 {
    public static void main(String[] args) {
        long factorial;
        int num;
        int sum = 0;

        for (int i = 1; i <= 100; i++)
            sum += i;

        System.out.println("The sum of the integers between 1 and 100 =" + sum);

        System.out.printf("The sum of the integers between 1 and 100 =%d\n",
                (1 + 100) * 100 / 2);

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        num = in.nextInt();
        factorial = 1;

        for (int i = 2; i <= num; i++) {
            factorial *= i;
        }

        System.out.printf("Factorial %d = %d\n", num, factorial);

        in.close();


    }
}

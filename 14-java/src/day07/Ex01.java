package day07;

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        int n1, n2;

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter the dividend: ");
        n1 = in.nextInt();
        System.out.print("Enter the divisor: ");
        n2 = in.nextInt();
        System.out.println();

        in.close();

        //예외 처리
        try {
        System.out.println("n1 / n2 = " + n1 / n2);
        System.out.println("n1 % n2 = " + n1 % n2);
        } catch(ArithmeticException e) {
            System.out.println("ERROR: Cannot divide by zero.");
        } finally{
            System.out.println("Good bye!");
        }
    }
}

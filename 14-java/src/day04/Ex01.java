package day04;

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        int n1;
        int n2;
        int gcf = 1;
        int min;

        int a, b, r;

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter two positive integers: ");
        n1 = in.nextInt();
        n2 = in.nextInt();
        System.out.println();

        in.close();

//        min = Math.min(n1, n2);
//
//        for (int i = min; i >= 1; i--) {
//            if (n1 % i == 0 && n2 % i == 0) {
//                gcf = i;
//                break;
//            }
//        }

        a = Math.max(n1, n2);
        b = Math.min(n1, n2);

        while (true) {
            r = a % b;
            if (r == 0) break;
            a = b;
            b = r;
        }

        System.out.printf("The greatest common factor of %d and %d is = %d\n", n1, n2, b);

    }
}

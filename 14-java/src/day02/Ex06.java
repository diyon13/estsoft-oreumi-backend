package day02;

import java.util.Scanner;

public class Ex06 {
    public static void main(String[] args) {
        int n1, n2;
        int max;
        int min;

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter two integers: ");
        n1 = in.nextInt();
        n2 = in.nextInt();
        System.out.println();

//        if (n1 > n2) {
//            max = n1;
//            min = n2;
//        } else {
//            max = n2;
//            min = n1;
//        }

//        max = n1 > n2 ? n1 : n2;
//        min = n1 < n2 ? n1 : n2;

        max = Math.max(n1, n2);
        min = Math.min(n1, n2);

        System.out.printf("max - min = %d\n", max - min);

        int delta = n1 > n2 ? n1 - n2 : n2 - n1;
        System.out.println("max - min = "  + delta);

        in.close();
    }
}

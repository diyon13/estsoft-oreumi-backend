package day02;

import java.util.Scanner;

public class Ex09 {
    public static void main(String[] args) {

        int n1, n2;
        int tmp;

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter two integers: ");
        n1 = in.nextInt();
        n2 = in.nextInt();
        System.out.println();

        System.out.printf("n1 = %d, n2 = %d\n", n1, n2);

        tmp = n1;
        n1 = n2;
        n2 = tmp;

        System.out.printf("n1 = %d, n2 = %d\n", n1, n2);

        in.close();
    }
}

package day02;

import java.util.Scanner;

public class Ex10 {
    public static void main(String[] args) {
        int num;
        Scanner in =  new Scanner(System.in);

        System.out.print("Enter an integar: ");
        num = in.nextInt();
        System.out.println("");

        in.close();

        for (int i = num; i >= 1; i-- ) {
            System.out.println(i);
        }
    }
}

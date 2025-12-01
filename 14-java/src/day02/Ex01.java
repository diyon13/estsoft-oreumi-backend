package day02;

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter a integer: ");
        int num = in.nextInt();

        in.close();

        System.out.println("num = " + num);
    }
}

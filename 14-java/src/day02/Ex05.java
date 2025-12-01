package day02;

import java.util.Scanner;

public class Ex05 {
    public static void main(String[] args) {
        int n1;

        Scanner in =  new Scanner(System.in);

        System.out.print("Enter an integar: ");
        n1 = in.nextInt();

        System.out.println("");

        if (n1 >20 && n1 < 40) {
            System.out.println("Dream comes true!");
        }

        in.close();
    }
}

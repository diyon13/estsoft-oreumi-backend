package day02;

import java.util.Scanner;

public class Ex08 {
    public static void main(String[] args) {
        char ch;
        Scanner in =  new Scanner(System.in);
        System.out.print("Enter a character: ");

        ch = in.nextLine().charAt(0);

        in.close();

        switch (ch) {
            case 'a':
            case 'A':
                System.out.println("Apple");
                break;

            case 'b':
            case 'B':
                System.out.println("Banana");
                break;

            case 'c':
            case 'C':
                System.out.println("Cherry");
                break;

            default:
                System.out.println("Fruit");
        }
    }
}

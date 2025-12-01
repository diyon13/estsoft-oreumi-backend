package day02;

import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter a string: ");

        String str = in.nextLine();

        System.out.printf("str = \"%s\"\n", str);

        System.out.print("Enter a character: ");

//        str = in.nextLine();

        System.out.printf("str = \"%s\"\n", str);

//        char ch = str.charAt(0);

        char ch = in.nextLine().charAt(0);

        System.out.printf("ch = '%c'\n", ch);
        in.close();
    }
}

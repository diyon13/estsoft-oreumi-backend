package day03;

import java.util.Scanner;

public class Ex07 {
    public static void main(String[] args) {
        int n1, n2;

        Scanner in =  new Scanner(System.in);

//        while (true) {
//
//            System.out.print("Enter two integers: ");
//            n1 = in.nextInt();
//            n2 = in.nextInt();
//            System.out.println();
//
//            if (n1 == 0 && n2 == 0) break;
//
//            System.out.printf("%d + %d = %d\n\n", n1, n2, n1 + n2);
//        }

        do {
            System.out.print("Enter two integers: ");
            n1 = in.nextInt();
            n2 = in.nextInt();
            System.out.println();

            if (n1 != 0 || n2 != 0)
                System.out.printf("%d + %d = %d\n\n", n1, n2, n1 + n2);

        } while (!(n1 == 0 && n2 == 0));

        System.out.println("Good Bye!");

        in.close();

    }
}

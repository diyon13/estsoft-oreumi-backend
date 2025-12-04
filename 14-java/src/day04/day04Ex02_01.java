package day04;

import java.awt.print.Printable;
import java.util.Scanner;

public class day04Ex02_01 {
    public static void main(String[] args) {
        final int LENGTH = 20;
        int num;
        int decimal;
        int digits;
        int[] binary = new int[LENGTH];

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a  positive integer: ");
            num = in.nextInt();

            if (num == 0) break;
            //사용자가 입력한 10진수의 유효성 검사
            if (num < 1 || num > 1048575) {
                System.out.println("ERROR: the entered value is out of range.\n");
                continue;
            }

            //변수 decimal에 변수 num을 복사
            decimal = num;

            for (digits = 0; decimal != 0; digits++) {
                //변수 decimal을 2로 나누고 남은 나머지를 배열 binary의 원소에 대입
               binary[digits] = decimal % 2;
               decimal /= 2;
            }
            System.out.printf("Binary number for decimal %d = ", num);

            for (int i = digits - 1; i >= 0; i--)
                System.out.print(binary[i]);

            System.out.println();
        }

        System.out.println("Good bye!");

        in.close();
    }
}

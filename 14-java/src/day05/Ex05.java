package day05;

import java.util.Scanner;

public class Ex05 {
    /*
    * @param n1 평균을 구하고자 하는 정수
    * @param n2 평균을 구하고자 하는 정수
    * @return 두 정수의 평균
    * */
    public static double getAverage(int n1, int n2) {
        return (double)(n1 + n2) / 2;
    }

    public static void main(String[] args) {
        int n1;
        int n2;

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter two integers:");
        n1 = in.nextInt();
        n2 = in.nextInt();
        System.out.println();

        in.close();

        System.out.printf("The average of %d and %d = %.1f\n",
                n1, n2, getAverage(n1, n2));
    }
}

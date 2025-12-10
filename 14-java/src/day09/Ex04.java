package day09;

import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        // 변수 선언
        int n1, n2;
        int gcf;
        long lcm;

        // 사용자에게 정수 2개를 입력 받기
        Scanner in =  new Scanner(System.in);
        System.out.print("Enter two positive integers: ");
        n1 = in.nextInt();
        n2 = in.nextInt();
        System.out.println();

        // 입력문 닫기
        in.close();

        // 최대공약수와 최소공배수
        gcf = getGcf(n1, n2);
        lcm = (long)n1 * n2 / gcf;

        // 출력
        System.out.printf("The greatest common factor of %d and %d = %d\n", n1, n2, gcf);
        System.out.printf("The least common factor of %d and %d = %d\n", n1, n2, lcm);

    }
    
    public static int getGcf(int a, int b) {

        // 유클리드 알고리즘
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 재귀 호출
        return (b == 0) ? a : getGcf(b, a % b);
    }
}

package day09;

import java.util.Scanner;

public class Ex02 {
    /**
     * n번째 피보나치 수를 구하는 재귀 메서드
     *
     * @param n 구하고자 하는 피보나치 수의 순서
     * @return n번째 피보나치 수
     */
    public static long fibonacci(int n) {
        /*
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fibonacci(n - 1) + fibonacci(n - 2);
        */

        // 조건 연산자로 종료 조건을 확인하고, 종료 저건에 해당하지 않으면
        // 이전 두 피보나치 수를 더해서 피보나치 수를 반환
        return n <= 1 ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        // 변수 선언
        int num;

        Scanner in =  new Scanner(System.in);

        System.out.print("Enter a positive integer greater than 1: ");
        num = in.nextInt();
        System.out.println();

        in.close();
        long result = fibonacci(num);

        System.out.printf("%dth Fibonacci number = %d%n", num, result);
    }
}

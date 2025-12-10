package day09;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        // 변수 선언
        int num;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter a positive integer greater than 1: ");
        num = in.nextInt();
        System.out.println();

        in.close();

        //이전에 구한 피보나치 수를 저장할 배열
        long[] cache = new long[num + 1];

        System.out.printf("%dth Fibonacci number = %d%n", num, fibonacci(num, cache));
    }

    /**
     * n번째 피보나치 수를 구하는 재귀 메서드
     *
     * @param n 구하고자 하는 피보나치 수의 순서
     * @return n번째 피보나치 수
     */

    public static long fibonacci(int n, long[] cache) {
        // 종료 저건에 해당하면 피보나치 수를 반환
        if (n <= 1) return n;
        // 이번에 구해서 저장해놓은 피보나치 수가 있으면, 그 값을 반환
        if (cache[n] != 0) return cache[n];
        // 위 조건에 해당하지 않으면, n번째 피보나치 수를 구해 배열에 저장
        cache[n] = fibonacci(n - 1, cache) + fibonacci(n - 2, cache);

        // n번째 피보나치 수를 반환
        return cache[n];
    }
}

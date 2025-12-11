package day10;

import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        // 변수 선언
        int N1, N2, N3;
        int bestSum = 0;
        int bestIndex = 0;

        // 사용자에게 N1 ~ N3를 입력 받기
        Scanner in =  new Scanner(System.in);
        System.out.print("Enter five card numbers: ");
        N1 = in.nextInt();
        N2 = in.nextInt();
        N3 = in.nextInt();
        System.out.println();

        // 가능한 조합 생성
        int[] sums = { N1, N2, N3, N1 + N2, N1 + N3, N2 + N3, N1 + N2 + N3 };

        // 가능한 조합을 문자열로 표현 해두기
        String[] numbers = {
                String.valueOf(N1),
                String.valueOf(N2),
                String.valueOf(N3),
                String.valueOf(N1) + " " + String.valueOf(N2),
                String.valueOf(N1) + " " + String.valueOf(N3),
                String.valueOf(N2) + " " + String.valueOf(N3),
                String.valueOf(N1) + " " + String.valueOf(N2) + " " + String.valueOf(N3)
        };

        // 입력창 닫기
        in.close();

        // 21이하의 중 최고의 조합 찾기
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] <= 21 && sums[i] > bestSum) {
                bestSum = sums[i];
                bestIndex = i;
            }
        }

        // 출력
        System.out.println("Cards = "+ numbers[bestIndex]);
        System.out.println("Sum = " + bestSum);

    }
}

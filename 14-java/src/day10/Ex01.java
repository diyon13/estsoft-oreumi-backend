package day10;

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        // 변수 선언
        int N;
        int num;
        int count;
        final String[] ordinals = {"1st", "2nd", "3rd"};
        String ordinal;

        // 철수가 만든 N번째 영화의 제목
        Scanner in =  new Scanner(System.in);
        System.out.print("Enter the episode number to check: ");
        N = in.nextInt();
        System.out.println();

        // 입력창 닫기
        in.close();

        // 사용자가 0이하의 값을 입력했을때 에러메세지 출력
        if (N <= 0) {
            System.err.println("Error: 올바른 값을 입력해 주세요.");
            return;
        }

        // 변수 초기화
        num = 0;
        count = 0;

        while (true) {
            // 666이 포함되어 있는지 검사
            if (String.valueOf(num).contains("666")) {
                count++;

                // 입력한 값 N을 찾으면 종료
                if (count == N) {
                    break;
                }
            }
            num++;
        }

        // 입력한 값의 따라 출력값이 달라지므로 ordinal에 대입
        if (N >= 1 && N <= 3) {
            ordinal = ordinals[N - 1];
        } else {
            ordinal = N + "th";
        }

        // 출력
        System.out.printf("The number included in the %s episode title = %d", ordinal, num);
    }
}

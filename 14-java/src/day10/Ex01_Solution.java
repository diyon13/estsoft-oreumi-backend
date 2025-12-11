package day10;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Ex01_Solution {
    public static void main(String[] args) {

    }
   static int solutions(int n) {
    int N;

    Scanner in =  new Scanner(System.in);
        System.out.print("Enter the episode number to check: ");
    N = in.nextInt();
        System.out.println();

    // 입력 스트림 닫기
        in.close();
       if (n <= 0) {
           throw new IllegalArgumentException("n must be greater than zero");
       }

       int count = 0;
       final String strBase = "666";
       String strNum = "666";
       int i = 0;

        //do while 구문으로 "666"이 들어간 숫자르 탐색
       do {
           // 변수 i가 6이 아니면, 변수 i를 "666"의 앞에 붙인다.
           // 변수 i의 1의 자리 숫자가 6이 아니면, 변수 i를 "666"의 앞에 붙인다
           if (i % 10 != 6) {
               strNum = i + strBase;
               count++;
           } else {
                for (int j = 0; j < 10 && count < n; j++) {
                    strNum = (i / 10) + strBase + j;
                    count++;
                }
           }
           // 인덱스 변수 i를 증가
           i++;
       } while (count <= n);

       // 찾은 종말의 숫자를 반환
       return Integer.parseInt(strNum);
    }
    static int solution(int n) {
        return IntStream.iterate(666, i -> i + 1)
                .filter(num -> Integer.toString(num).contains("666"))
                .skip(n - 1)
                .findFirst()
                .orElse(0);
    }
}

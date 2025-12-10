package day08;

import java.util.*;

public class Ex04 {
    public static void main(String[] args) {
        // 변수 선언
        final int LENGTH = 6;
        final int MAX_NUM = 45;
        Set<Integer> set = new TreeSet<>();
        Random rand = new Random();

        // while 구문으로 집합 set의 요소가 6개가 될 때까지 반복문을 수행
        while (set.size() != LENGTH) {
            // 1부터 45 사이의 임의의 자연수를 생성해서 집합 set에 추가
            set.add(rand.nextInt(MAX_NUM) + 1);
        }

        // 출력
        System.out.println("set = " + set);
    }
}

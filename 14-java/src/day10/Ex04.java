package day10;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex04 {
    public int[] solution(int []arr) {
        // 자연수를 담을 ArrayList라는 빈 리스트를 만들고
        ArrayList<Integer> list = new ArrayList<>();

        // 제한 조건 검사
        if (arr.length == 1000000) {
                throw new IllegalArgumentException();
        }

        for (int n : arr) {
            if ( n < 0 || n > 9) {
                throw new IllegalArgumentException();
            }
        }


        // 첫번째수는 절대 중복되지 않으니 저장
        list.add(arr[0]);

        // 이전 숫자와 다르면 추가
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                list.add(arr[i]);
            }
        }

        // list에 저장한 값을 answer 배열에 복사
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // 테스트용
    public static void main(String[] args) {
        Ex04 solution = new Ex04();
        int[] array = new int[]{1, 1, 3, 3, 3, 3, 3, 0, 1, 1, 5, 6, 5, 100};

        System.out.println(Arrays.toString(solution.solution(array)));
    }
}

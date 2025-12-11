package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ex03 {
    static int[] solution(int[] arr, int divisor) {
        // 스트림을 이용해 배열 arr의 원소들 중에 divisor로 나누어 떨어지는 원소들을
        // 오름차순으로 정렬한 배열을 생성
        int[] result = Arrays.stream(arr)
                .filter(n -> n % divisor == 0)
                .sorted()
                .toArray();

        return result.length == 0 ? new int[] { -1 } : result;
    }
    static int[] solution1(int[] arr, int divisor) {
        // 자연수를 담을 ArrayList라는 빈 리스트를 만들고
        ArrayList<Integer> list = new ArrayList<>();

        // 거기에 arr을 divisor로 나누어 떨어지는 값을 저장
        for (int items : arr) {
            if (items % divisor == 0) {
                list.add(items);
            }
        }

        // 나누어 떨어지는 값이 없으면 -1을 저장해서 반환
         if (list.isEmpty()) {
             return new int[]{ -1 };
         }

        // 오름차순으로 정렬
        Collections.sort(list);

         // 정렬된 list를 answer 배열에 복사
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // 출력
    public static void main(String[] args) {
        Ex03 solution = new Ex03();
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(Arrays.toString(solution.solution(array, 4)));
    }
}

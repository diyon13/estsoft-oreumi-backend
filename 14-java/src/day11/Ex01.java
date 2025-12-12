package day11;

import java.util.Arrays;

public class Ex01 {
    static int[] solution(String s) {
        /*
        문자열을 문자로 쪼개서 하나씩 검사하고
        처음은 무조건 -1을 반환
        이전에 같은 문자가 있는지 확인해서
        원소의 인덱스의 차이만큼 반환해주면 될거같은데?
         */

        // 문자열 길이만큼의 배열을 생성
        int[] answer = new int[s.length()];

        // 문자들을 하나씩 확인
        for (int i = 0; i < s.length(); i++) {
            // 처음은 무조건 -1을 반환
            answer[i] = -1;

            // 앞에 같은 문자가 있는지 확인
            for (int j = i -1; j >= 0; j--) {
                // 문자가 같은지 비교
                if (s.charAt(j) == s.charAt(i)) {
                    // 현재 위치 i에서 j까지의 거리를 계산
                answer[i] = i - j;
                break;
                }
            }
        }
        return answer;
    }
    // 출력
    public static void main(String[] args) {
        String s1 = "banana";
        String s2 = "foobar";

        System.out.println("banana = " + Arrays.toString(solution(s1)));
        System.out.println("foobar = " + Arrays.toString(solution(s2)));
    }
}

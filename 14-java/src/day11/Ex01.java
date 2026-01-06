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


    /*
    * 위 solution 메서드는 최악의 상황을 고려하면 시간 복잡도가 O(n²)이다
    * 이 문제에서는 문자열의 s의 최대 길이가 10,000이므로 충분히 통과할 수 있지만,
    * 이를 개선하기 위해 문자열 s에서 각 영문자가 몇 뻔째 위치에 나왔는지 그 정보를 배열에 저장하면
    * 효율성을 더 개선할 수 있다.
     */
    static int[] solution1(String s) {
        final int length = s.length();
        final int[] answer = new int[length];
        // 문자열 s에 나올 수 잇는 문자가 영어 소문자로 제한되어 있으므로
        // 각 문자가 나온 위치를 배열에 저장할 수 있다.
        final int[] lastIndexes =  new int[26];
        // lastIndexes[0] = 'a'가 나온 위치 (0 == 'a' - 'a')
        // lastIndexes[25] = 'z'가 나온 위치 (0 == 'z' - 'a')

        // 배열 lastIndexes의 원소에 접근하기 위한 인덱스
        // → (현재 위치의 문자) - 'a'
        int index;

        // 배열 lastIndexes의 모든 원소를 -1로 초기화
        Arrays.fill (lastIndexes, -1);

        // for 구문으로 첫 번째 문자에서부터 이전에 같은 문자가 있는지 확인
        for (int i = 0; i < length; i++) {
            // 배열 lastIndexes의 원소에 접근하기 위한 인덱스를 계산
            index = s.charAt(i) - 'a';

            // 배열 answer의 i번째 원소에 -1 또는 이전에 나온 같은 문자와의 간격을 대입
            answer[i] = lastIndexes[index] == -1 ? -1 : i - lastIndexes[index];

            // 문자열 s에서 현재 위치의 문자가 나온 인덱스를 배열 lastIndexes에 저장
            lastIndexes[index] = i;
        }

        return answer;
    }
}

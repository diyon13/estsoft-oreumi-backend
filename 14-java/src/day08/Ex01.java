package day08;

import java.util.Arrays;
import java.util.Random;

public class Ex01 {
    public static void main(String[] args) {
        // 변수 선언
        final int LENGTH = 6;
        final int MAX_NUM = 45;
        int[] numbers = new int[LENGTH];
        Random rand = new Random();

        // for 구문으로 중복되지 않은 임의의 자연수 6개를 생성해서 배열 numbers에 저장
        for (int i = 0; i < LENGTH; i++) {
            // 1부터 45 사이의 임의의 자연수를 생성
            int num = rand.nextInt(MAX_NUM) + 1;

            // 생성한 임의의 자연수가 배열 numbers에 포함되어 있는지 확인
            if (inArray(numbers, num)) {
                // 생성한 임의의 자연수가 배열 numbers에 포함되어 있으면,
                // 인덱스 변수 i를 1만큼 감소시켜서 for 구문을 한 번 더 실행한다.
                i--;
            } else  {
                numbers[i] = num;
            }
        }

        // 오름차순으로 정렬
        Arrays.sort(numbers);
        //
        System.out.println("Numbers= " + Arrays.toString(numbers));
        System.out.println();

        System.out.print("Numbers=");
        for (int item : numbers) {
            System.out.print(" " + item);
        }
        System.out.println();
        System.out.println();

        // 배열을 문자열로 변환해서 replaceAll 메서드로 대괄호와 쉼표(,)를 제거
        String result = Arrays.toString(numbers).replaceAll("[\\[\\],]", "");

        System.out.println("numbers= " + result);
    }

        /**
         * int 자료형 배열에 지정한 값이 포함되어 있는지 검사
         *
         * @param array int 자료형 배열
         * @param value 확인할 정수
         * @return 배열 array에 value가 포함되어 있는지 여부
         */
        public static boolean inArray(int[] array, int value) {
            for (int item : array) {
                if (item == value) return true;
            }
            return false;
        }
}

package day10;

import java.util.Scanner;

public class Ex02_Solution {
    static final int MAX_SUM = 21;          // 합계의 최댓값
    static final int NUM_CARDS = 5;         // 카드의 개수
    static final int NUM_SELECTED = 3;      // 합계를 구할 카드의 개수

    public static void main(String[] args) {
        // 변수 선언
        int max = 0;                        // 합계의 최댓값
        int[] cards = new int[NUM_CARDS];   // 사용자가 입력한 카드
        int[] selected = new int[NUM_SELECTED];

        Scanner in =  new Scanner(System.in);

        // 사용자 입력
        System.out.print("Enter five card numbers between 1 and 12: ");

        for (int i = 0; i < NUM_CARDS; i++) {
            cards[i] = in.nextInt();

            if (cards[i] < 1 || cards[i] > 12) {
                throw new RuntimeException("Invalid card number.");
            }
        }
        System.out.println();

        // 입력 스트림 닫기
        in.close();

        // solution 함수로 합계가 21을 넘지 않는 한도 내에서, 합이 가장 큰 카드 1~3장을 선택
        max = solution(cards, selected);

        // 출력
        System.out.print("cards =");

        for (int card : selected) {
            if (card != 0) System.out.print(" " + card);
        }

        System.out.println();
        System.out.println("sum = " + max);

    }
    /**
     * 카드 5장 중에서 합계가 21을 넘지 않는 한도 내에서, 합계가 가장 큰 카드 1~3장을 선택
     *
     * @param cards 카드의 숫자가 저장된 배열
     * @param selected 선택한 카드의 숫자를 저장할 배열
     * @return 선택한 카드들의 합계
     */
    static int solution(int[] cards, int[] selected) {
        // 카드 3장의 합계 중에 21을 넘지 않는 최댓값
        int max = 0;
        // for 구문으로 첫 번째 카드부터 순회
        for (int i = 0; i < cards.length; i++) {
            max = checkCards(cards[i], 0, 0, max, selected);

            for (int j = i + 1; j < cards.length; j++) {
                max = checkCards(cards[i], cards[j], 0, max, selected);

                for (int k = j + 1; k < cards.length; k++) {
                    max = checkCards(cards[i], cards[j], cards[k], max, selected);
                }
            }
        }

        // 카드 3장의 합계 중에 21을 넘지 않는 최댓값을 반환
        return max;
    }

    /**
     * 세 정수의 합계가 21보다 작거나 같고 max보다 크면 배열 selected에 저장
     *
     * @param n1 합계를 확인할 정수
     * @param n2 합계를 확인할 정수
     * @param n3 합계를 확인할 정수
     * @param max 합계의 최댓값
     * @param selected 합계가 최댓값인 정수들을 저장할 배열
     * @return 합계의 최댓값
     *
     */
    static int checkCards(int n1, int n2, int n3, int max, int[] selected) {
        // 세 정수의 합계를 계산
        int sum = n1 + n2 + n3;

        // 세 정수의 합계가 21보다 작거나 같고 max보다 크면 배열 selected에 저장
        if (sum <= MAX_SUM && sum >= max) {
            selected[0] = n1;
            selected[1] = n2;
            selected[2] = n3;

            // 합계의 최댓값을 갱신
            max = sum;
        }

        // 합계의 최댓값을 반환
        return max;
    }

}

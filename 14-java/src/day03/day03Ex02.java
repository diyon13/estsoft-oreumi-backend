package day03;

import java.util.Scanner;

public class day03Ex02 {
    public static void main(String[] args) {
        /*
        int num;
        int bag3 = -1;
        int bag5 = -1;
        int count = 0;

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter the weight of sugar to be delivered: ");
        num = in.nextInt();

        for (int i = num / 5; i >= 0; i--) {
            if ((num - (i * 5)) % 3 ==0) {
                bag5 = i;
                bag3 = (num - (i * 5)) / 3;
                count = bag5 + bag3;
                break;
            }
        }

        if (bag5 == -1) {
            System.out.printf("Cheolsu cannot take %dkg of sugar.\n", num);
        } else {
            System.out.printf("Cheolsu will need to bring %d bags.\n", count);
        }
        in.close();
    }
    */
        int weight; // 철수가 배달할 설탕의 무게
        int bags = 0; // 철수가 배달할 설탕 봉지의 개수
        int max3kg; // 3kg 봉지로만 가져갈 경우 최대 개수
        int remain; // 3kg 봉지를 제하고 남은 설탕의 무게

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the weight of sugar to delivered: ");
        weight = in.nextInt();
        System.out.println();

        in.close();

        /*설탕 봉지를 최대한 적게 가져가려면 3kg 봉지를 최대한 적게 가져가야 한다.*/
        // 3kg 봉지로만 배달할 경우 최대 개수를 계산
        max3kg = weight / 3;

        //for 구문으로 3kg 봉지의 개수를 0에서부터 변수 max3kg까지 1씩 증가시키면서,
        // 남은 설탕의 무게를 5kg 봉지로 맞출 수 있는지 확인
        // 인덱스 변수 isms 3kg 봉지의 개수를 의미한다
        for (int i =0; i <= max3kg; i++) {
            // 3kg 봉지를 제하고 남은 설탕의 무게를 계산
            remain = weight - i * 3;
            // 3kg 봉지를 제하고 남은 설탕의 무게를 5kg 봉지로 맞출 수 있는지 확인
            if (remain % 5 == 0) {
                // (설탕 봉지의 개수) = (3kg 봉지의 개수) = (5kg 봉지의 개수)
                bags = i + remain / 5;

                // 설탕 봉지를 몇 개 가져갈지 계산이 끝났으므로 반복문을 종료
                break;
            }
        }

        if (bags > 0) {
            System.out.printf("Cheolsu will need to bring %d bags.\n", bags);
        } else {
            System.out.printf("Cheolsu cannot take %d kg of sugar.\n", weight);
        }
    }
}

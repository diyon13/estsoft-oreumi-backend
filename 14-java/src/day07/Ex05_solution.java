package day07;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex05_solution {
    public static void main(String[] args) {
        int korean;
        int math;
        int english;
        double average;

        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter Korean score: ");
                korean = in.nextInt();

                System.out.print("Enter math score: ");
                math = in.nextInt();

                System.out.print("Enter english score: ");
                english = in.nextInt();
                System.out.println();

                // 사용자가 입력한 점수를 검사
                if (korean < 0 || math < 0 || english < 0) {
                    throw new NegativeScoreException();
                } else if (korean > 100 || math > 100 || english > 100) {
                    throw new InvalidScoreException();
                }

                // 사용자가 유효한 값을 입력한 경우 반복문을 종료
                break;
            } catch (NegativeScoreException | InvalidScoreException e) {
                System.out.println(e.getMessage());
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input entered.\nTry again.");
                System.out.println();
                // 예외가 발생하면, 읽어오지 못한 그 값은 입력 스트림에 그대로 남아 있게 된다.
                // 입력 스트림에 남아 있는 값을 모두 읽어와야 사용자가 다시 값을
                // 입력하고 그 값을 읽어올 수 있다.
                in.nextLine();
            }
        }
        in.close();
        average = (double)(korean + math + english) / 3;
        System.out.printf("Average: %.1f\n", average);
    }
}

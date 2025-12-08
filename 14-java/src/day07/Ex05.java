package day07;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex05 {
    public static void main(String[] args) {
        int korean;
        int math;
        int english;
        double average;

        try (Scanner in = new Scanner(System.in)) {
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
                    }
                    if (korean > 100 || math > 100 || english > 100) {
                        throw new InvalidScoreException();
                    }

                    average = (double) (korean + math + english) / 3;
                    System.out.printf("Average: %.1f\n", average);
                    break;
                } catch (NegativeScoreException | InvalidScoreException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR: Invalid input entered.");
                    System.out.println();
                    in.nextLine();
                }
            }
        }
    }
}

package day07;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        int korean;
        int math;
        int english;
        double average;

        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter Korean score: ");
            korean = in.nextInt();

            System.out.print("Enter Math score: ");
            math = in.nextInt();

            System.out.print("Enter English score: ");
            english = in.nextInt();
            System.out.println();

            // 사용자가 입력한 점수를 검사
            if (korean < 0 || math < 0 || english < 0) {
                throw new NegativeScoreException();
            }

            average = (double) (korean + math + english) / 3;

            System.out.println("Average: " + average);
        } catch (NegativeScoreException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        } catch (InputMismatchException e) {
            System.err.println("ERROR: Invalid input entered.");
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }

    }
}

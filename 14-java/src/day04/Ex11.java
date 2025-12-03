package day04;

public class Ex11 {
    public static void main(String[] args) {
        int sum = 0;
        double average;

        int[][]  scores = {
                { 87, 43, 64, 71 },
                { 65, 69, 77, 82 },
                { 91, 84, 62, 76 }
        };

        final String[] ordinals = { "1st", "2nd", "3rd" };

        for (int i = 0; i < scores.length; i++) {
            sum = 0;

            for (int j = 0; j < scores[i].length; j++) {
                sum += scores[i][j];
            }

            average = (double)sum / scores[i].length;

            System.out.printf("%s column: sum = %d, average = %.2f\n", ordinals[i], sum, average);
        }
    }
}

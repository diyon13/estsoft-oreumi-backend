package day04;

public class Ex02 {
    public static void main(String[] args) {
//        int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("numbers[%d] = %d\n", i, numbers[i]);
        }

        System.out.println();

        for (int item : numbers) {
            System.out.println("item = " + item);
        }
    }
}

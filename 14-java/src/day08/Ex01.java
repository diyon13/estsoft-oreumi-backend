package day08;

import java.util.Arrays;

public class Ex01 {
    public static void main(String[] args) {
        int[] numbers = new int[6];

        for (int i = 0; i < numbers.length; i++) {
            int n = (int)(Math.random() * 45);
        }

        Arrays.sort(numbers);

        System.out.println("Numbers: " + Arrays.toString(numbers));
    }
}

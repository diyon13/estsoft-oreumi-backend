package day04;

public class Ex05 {
    public static void main(String[] args) {
        int[] numbers = { 1, 3, 4, 3, 1, 3, 4, 1, 2, 3, 1, 3 };
        int[] counts = new int[5];

        for (int item : numbers) {
            /*
            if (item == 1) counts[1]++;
            else if (item == 2) counts[2]++;
            else if (item == 3) counts[3]++;
            else if (item == 4) counts[4]++;
             */
            counts[item]++;
        }

        for (int i = 1; i < counts.length; i++) {
            System.out.print(i + " ");
            /*
            for (int j = 0; j < counts[i]; j++) System.out.print("*");
            System.out.println();
             */
            System.out.println("*".repeat(counts[i]));
        }


    }
}

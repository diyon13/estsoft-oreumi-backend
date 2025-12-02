package day03;

import java.util.Scanner;

public class Ex09 {
    public static void main(String[] args) {
        int treeHeight = 0;
        int riseDay = 0;
        int slidingNight = 0;
        int days = 0;
        int snailHeight = 0;

        Scanner in =  new Scanner(System.in);
        System.out.print("Enter the tree height: ");
        treeHeight = in.nextInt();
        System.out.print("Enter the height that rises during the day: ");
        riseDay = in.nextInt();
        System.out.print("Enter the sliding height at night: ");
        slidingNight = in.nextInt();

        in.close();

        if (slidingNight >= riseDay) {
            System.out.println("ERROR: The snail can never reach the top of the tree.");
            return;
        }

        while (true) {
            days++;
            snailHeight += riseDay;

            if (snailHeight >= treeHeight) break;

            snailHeight -= slidingNight;
        }

        System.out.println();
        System.out.printf("It takes %d days for the snail to climb the tree.\n", days);
    }
}

package day03;

import java.util.Scanner;

public class Ex10 {
    public static void main(String[] args) {
        int num;
        int hours;
        int minutes;

        Scanner in =  new Scanner(System.in);

        while(true){
            System.out.print("Enter minutes: ");
            num = in.nextInt();

            if (num == 0) break;


            hours = num / 60;
            minutes = num % 60;
/*
            String hourLabel = (hours > 1) ? "hours" : "hour";
            String minLabel = (minutes > 1) ? "minutes" : "minute";

            if (hours > 0 && minutes > 0) {
                System.out.printf("%d minutes = %d %s %d %s\n\n", num, hours, hourLabel, minutes, minLabel);
            } else if (hours > 0) {
                System.out.printf("%d minutes = %d %s\n\n", num, hours, hourLabel);
            } else {
                System.out.printf("%d minutes = %d %s\n\n", num, minutes, minLabel);
            }

 */
            if (hours >= 1)
                System.out.printf("%d %s ", hours, hours > 1 ? "hours" : "hour");

            if (minutes >= 1)
                System.out.printf("%d %s", minutes, minutes > 1 ? "minutes" : "minute");

            System.out.println("\n");
        }

        System.out.println("Good bye!");

        in.close();
    }
}

package day02;

import java.util.Scanner;

public class day02Ex01 {
    public static void main(String[] args) {
        int wage;
        int hours;
        double salary;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter hourly wage: ");
        wage = in.nextInt();

        System.out.print("Enter hourly hourly wage: ");
        hours = in.nextInt();

        if (hours <= 8) {
            salary = wage * hours;
        } else {
            salary = wage * 8 + (hours - 8) * (wage * 1.5);
        }
        System.out.println();

        System.out.printf("Today's wage is %.0f won\n", salary);

//        근무시간에 따른 급여 = 시급 * 근무시간
        salary = (double)hours * wage;

        if (hours > 8)
            salary += wage * 0.5 * (hours - 8);

        System.out.printf("Today's wage is %.0f won\n", salary);

        in.close();
    }
}

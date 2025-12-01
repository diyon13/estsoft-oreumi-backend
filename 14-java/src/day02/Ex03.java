package day02;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        int radius;
        final double PI = 3.141592;
        double area;
        double circumference;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter a radius of a circle as an integer: ");

        radius = in.nextInt();
        System.out.println();

        in.close();

        area = PI * radius * radius;
        circumference = 2 * PI * radius;

        System.out.println("pi = " + PI);
        System.out.println("radius of the circle = " + radius);
        System.out.printf("area of the circle %.3f\n", area);
        System.out.printf("circumference of the circle %.3f\n", circumference);

    }
}

package day05;

import java.util.Scanner;

public class Ex04 {
    /*
    @param radius 원의 반지름
    @return 원의 넓이
     */
    public static void main(String[] args) {
        int radius;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the radius of the circle: ");
        radius = in.nextInt();
        System.out.println();

        in.close();

        System.out.printf("Area of circle with radius %d = %g\n", radius, getAreaCircle(radius));
    }

    public static double getAreaCircle(int radius) {
        return Math.PI * radius * radius;
    }
}

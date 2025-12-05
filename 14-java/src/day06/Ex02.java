package day06;

import day05.Student;

public class Ex02 {
    public static void main(String[] args) {
        // 객체 배열(object array) 생성
        Student[] list = new Student[3];

        // 객체를 생성해서, 객체 배열의 각 원소, 즉 참조 변수에 대입
        list[0] =  new Student("Chealsu Kim", 88, 76, 64);
        list[1] =  new Student("Yeonghee Park", 92, 88, 94);
        list[2] =  new Student("Gildong Hong", 96, 64, 48);

        // for-each 구문으로 객체 배열의 원소에 차례대로 접근
        for (Student student : list) {
            student.print();
        }
    }
}

package day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex05 {
    public static void main(String[] args) {
        /*
        Student[] students = {
                new Student("20255103", "Hans"),
                new Student("20255114", "Michael"),
                new Student("20255108", "James")
        };

        Arrays.sort(students);

        for (Student item : students) {
            System.out.println(item);
        }
        */

        // ArrayList 클래스의 인스턴스 생성
        List<Student> students = new ArrayList<>();

        // students 객체에 Student 클래스의 인스턴스를 추가
        students.add(new Student("20255108", "James"));
        students.add(new Student("20255114", "Michael"));
        students.add(new Student("20255103", "Hans"));

        // 리스트 출력
        System.out.println(students);

        // java.util.Collections 클래스의 sort 메서드로 리스트를 오름차순으로 정렬
        Collections.sort(students);

        // 리스트 출력
        System.out.println(students);
        // java.util.Collections 클래스의 sort 메서드로 리스트를 내림차순으로 정렬
        // 내림차순으로 정렬하기 위해 sort 메서드의 두 번째 인자로 Collections 클래스의
        // reverseOrder 메서드를 지정한다
        // Collections.sort(students, Collections.reverseOrder());

        // List 인터페이스의 sort 메서드로 리스트를 내림차순으로 정렬
        students.sort(Collections.reverseOrder());

        // 리스트 출력
        System.out.println(students);

    }
}

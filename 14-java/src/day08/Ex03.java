package day08;

import java.util.ArrayList;
// import java.util.LinkedList;
import java.util.List;

public class Ex03 {
    public static void main(String[] args) {
        // ArrayList 클래스의 인스턴스를 생성
        // ArrayList<String> list = new ArrayList<>();
         List<String> list = new ArrayList<>();
        //List<String> list = new LinkedList<>();

        // add 메서드로 ArrayList 클래스의 인스턴스에 새로운 요소(element)를 추가
        list.add("Milk");
        list.add("Bread");
        list.add("Butter");
        list.add("Milk");
        list.add("Banana");

        // add 메서드로 ArrayList 클래스의 인스턴스에서 원하는 위치(인덱스)에 새로운 요소를 추가
        list.add(1, "Apple");

        // set 메서드로 ArrayList 클래스의 인스턴스에서 지정한 위치(인덱스)에 있는 요소를 교체
        list.set(2, "Grape");

        // remove 메서드로 Arraylist 클래스의 인스턴스에서 지정한 위치(인덱스)에 있는 요소를 제거
        String removed = list.remove(3);

        System.out.println("removed = " + removed);

        // for 구문으로 ArrayList 클래스의 인스턴스의 요소에 차례대로 접근
        for (int i = 0; i < list.size(); i++){
            System.out.printf("List element %d: %s\n", i , list.get(i));
        }

        System.out.println();

        // for-each 구문으로 ArrayList 클래스 인스턴스의 요소에 차례대로 접근
        for (String element : list){
            System.out.println("element = " + element);
        }
    }
}

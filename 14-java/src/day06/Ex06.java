package day06;

public class Ex06 {
    public static void main(String[] args) {
        //객체 생성
        Teacher potter = new Teacher(
                "Harry potter",
                37,
                Gender.MALE,
                371,
                "Defence of the Dark"
        );

        Student rose = new Student(
                "Rose Granger-Weasley",
                11,
                Gender.FEMALE,
                7128,
                2,
                4.2
        );

        //출력
        potter.print();          //Teacher#print();
        rose.print();           //Student#print();

        System.out.println("-------------------------------------------------");


        // 슈퍼 클래스의 참조 변수로 서브 클래스의 인스턴스를 참조할 수 있다.
        Person person = potter;

        // 슈퍼 클래스의 참조 변수로 서브 클래스의 인스턴스를 참조하는경우,
        // 슈퍼 클래스의 참조 변수로 서브 클래스의 멤버에는 접근할 수 없다.
        // int id = person.setId();      //ERROR:

        // 슈퍼 클래스의 참조 변수로 서브 클래스의 멤버에 접근하려면,
        // 슈퍼 클래스의 참조 변수로 서브 클래스로 형 변환해야 한다.
        // int id = ((Teacher)person).getId();

        // 재정의한 메서드는 동적 바인딩(dynamic binding)을 한다.
        person.print();         // Teacher#print();

        person = rose;

        person.print();         // Student#print();

        System.out.println("-------------------------------------------------");

        // Person 클래스의 객체 배열 생성
        Person[] list = new Person[2];

        //슈퍼 클래스의 참조 변수로 서브 클래스의 인스턴스를 참조할 수 있다.
        list[0] = potter;
        list[1] = rose;

        // for-each 구문으로 객체 배열 list의 원소에 차례대로 접근
        for(Person item : list) {
            item.print();
        }


    }
}

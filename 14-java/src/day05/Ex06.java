package day05;

public class Ex06 {
    public static void main(String[] args) {
        // new 연산자로 Sample 클래스의 인스턴스를 생성하고, 참조 변수에 대입
        Sample sample = new Sample();

        /*
        // 멤버 접근 연산자(.)로 객체의 멤버(필드나 메서드)에 접근
        sample.data = 10;

        // 객체 sample의 print 메서드를 호출
        sample.print();

        // 멤버 접근 연산자(.)로 객체의 멤버(필드나 메서드)에 접근
        sample.data = -10;

        // 객체 sample의 print 메서드를 호출
        sample.print();
         */

        // 설정자로 객체 sample의 private 멤버인 data에 값을 설정
        sample.setData(10);

        // 접근자로 객체 sample의 private 멤버인 data의 값을 참조
        System.out.println("sample.data = " + sample.getData());
    }
}

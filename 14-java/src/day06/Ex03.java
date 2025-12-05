package day06;

public class Ex03 {
    public static void main(String[] args) {
        // 객체 생성
        DeskLamp lamp = new DeskLamp();

        // 책상 조명등을 켜고 상태를 출력
        lamp.turnOn();
        lamp.printStatus();

        lamp.turnOn().printStatus();

        // 책상 조명등을 끄고 상태를 출력
        lamp.turnOff();
        lamp.printStatus();

        lamp.turnOff().printStatus();
    }
}

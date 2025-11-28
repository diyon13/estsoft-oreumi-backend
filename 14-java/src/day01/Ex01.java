package day01;

public class Ex01 {
    public static void main(String[] args) {
        int value = 100;
        double factor = 0.98;
        char grade = 'A';

        System.out.println("Value: " + value + ", Factor: " + factor + ", Grade: " + grade);
//        sout을 입력하면 System.out.println이 자동으로 입력 된다
//        형식 태그에 정밀도를 지정해서 소수점 이하 둘째 자리(소수점 두번째에서 반올림)까지 출력
        System.out.printf("value: %d, Factor: %.2f, Grade: %c\n", value, factor, grade);
    }
}

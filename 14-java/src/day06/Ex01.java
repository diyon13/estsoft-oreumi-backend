package day06;

public class Ex01 {
    public static void main(String[] args) {
        //객체를 생성하기 이전에 클래스 식별자로 정적 멤버에 접근
        System.out.println("sample2.numInstances = " + Sample.getNumInstances());
        System.out.println();

        //객체 생성
        Sample sample1 = new Sample(10);
        Sample sample2 = new Sample(265);

        //출력
        System.out.println("sample1.data = " + sample1.getData());
        System.out.println("sample2.data = " + sample2.getData());

        /*
        System.out.println("sample1.numInstances = " + sample1.getNumInstances());
        System.out.println("sample2.numInstances = " + sample2.getNumInstances());
         */

        System.out.println("sample2.numInstances = " + Sample.getNumInstances());
    }
}

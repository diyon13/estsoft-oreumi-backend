package day06;

public class Ex05 {
    public static void main(String[] args) {
        //SampleSuper 클래스의 인스턴스를 생성
        SampleSuper sampleSuper = new SampleSuper(10);

        sampleSuper.print();            // SampleSuper#print();
        System.out.println("--------------------------------------------------");

        //SampleSub 클래스의 인스턴스를 생성
        SampleSub sampleSub = new SampleSub(20, 30);

        sampleSub.print();              // SampleSub#print();
    }
}

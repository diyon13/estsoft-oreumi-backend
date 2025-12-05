package day06;

public class Sample {
    //필드
    private int data;

    //정적 변수(static variables; 클래스 변수)
    private static int numInstances = 0;

    //정적 상수(static constant)
    public static final int MAX = 255;

    //메서드
    public Sample(int data) {
        this.data = Math.min(data, MAX);

        // 새로운 인스턴스가 생성될 때마다 정적 변수 numInstances를 증가
        numInstances++;
    }
    public Sample() { this.data = 0; }

    //설정자
    public void setData(int data) { this.data = Math.min(data, MAX); }

    //접근자
    public int getData() { return data; }

    //정적 메서드(static methods)
    public static int getNumInstances() { return numInstances; }

    //출력
    public void print() {
        System.out.println("data= " + data);
    }
}

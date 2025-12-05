package day06;

public class SampleSub extends SampleSuper {
    //필드
    private int dataSub;

    //메서드
    public SampleSub(int dataSuper, int dataSub) {
        // super 메서드로 슈퍼 클래스의 생성자를 호출
        super(dataSuper);

        this.dataSub = dataSub;

        System.out.println(">>> SampleSub#SampleSub(int, int);");
    }
    //기본 생성자
    public SampleSub() {
//        this(0, 0);
        super(0);

        dataSub = 0;
        System.out.println(">>> SampleSub#SampleSub()");
    }

    public void setDataSub(int dataSub) { this.dataSub = dataSub; }
    public int getDataSub() { return dataSub; }


    // 슈퍼 클래스의 print 메서드를 재정의(overriding)
    @Override
    public void print() {
//        System.out.println("dataSuper = "+ getDataSuper());
        super.print();
        System.out.println("dataSub: " + dataSub);
    }
}

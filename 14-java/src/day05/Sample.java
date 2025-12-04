package day05;

class Sample {
    // 필드(fields) 또는 인스턴스 변수(instance variables)
    // int data;
    // 제한: 필드 data는 양수(positive integers)만 대입할 수 있다.

    // 필드 data에 잘못된 값이 대입되는 것을 방지하기 위해 private 멤버로 선언
    private int data;

    // 생성자(constructors)
    public Sample(int data) {
        this.data = data;
    }

    public Sample() {
        // data = 0;
        this(0);
    }

    // 복사 생성자 (copy constructor)
    public Sample (Sample copy) {
        this(copy.data);
    }

    // 메서드(methods)

    // 설정자(setter)
    public void setData(int data) {
        if (data >= 0) {
            this.data = data;
        }
    }

    // 접근자(getter)
    public int getData() {
        return data;
    }

    // 출력
    public void print() {
        System.out.println("data = " + data);
    }
}

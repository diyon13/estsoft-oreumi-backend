package day08;

public class Box <T>{
    // 필드
    private T data;

    //메서드
    public Box(T data){ this.data = data; }
    public Box(){ this(null); }

    public void setData(T data){ this.data = data; }
    public T getData() { return data; }

    // toString 메서드 재정의
    @Override
    public String toString(){ return data.toString(); }
}
/*
// 제네릭 클래스의 참조 변수를 선언하거나 인스턴스를 생성하면,
// 타입 매개 변수에 전달된 자료형을 이용해 컴파일러가 Box<String> 클래스를 생성한다.
   public class Box <String>{
    // 필드
    private String data;

    //메서드
    public Box(String data){ this.data = data; }
    public Box(){ this(null); }

    public void setData(String data){ this.data = data; }
    public String getData() { return data; }

    // toString 메서드 재정의
    @Override
    public String toString(){ return data.toString(); }
*/

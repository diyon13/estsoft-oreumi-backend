package day06;

public class Student extends Person {
    //필드
    private int id;
    private int year;
    private double gpa;

    //메서드
    public Student( String name, int age, Gender gender, int id, int year, double gpa) {
        super(name, age, gender);
        setId(id);
        setYear(year);
        setGpa(gpa);
    }

    //기본 생성자
//    public Student() {
//        super(null, 0, null);
//        this.id = 0;
//        this.year = 0;
//        this.gpa = 0;
//    }
    public Student() { this(null, 0, Gender.UNDISCLOSED, 0, 0, 0); }

    //설정자
    public void setId(int id) {
        // TODO: 올바른 ID인지 검사하고 나서 필드에 설정
        this.id = id;
    }
    public void setYear(int year) {
        this.year = year >= 1 && year <= 7 ? year : 0;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa >= 0 && gpa <= 4.5 ? gpa : 0;
    }

    //접근자
    public int getId() { return id; }
    public int getYear() { return year; }
    public double getGpa() { return gpa; }

    //출력
    @Override
    public void print() {
        super.print();

        System.out.println("ID: " + id);
        System.out.println("Year: " + year);
        System.out.println("GPA: " + gpa);
        System.out.println();
    }

}

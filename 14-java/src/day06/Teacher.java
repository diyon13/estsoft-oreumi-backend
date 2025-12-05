package day06;

public class Teacher extends Person {
    // 필드
    private int id;
    private String subject;

    //메서드
    public Teacher(String name, int age, Gender gender, int id, String subject) {
        super(name, age, gender);
        setId(id);
        this.subject = subject;
    }

    //기본생성자
//    public Teacher() {
//        super(null, 0, null);
//        id = 0;
//        subject = null;
//    }
    public Teacher() { this(null, 0 , Gender.UNDISCLOSED, 0, null); }

    //설정자
    public void setId(int id) {
        // TODO: 올바른 ID인지 검사하고 나서 필드에 설정
        this.id = id;
    }
    public void setSubject(String subject) { this.subject = subject; }

    //접근자
    public int getId() { return id; }
    public String getSubject() { return subject; }

    //메서드 재정의
    @Override
    public void print() {
        super.print();

        System.out.println("ID: " + id);
        System.out.println("Subject: " + subject);
        System.out.println();
    }
}

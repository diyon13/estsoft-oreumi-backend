package day08;

 /*
 * Student 클래스의 인스턴스들을 정렬하기 위해서는 인스턴스들은 비교할 수 있어야 한다
 * 따라서
 */
public class Student implements Comparable<Student> {
    // 필드
    private  String id;
    private String name;

    // 메서드
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // 기본 생성자
    public Student() { this(null, null); }

    public void setId(String id) { this.id = id;}
    public void setName(String name) {this.name = name;}

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public int compareTo(Student obj) {
        return Integer.parseInt(id) - Integer.parseInt(obj.id);
    }

    @Override
    public String toString() { return "ID: " + id + " Name: " + name; }
}

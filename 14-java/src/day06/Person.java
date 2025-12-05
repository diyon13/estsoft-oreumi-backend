package day06;

public class Person {
    //필드
    private String name;
    private int age;
    private Gender gender;

    //메서드
    public Person(String name, int age, Gender gender) {
        this.name = name;
        setAge(age);
        this.gender = gender;
    }

    //기본 생성자
    public Person() { this("", 0, Gender.UNDISCLOSED); }

    // 설정자
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(Gender gender) { this.gender = gender; }

    // 접근자
    public String getName() { return name; }
    public int getAge() { return age; }
    public Gender getGender() { return gender; }

    //출력
    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }

}

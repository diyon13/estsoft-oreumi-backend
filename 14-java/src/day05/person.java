package day05;

public class person {
    // 필드
    private String name;
    private int age;

    // 메서드

    // 설정자
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int n) {
        // if (n >=0) age = n;
        age = Math.max(n, 0);
    }

    // 접근자
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // 출력
    public void print() {
        System.out.printf("Name: %s\n", name);
        System.out.printf("Age: %d\n", age);
        System.out.println();
    }
}

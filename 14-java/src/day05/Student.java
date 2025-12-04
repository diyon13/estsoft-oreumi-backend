package day05;

public class Student {
    private String name;
    private int korean;
    private int math;
    private int english;
    private double average;
    private char grade;
    private boolean isValid = true;

    // 초기화
    public Student() {
        this(null, 0, 0, 0);
    }

    // 유효성 검사
    private boolean score(int score) {
        if (score < 0 || score > 100) {
            return false;
        }
        return true;
    }

    public Student(String name, int Korean, int math, int english) {
        this.name = name;

        if (!score(Korean) || !score(math) || !score(english)) {
            isValid = false;
            return;
        }

        this.korean = Korean;
        this.math = math;
        this.english = english;

        calculateAverage();
        calculateGrade();
    }

    // 설정자
    public void setName(String name) { this.name = name; }
    public void setKorean(int Korean) { this.korean = Korean; }
    public void setMath(int math) { this.math = math; }
    public void setEnglish(int english) { this.english = english; }
    /*
    public void setAverage(double average) { this.average = average; }
    public void setGrade(char grade) { this.grade = grade; }
     */

    // 접근자
    public String getName() { return name; }
    public int getKorean() { return korean; }
    public int getMath() { return math; }
    public int getEnglish() { return english; }
    public double getAverage() { return average; }
    public char getGrade() { return grade; }

    // 평균 계산
    private void calculateAverage() {
        average = (double)(korean + math + english) / 3;
    }

    // 등급 계산
    private void calculateGrade() {
        if (average >= 90) grade = 'A';
        else if (average >= 80) grade = 'B';
        else if (average >= 70) grade = 'C';
        else if (average >= 60) grade = 'D';
        else grade = 'F';
    }

    // 출력
    public void print() {
        if (!isValid) {
            System.out.println("제대로 입력하세요.");
            System.out.println();
            return;
        }

        System.out.printf("name: %s\n", name );
        System.out.printf("Average: %.1f\n", average );
        System.out.printf("Grade: %c\n", grade );
        System.out.println();
    }
}

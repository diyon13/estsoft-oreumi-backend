package day05;

public class Student {
    // 필드
    private String name;
    private int korean;
    private int math;
    private int english;
    private double average;
    private char grade;
    private boolean isValid = true;

    // 유효성 검사
//    private boolean score(int score) {
//        if (score < 0 || score > 100) {
//            return false;
//        }
//        return true;
//    }

    // 메서드
    // 생성자
    public Student(String name, int Korean, int math, int english) {
        this.name = name;

//        if (!score(Korean) || !score(math) || !score(english)) {
//            isValid = false;
//            return;
//        }
//
//        this.korean = Korean;
//        this.math = math;
//        this.english = english;

        setKorean(Korean);
        setMath(math);
        setEnglish(english);

        average = 0;
        grade = 'F';
    }

    // 초기화
    public Student() {
        this(null, 0, 0, 0);
    }

    // 설정자
    public void setName(String name) { this.name = name; }

    public void setKorean(int Korean) {
//        if (Korean >=0 && Korean <= 100) this.korean = Korean;
//        this.korean = korean >= 0 && korean <= 100 ? korean : 0;
        this.korean = isValidScore(Korean) ? Korean : 0;
    }
    /**
    * 유효한 점수인지 확인
     *
    * @param score 확인하고자 하는 점수
    * @return 유효한 점수인지 여부
    */
    private  boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    public void setMath(int math) {
        this.math = isValidScore(math) ? math : 0;
    }

    public void setEnglish(int english) {
        this.english = isValidScore(english) ? english : 0;
    }

    /*
    public void setAverage(double average) { this.average = average;
    }
    public void setGrade(char grade) { this.grade = grade; }
     */

    // 접근자
    public String getName() { return name; }
    public int getKorean() { return korean; }
    public int getMath() { return math; }
    public int getEnglish() { return english; }

    public double getAverage() {
        average = (double)(korean + math + english) / 3;
        return average;
    }

    public char getGrade() {
        // 등급을 구하기 위해 먼저 평균 점수부터 구한다.
        getAverage();

        if (average >= 90) grade = 'A';
        else if (average >= 80) grade = 'B';
        else if (average >= 70) grade = 'C';
        else if (average >= 60) grade = 'D';
        else grade = 'F';

        return grade;
    }

    // 평균 계산
//    private void calculateAverage() {
//        average = (double)(korean + math + english) / 3;
//    }

    // 등급 계산
//    private void calculateGrade() {
//        if (average >= 90) grade = 'A';
//        else if (average >= 80) grade = 'B';
//        else if (average >= 70) grade = 'C';
//        else if (average >= 60) grade = 'D';
//        else grade = 'F';
//    }

    // 출력
//    public void print() {
//        if (!isValid) {
//            System.out.println("제대로 입력하세요.");
//            System.out.println();
//            return;
//        }
    public void print() {
        // 출력하기 전에 평균 점수와 등급을 먼저 구한다.
        getGrade();

        System.out.println("name: " + name);
        System.out.printf("Average: %.1f\n", average);
        System.out.println("Grade: " + grade);
        System.out.println();
    }
}

package day05;

public class Ex07 {
    public static void main(String[] args) {
        person jackson = new person();
        person carey = new person();
        person dean = new person();

        jackson.setName("Michael Jackson");
        jackson.setAge(24);

        carey.setName("Mariah Carey");
        carey.setAge(21);

        dean.setName("James Dean");
        dean.setAge(36);

        jackson.print();
        carey.print();
        dean.print();
    }
}

package day05;

public class Ex08 {
    public static void main(String[] args) {

        Sample sample = new Sample(10); // sample#Sample(int);

        sample.print();

        Sample sample2 = new Sample(sample);  // Sample#Sample(Sample);

        System.out.println("sample2.data = " + sample2.getData());
    }
}

package day02;

public class day02Ex03 {
    public static void main(String[] args) {

        final int PRINCIPAL = 50000;
        final double CS_INT_RATE = 0.05;
        final double YH_INT_RATE = 0.03;

        int cs_balance = PRINCIPAL;
        int yh_balance = PRINCIPAL;
        int year = 0;

        do {
            year++;
            cs_balance += (int)(PRINCIPAL * CS_INT_RATE);
            yh_balance += (int)(yh_balance * YH_INT_RATE);

        } while (yh_balance <= cs_balance);

        System.out.printf("after %d years, cs = %d, yh = %d\n", year, cs_balance, yh_balance);
    }
}

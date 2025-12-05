package day06;

public class Ex04 {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("Harry Potter", 13275);

        myAccount.print();
        myAccount.deposit(10000).printBalance();
        myAccount.withdraw(5000).printBalance();
        myAccount.withdraw(10000).printBalance();
        myAccount.withdraw(-10000).printBalance();
    }
}


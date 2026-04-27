package MIT;

//BankAccountSystem.java
abstract class BankAccount {
 String accountNumber;
 double balance;

 BankAccount(String accountNumber, double balance) {
     this.accountNumber = accountNumber;
     this.balance = balance;
 }

 abstract double calculateInterest();
}

class SavingsAccount extends BankAccount {
 SavingsAccount(String accountNumber, double balance) {
     super(accountNumber, balance);
 }
 double calculateInterest() {
     return balance * 0.04; // 4% interest
 }
}

class CurrentAccount extends BankAccount {
 CurrentAccount(String accountNumber, double balance) {
     super(accountNumber, balance);
 }
 double calculateInterest() {
     return balance * 0.02; // 2% interest
 }
}

public class BankAccountSystem {
 public static void main(String[] args) {
     BankAccount acc;

     acc = new SavingsAccount("S123", 10000);
     System.out.println("Savings Account Interest: " + acc.calculateInterest());

     acc = new CurrentAccount("C456", 20000);
     System.out.println("Current Account Interest: " + acc.calculateInterest());
 }
}

package BankAccountSystem;
public class BankAccount {

    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(String source, int amount) {
        System.out.println(source + " is depositing ₹" + amount);

        try {
            Thread.sleep(1000); // Simulate processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        balance += amount;
        System.out.println("Deposit successful by " + source);
        System.out.println("Current Balance: ₹" + balance);
        System.out.println("--------------------------------");
    }
    public synchronized void withdraw(String source, int amount) {
        System.out.println(source + " is trying to withdraw ₹" + amount);

        if (amount <= balance) {
            try {
                Thread.sleep(1000); // Simulate processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println("Withdrawal successful by " + source);
        } else {
            System.out.println("Withdrawal failed by " + source + " (Insufficient Balance)");
        }
        System.out.println("Current Balance: ₹" + balance);
        System.out.println("--------------------------------");
    }
}

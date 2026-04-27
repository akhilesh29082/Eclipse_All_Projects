package BankAccountSystem;

public class BankAccountSystemMain {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(5000);

      
        TransactionThread t1 = new TransactionThread(account, "ATM", "withdraw", 3000);
        TransactionThread t2 = new TransactionThread(account, "Online Banking", "withdraw", 2500);
        TransactionThread t3 = new TransactionThread(account, "Mobile App", "deposit", 2000);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All transactions completed.");
    }
}


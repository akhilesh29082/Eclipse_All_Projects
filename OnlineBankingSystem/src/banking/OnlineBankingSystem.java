package banking;

public class OnlineBankingSystem {
    public static void main(String[] args) {

        BankAccount acc1 = new SavingsAccount("SA101", "Akshay", 5000);
        BankAccount acc2 = new CurrentAccount("CA202", "Rahul", 3000);

        Transaction transaction = new TransactionImpl();

        try {
            acc1.deposit(2000);
            acc1.withdraw(1000);

            transaction.transferFunds(acc1, acc2, 1500);

            System.out.println("Acc1 Balance: ₹" + acc1.getBalance());
            System.out.println("Acc2 Balance: ₹" + acc2.getBalance());

            transaction.viewTransactionHistory();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

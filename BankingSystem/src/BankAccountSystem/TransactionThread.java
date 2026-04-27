package BankAccountSystem;

public class TransactionThread extends Thread {

    private BankAccount account;
    private String transactionType;
    private int amount;
    private String source;

    public TransactionThread(BankAccount account, String source, String transactionType, int amount) {
        this.account = account;
        this.source = source;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (transactionType.equalsIgnoreCase("deposit")) {
            account.deposit(source, amount);
        } else if (transactionType.equalsIgnoreCase("withdraw")) {
            account.withdraw(source, amount);
        }
    }
}

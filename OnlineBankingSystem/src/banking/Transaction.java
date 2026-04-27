package banking;

interface Transaction {
    void transferFunds(BankAccount from, BankAccount to, double amount)
            throws InsufficientBalanceException, NegativeAmountException, InvalidAccountException;

    void viewTransactionHistory();
}

package banking;

class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void deposit(double amount) throws NegativeAmountException {
        if (amount <= 0)
            throw new NegativeAmountException("Deposit amount must be positive");
        balance += amount;
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientBalanceException, NegativeAmountException {

        if (amount <= 0)
            throw new NegativeAmountException("Withdrawal amount must be positive");

        if (amount > balance)
            throw new InsufficientBalanceException("Insufficient balance");

        balance -= amount;
    }
}

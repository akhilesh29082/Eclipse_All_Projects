package banking;

import java.util.ArrayList;
import java.util.List;

class TransactionImpl implements Transaction {

    private List<String> transactionHistory = new ArrayList<>();

    @Override
    public void transferFunds(BankAccount from, BankAccount to, double amount)
            throws InsufficientBalanceException, NegativeAmountException, InvalidAccountException {

        if (from == null || to == null)
            throw new InvalidAccountException("Invalid account number");

        from.withdraw(amount);
        to.deposit(amount);

        transactionHistory.add("Transferred ₹" + amount +
                " from " + from.getAccountNumber() +
                " to " + to.getAccountNumber());
    }

    @Override
    public void viewTransactionHistory() {
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}

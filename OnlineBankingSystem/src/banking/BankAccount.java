package banking;

public abstract class BankAccount {
	
	    protected String accountNumber;
	    protected String holderName;
	    protected double balance;

	    public BankAccount(String accountNumber, String holderName, double balance) {
	        this.accountNumber = accountNumber;
	        this.holderName = holderName;
	        this.balance = balance;
	    }

	    public abstract void deposit(double amount) throws NegativeAmountException;
	    public abstract void withdraw(double amount)
	            throws InsufficientBalanceException, NegativeAmountException;

	    public double getBalance() {
	        return balance;
	    }

	    public String getAccountNumber() {
	        return accountNumber;
	    }
	}



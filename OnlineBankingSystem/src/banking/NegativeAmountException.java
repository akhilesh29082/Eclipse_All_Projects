package banking;

class NegativeAmountException extends Exception {
    public NegativeAmountException(String message) {
        super(message);
    }
}

package s5;

public interface Payment {
    void processPayment(double amount) throws Exception;
    void refundPayment(double amount);
}

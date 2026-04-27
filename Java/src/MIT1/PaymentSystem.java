package MIT1;

//PaymentSystem.java
import java.util.Scanner;

interface PaymentGateway {
 void makePayment(double amount);
 String getPaymentStatus();
}

class CreditCardPayment implements PaymentGateway {
 private String status;

 public void makePayment(double amount) {
     System.out.println("Processing Credit Card Payment of ₹" + amount);
     status = "Payment Successful";
 }

 public String getPaymentStatus() {
     return status;
 }
}

class UPIPayment implements PaymentGateway {
 private String status;

 public void makePayment(double amount) {
     System.out.println("Processing UPI Payment of ₹" + amount);
     status = "Payment Successful";
 }

 public String getPaymentStatus() {
     return status;
 }
}

public class PaymentSystem {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     System.out.println("Choose Payment Method: 1. Credit Card  2. UPI");
     int choice = sc.nextInt();

     System.out.print("Enter amount: ₹");
     double amount = sc.nextDouble();

     PaymentGateway payment;

     if (choice == 1)
         payment = new CreditCardPayment();
     else
         payment = new UPIPayment();

     payment.makePayment(amount);
     System.out.println("Status: " + payment.getPaymentStatus());
     sc.close();
 }
}
 
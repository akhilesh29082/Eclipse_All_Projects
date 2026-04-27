package MIT;

//MethodOverridingDemo.java

//Parent class
class Bank {
 double rateOfInterest() {
     return 5.0; // default interest rate
 }
}

//Subclass 1
class SBI extends Bank {
 // Overriding the method
 double rateOfInterest() {
     return 6.5;
 }
}

//Subclass 2
class HDFC extends Bank {
 // Overriding the method
 double rateOfInterest() {
     // Use super to call parent method
     double parentRate = super.rateOfInterest();
     System.out.println("Parent Bank interest rate: " + parentRate + "%");
     return 7.2;
 }
}

//Main class to demonstrate runtime polymorphism
public class MethodOverriding {
 public static void main(String[] args) {
     Bank b1; // reference variable of parent class

     // SBI object assigned to Bank reference
     b1 = new SBI();
     System.out.println("SBI Interest Rate: " + b1.rateOfInterest() + "%");

     // HDFC object assigned to Bank reference
     b1 = new HDFC();
     System.out.println("HDFC Interest Rate: " + b1.rateOfInterest() + "%");
 }
}

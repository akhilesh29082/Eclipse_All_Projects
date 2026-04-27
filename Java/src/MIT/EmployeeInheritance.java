package MIT;
//EmployeeInheritanceDemo.java

//Base class
class Employee {
 String name;
 double salary;

 // Constructor
 Employee(String name, double salary) {
     this.name = name;
     this.salary = salary;
 }

 // Method to display employee details
 void displayDetails() {
     System.out.println("Name: " + name);
     System.out.println("Salary: " + salary);
 }
}

//Subclass 1: Manager
class Manager extends Employee {
 double bonus;

 // Constructor
 Manager(String name, double salary, double bonus) {
     super(name, salary); // call parent constructor
     this.bonus = bonus;
 }

 // Overriding method
 @Override
 void displayDetails() {
     super.displayDetails(); // show base info
     System.out.println("Bonus: " + bonus);
     System.out.println("Total Salary: " + (salary + bonus));
     System.out.println("--------------------------------");
 }
}

//Subclass 2: Developer
class Developer extends Employee {
 String programmingLanguage;

 // Constructor
 Developer(String name, double salary, String programmingLanguage) {
     super(name, salary);
     this.programmingLanguage = programmingLanguage;
 }

 // Overriding method
 @Override
 void displayDetails() {
     super.displayDetails();
     System.out.println("Programming Language: " + programmingLanguage);
     System.out.println("--------------------------------");
 }
}

//Main class
public class EmployeeInheritance {
 public static void main(String[] args) {
     // Creating objects
     Manager m1 = new Manager("Abhishek", 75000, 15000);
     Developer d1 = new Developer("Akhilesh", 60000, "Java");

     // Demonstrating method overriding
     System.out.println("=== Manager Details ===");
     m1.displayDetails();

     System.out.println("=== Developer Details ===");
     d1.displayDetails();

     // Runtime polymorphism
     Employee emp;
     emp = new Manager("Krish", 80000, 20000);
     System.out.println("=== Polymorphism (Manager) ===");
     emp.displayDetails();

     emp = new Developer("Priti", 55000, "Python");
     System.out.println("=== Polymorphism (Developer) ===");
     emp.displayDetails();
 }
}

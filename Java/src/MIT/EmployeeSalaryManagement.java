package MIT;

//EmployeeSalaryManagement.java
abstract class Employe {
 String name;
 double salary;

 Employe(String name, double salary) {
     this.name = name;
     this.salary = salary;
 }

 abstract double calculateBonus();
}

class Manage extends Employe{
 Manage(String name, double salary) {
     super(name, salary);
 }
 double calculateBonus() {
     return salary * 0.20;
 }
}

class Develope extends Employe {
 Develope(String name, double salary) {
     super(name, salary);
 }
 double calculateBonus() {
     return salary * 0.10;
 }
}

class Intern extends Employe {
 Intern(String name, double salary) {
     super(name, salary);
 }
 double calculateBonus() {
     return 0;
 }
}

public class EmployeeSalaryManagement {
 public static void main(String[] args) {
     Employe e;

     e = new Manage("Alice", 80000);
     System.out.println("Manager Bonus: " + e.calculateBonus());

     e = new Develope("Bob", 50000);
     System.out.println("Developer Bonus: " + e.calculateBonus());

     e = new Intern("Charlie", 20000);
     System.out.println("Intern Bonus: " + e.calculateBonus());
 }
}

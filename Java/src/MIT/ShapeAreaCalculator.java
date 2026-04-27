package MIT;

//ShapeAreaCalculator.java
abstract class Shape {
 abstract double area();
}

class Circle extends Shape {
 double radius;
 Circle(double radius) {
     this.radius = radius;
 }
 double area() {
     return Math.PI * radius * radius;
 }
}

class Rectangle extends Shape {
 double length, breadth;
 Rectangle(double length, double breadth) {
     this.length = length;
     this.breadth = breadth;
 }
 double area() {
     return length * breadth;
 }
}

class Triangle extends Shape {
 double base, height;
 Triangle(double base, double height) {
     this.base = base;
     this.height = height;
 }
 double area() {
     return 0.5 * base * height;
 }
}

public class ShapeAreaCalculator {
 public static void main(String[] args) {
     Shape s;

     s = new Circle(5);
     System.out.println("Circle Area: " + s.area());

     s = new Rectangle(4, 6);
     System.out.println("Rectangle Area: " + s.area());

     s = new Triangle(5, 8);
     System.out.println("Triangle Area: " + s.area());
 }
}

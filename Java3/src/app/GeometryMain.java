package app;

import geometry.*;
import java.util.*;

public class GeometryMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter radius of Circle: ");
        double r = sc.nextDouble();
        Circle c = new Circle(r);

        System.out.print("Enter length and breadth of Rectangle: ");
        double l = sc.nextDouble();
        double b = sc.nextDouble();
        Rectangle rect = new Rectangle(l, b);

        System.out.print("Enter 3 sides of Triangle: ");
        double x = sc.nextDouble();
        double y = sc.nextDouble();
        double z = sc.nextDouble();
        Triangle t = new Triangle(x, y, z);

        System.out.println("\n--- Geometry Results ---");
        System.out.println("Circle -> Area: " + c.area() + ", Perimeter: " + c.perimeter());
        System.out.println("Rectangle -> Area: " + rect.area() + ", Perimeter: " + rect.perimeter());
        System.out.println("Triangle -> Area: " + t.area() + ", Perimeter: " + t.perimeter());
    }
}

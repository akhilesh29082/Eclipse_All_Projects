package mainapp;

import calculator.*;
import java.util.*;

public class CalculatorMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        Addition add = new Addition();
        Subtraction sub = new Subtraction();
        Multiplication mul = new Multiplication();
        Division div = new Division();

        System.out.println("\nResults:");
        System.out.println("Addition: " + add.add(a, b));
        System.out.println("Subtraction: " + sub.subtract(a, b));
        System.out.println("Multiplication: " + mul.multiply(a, b));
        System.out.println("Division: " + div.divide(a, b));
    }
}

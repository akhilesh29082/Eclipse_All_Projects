package main;

import studentinfo.Student;
import result.Result;
import java.util.*;

public class ResultMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        System.out.print("Enter Marks: ");
        float marks = sc.nextFloat();

        Student s = new Student(name, roll, marks);
        Result r = new Result();

        System.out.println("\n--- Student Result ---");
        r.calculateGrade(s);
    }
}

package main;

import dao.FeePaymentDAO;
import dao.StudentDAO;
import model.Student;

import java.util.Scanner;

public class StudentFeeApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
        FeePaymentDAO feeDAO = new FeePaymentDAO();

        while (true) {
            System.out.println("\n1.Add Student\n2.View Students\n3.Pay Fee\n4.Fee History\n5.Delete Student\n6.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1:
                        Student s = new Student();
                        System.out.print("Name: ");
                        s.setName(sc.next());
                        System.out.print("Course: ");
                        s.setCourse(sc.next());
                        System.out.print("Base Fee: ");
                        s.setBaseFee(sc.nextDouble());
                        System.out.print("Discount: ");
                        s.setDiscount(sc.nextDouble());
                        studentDAO.addStudent(s);
                        System.out.println("Student Added");
                        break;

                    case 2:
                        studentDAO.viewStudents();
                        break;

                    case 3:
                        System.out.print("Student ID: ");
                        int id = sc.nextInt();
                        System.out.print("Month: ");
                        String month = sc.next();
                        feeDAO.payFee(id, month);
                        break;

                    case 4:
                        System.out.print("Student ID: ");
                        feeDAO.feeHistory(sc.nextInt());
                        break;

                    case 5:
                        System.out.print("Student ID: ");
                        studentDAO.deleteStudent(sc.nextInt());
                        System.out.println("Deleted Successfully");
                        break;

                    case 6:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
                   }
        
    }
}

package MIT2;

import java.util.*;

class Student {
    int rollNo;
    String name;
    float marks;

    Student(int rollNo, String name, float marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return "Roll No: " + rollNo + ", Name: " + name + ", Marks: " + marks;
    }
}

public class StudentList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Add new student");
            System.out.println("2. Display all students");
            System.out.println("3. Search by roll number");
            System.out.println("4. Remove by roll number");
            System.out.println("5. Display students in ascending order of marks");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    float marks = sc.nextFloat();
                    students.add(new Student(roll, name, marks));
                    break;

                case 2:
                    for (Student s : students)
                        System.out.println(s);
                    break;

                case 3:
                    System.out.print("Enter Roll No to search: ");
                    int search = sc.nextInt();
                    for (Student s : students) {
                        if (s.rollNo == search) {
                            System.out.println("Found: " + s);
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll No to remove: ");
                    int rem = sc.nextInt();
                    students.removeIf(s -> s.rollNo == rem);
                    break;

                case 5:
                    students.sort(Comparator.comparingDouble(s -> s.marks));
                    for (Student s : students)
                        System.out.println(s);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

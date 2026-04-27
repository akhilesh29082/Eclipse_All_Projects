package result;

import studentinfo.Student;

public class Result {
    public void calculateGrade(Student s) {
        char grade;
        if (s.marks >= 90)
            grade = 'A';
        else if (s.marks >= 75)
            grade = 'B';
        else if (s.marks >= 60)
            grade = 'C';
        else if (s.marks >= 40)
            grade = 'D';
        else
            grade = 'F';

        s.displayDetails();
        System.out.println("Grade: " + grade);
    }
}

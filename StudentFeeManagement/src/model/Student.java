package model;

public class Student {
    private int studentId;
    private String name;
    private String course;
    private double baseFee;
    private double discount;

    // getters & setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public double getBaseFee() { return baseFee; }
    public void setBaseFee(double baseFee) { this.baseFee = baseFee; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
}

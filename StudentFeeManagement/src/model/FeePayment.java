package model;

import java.sql.Date;

public class FeePayment {

    private int paymentId;
    private int studentId;
    private String month;
    private double totalFee;
    private String status;
    private Date paymentDate;

    // No-argument constructor
    public FeePayment() {}

    // Parameterized constructor
    public FeePayment(int studentId, String month, double totalFee, String status, Date paymentDate) {
        this.studentId = studentId;
        this.month = month;
        this.totalFee = totalFee;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}

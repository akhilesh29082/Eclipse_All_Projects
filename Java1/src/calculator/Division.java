package calculator;

public class Division {
    public double divide(double a, double b) {
        if (b == 0)
            throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }
}

package s5;

public class Customer extends User {

    public Customer(int userId, String name, String email) {
        super(userId, name, email);
    }

    @Override
    public void showUserType() {
        System.out.println("User Type: Customer");
    }
}

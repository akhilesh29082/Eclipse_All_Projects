package s5;

public class MainApp {
    static class Order {
        private int orderId;
        private String status = "Created";
        public Order(int orderId) {
            this.orderId = orderId;
        }
        public void trackOrder() throws Exception {
            if (orderId <= 0) {
                throw new Exception("Order not found!");
            }
            System.out.println("Order Status: " + status);
        }
        public void confirmOrder() {
            status = "Confirmed";
            System.out.println("Order confirmed.");
        }
    }
    static class CardPayment implements Payment {

        @Override
        public void processPayment(double amount) throws Exception {
            if (amount <= 0) {
                throw new Exception("Invalid payment amount!");
            }
            System.out.println("Payment of ₹" + amount + " successful.");
        }

        @Override
        public void refundPayment(double amount) {
            System.out.println("Refund of ₹" + amount + " processed.");
        }
    }

    public static void main(String[] args) {

        try {
            Customer customer = new Customer(1, "Amit", "amit@gmail.com");
            customer.showUserType();

            Product product = new Product(101, "Headphones", 2000, 5);
            int quantity = 2;

            product.reduceStock(quantity);
            double totalAmount = product.getPrice() * quantity;

            Payment payment = new CardPayment();
            payment.processPayment(totalAmount);

            Order order = new Order(1001);
            order.confirmOrder();
            order.trackOrder();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

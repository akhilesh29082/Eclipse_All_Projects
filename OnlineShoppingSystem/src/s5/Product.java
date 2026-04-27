package s5;

public class Product {
    private int productId;
    private String productName;
    private double price;
    private int stock;

    public Product(int productId, String productName, double price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void reduceStock(int quantity) throws Exception {
        if (quantity > stock) {
            throw new Exception("Out of stock product!");
        }
        stock -= quantity;
    }
}

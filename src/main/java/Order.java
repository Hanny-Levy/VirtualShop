import java.util.Date;

public class Order {
    private Date dateBuy ;
    private ShoppingCart shoppingCart;
    private double totalPrice;

    public Order(Client client, ShoppingCart shoppingCart) {
        this.dateBuy = new Date();
        this.shoppingCart = shoppingCart;
        client.getShoppingCart().getProducts().clear();
    }

    public Order(Employee employee, ShoppingCart shoppingCart) {
        this.dateBuy = new Date();
        this.shoppingCart = shoppingCart;
        employee.getShoppingCart().getProducts().clear();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

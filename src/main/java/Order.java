import java.util.Date;

public class Order {
    private Date dateBuy ;
    private ShoppingCart shoppingCart;
    private int totalPrice;

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

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}

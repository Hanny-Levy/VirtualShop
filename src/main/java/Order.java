import java.util.Date;

public class Order {
    Date dateBuy ;
    ShoppingCart shoppingCart;

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
}

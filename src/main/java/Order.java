
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
    private final LocalDate dateBuy ;
    private final ShoppingCart shoppingCart;
    private double totalPrice;


    public Order(ShoppingCart shoppingCart) {
        this.dateBuy = LocalDate.now();
        this.shoppingCart = shoppingCart;
        this.totalPrice=this.shoppingCart.getSumOfCart();

    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateBuy() {
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dateBuy.format(dateTimeFormatter);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void print(){
        this.shoppingCart.print();
    }



}

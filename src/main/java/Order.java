import java.util.Date;

public class Order {
    private Date dateBuy ;
    private ShoppingCart shoppingCart;
    private double totalPrice;


    public Order(Employee employee, ShoppingCart shoppingCart) {
        this.dateBuy = new Date();
        this.shoppingCart = shoppingCart;
        this.totalPrice=this.shoppingCart.getSumOfCart();
        //employee.getShoppingCart().getProducts().clear();

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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void print(){
        this.shoppingCart.print();
    }

}

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private HashMap<Product,Integer> products;

    public ShoppingCart(User user) {
        this.products = new HashMap<Product, Integer>();
    }

    private void addProduct(Product product,int amount){
        this.products.put(product,amount);
    }
}

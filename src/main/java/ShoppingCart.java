import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private HashMap<Product,Integer> products;
    private static int sumOfCart;

    public ShoppingCart(User user) {
        this.products = new HashMap<Product, Integer>();
        this.sumOfCart=0;
    }

    public void print(){
        System.out.println("My shopping cart :");
        for(Product product : products.keySet()) {
            product.print();
        }

    }

    private void setSumOfCart(Employee client) {
        for(Product product : products.keySet()) {
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println("products.get(product) " +products.get(product));
            System.out.println("this.sumOfCart " + this.sumOfCart);
            System.out.println("*************************");
            this.sumOfCart+=(product.getPrice()*products.get(product));
            System.out.println(this.sumOfCart);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");

        }

    }

    public int getSumOfCart(Employee client) {
        this.setSumOfCart(client);
        return sumOfCart;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }


}

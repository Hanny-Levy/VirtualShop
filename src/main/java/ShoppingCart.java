import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private HashMap<Product,Integer> products;
    private  double sumOfCart;

    public ShoppingCart() {
        this.products = new HashMap<Product, Integer>();
        this.sumOfCart=0;
    }

    public void print(){
        System.out.println("My shopping cart :");
        for(Product product : products.keySet()) {
            product.print();
        }

    }

    private void setSumOfCart(Employee employee) {
        this.sumOfCart=0;
        for(Product product : products.keySet()) {
            this.sumOfCart=(product.getPrice()*products.get(product));
        }
        System.out.println();
    }



    public double getSumOfCart(Employee employee) {
        this.setSumOfCart(employee);
        return sumOfCart;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }


}

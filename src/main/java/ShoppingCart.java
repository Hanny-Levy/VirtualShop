import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private HashMap<Product,Integer> products;
    private  double sumOfCart;

    public ShoppingCart() {
        this.products = new HashMap<Product, Integer>();
        //this.sumOfCart=0;
    }

    public void print(){
        int index=0;
        System.out.println("________________________________________");
        System.out.println("My shopping cart :");
        for(Product current : products.keySet()) {
            index++;
            System.out.println(index + "."+ current.getName() + " amount:"+products.get(current));
        }

    }

    public void setSumOfCart(Employee employee) {
        this.sumOfCart=0;double temp;
        ArrayList<Double> sum = new ArrayList<Double>();
        for(Product product : products.keySet()) {
            temp=(product.getPrice()*products.get(product));
            sum.add(temp);
        }
        for (Double currentSum: sum) {
            this.sumOfCart+=currentSum;
        }
        System.out.println();
    }



    public double getSumOfCart() {
        //this.setSumOfCart(employee);
        return sumOfCart;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }
    public void add(Product product , int amount){
        this.products.put(product,amount);
    }

}

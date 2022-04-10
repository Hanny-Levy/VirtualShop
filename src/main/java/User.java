import java.util.HashMap;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private HashMap<Product,Integer> shoppingCart;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<Product,Integer> getShoppingCart() {
        return shoppingCart;
    }
}

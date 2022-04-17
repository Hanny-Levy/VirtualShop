import java.util.HashMap;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private HashMap<Product, Integer> shoppingCart;

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.shoppingCart = new HashMap<Product, Integer>();
    }


    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

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


}
import java.util.ArrayList;
import java.util.HashMap;

public class Client extends User {
    private boolean isMember;
    private ArrayList<Order> orders;

    public Client(String firstName, String lastName, String username, String password, boolean isMember) {
        super(firstName, lastName, username, password);
        this.isMember = isMember;
        this.orders=new ArrayList<Order>();
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void print() {
        String client="Hello {"+this.getFirstName()+"} {"+this.getLastName()+"}";

        if (this.isMember){
            client+="(VIP)!";
        }else {
            client+=" !";
        }
        System.out.println(client);

    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void addOrder(Order order){
        if (order.getShoppingCart().getProducts().size()>0){
            this.orders.add(order);
            this.getShoppingCart().getProducts().clear();
        }

    }

    public double sumOfOrders() {
        double sum = 0;
        for (Order order : this.orders) {
            sum += order.getTotalPrice();
        }
        return sum;
    }

    public void printObject(){
        System.out.println(this.getFirstName() +" "+ this.getLastName() + '\n' +
        "Member : " + this.isMember+ '\n' +
        "Amount of purchases : "  +  this.orders.size() + '\n' );
        if(this.orders.size()>0){
            System.out.println("Total cost of all purchases : " + this.sumOfOrders() + '\n' +
                    "Last purchase date : " + this.orders.get(this.orders.size() - 1).getDateBuy());
        }
        System.out.println("__________________________________________________");
    }
}


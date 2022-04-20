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


}


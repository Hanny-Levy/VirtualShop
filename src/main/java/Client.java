import java.util.HashMap;

public class Client extends User {
    private boolean isMember;


    public Client(String firstName, String lastName, String username, String password, boolean isMember) {
        super(firstName, lastName, username, password);
        this.isMember = isMember;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void print() {
        System.out.print("Hello {"+this.getFirstName()+"} {"+this.getLastName()+"} !");
        if (this.isMember)
            System.out.println("(VIP)!");  ;
    }


}


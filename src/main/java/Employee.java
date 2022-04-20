import java.util.HashMap;
import java.util.Scanner;

public class Employee extends Client {

    private EmployeeRank rank;
    private double discountPercentage;

    public Employee(String firstName, String lastName, String username, String password, boolean isMember, int rank) {
        super(firstName, lastName, username, password, isMember);
        this.setRank(rank);
        this.setDiscountPercentage();
    }
    public Employee(Client client){
        super(client.getFirstName(), client.getLastName(), client.getUsername(), client.getPassword(), client.isMember());
        this.rank=EmployeeRank.CLIENT;
        this.discountPercentage = 1;
    }

    public void setDiscountPercentage() {
        switch (rank) {
            case REGULAR:
                this.discountPercentage = 0.9;
                break;
            case MANAGER:
                this.discountPercentage = 0.8;
                break;
            case MEMBER_OF_MANAGEMENT:
                this.discountPercentage = 0.7;
                break;

        }

    }

    public EmployeeRank getRank() {
        return rank;
    }

    public void setRank(int rank) {
        Scanner scannerInt = new Scanner(System.in);
        while (rank>3 || rank<1){
            System.out.println("Invalid rank try again");
            rank=scannerInt.nextInt();

    }
        this.rank=EmployeeRank.values()[rank];

   /*     switch (rank) {
            case 1:
                this.rank = EmployeeRank.REGULAR;
                break;
            case 2: this.rank=EmployeeRank.MANAGER;
            break;
            case 3: this.rank=EmployeeRank.MEMBER_OF_MANAGEMENT;
            break;
            default: this.rank=EmployeeRank.CLIENT;
    }*/
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public boolean equals(String username){
        return (this.getUsername().equals(username));
    }

    public Client getClient(){
        return new Client(this.getFirstName(),this.getLastName(),this.getUsername(),this.getPassword(),this.isMember());

    }


    public void print(){
        if (rank==EmployeeRank.CLIENT)
            this.getClient().print();
        else {
            System.out.println("Hello {" + this.getFirstName() + "} {" + this.getLastName() + "} ({" + this.rank + "})");
            this.optionMenu();
        }
    }

    public void optionMenu(){
        if (this.rank!=EmployeeRank.CLIENT)
        System.out.println("1 - Print a list of all customers. \n" +
                "2 - Print the list of customers who are members of the club only.\n"+
                "3 - Print the list of customers who have made at least one purchase.\n" +
                "4 - Print the customer whose purchase amount is the highest.\n" +
                "5 - Adding a new product to the store.\n" +
                "6 - Change inventory status for product.\n" +
                "7 - Making a purchase.\n" +
                "8 - Logout.\n");
    }



}




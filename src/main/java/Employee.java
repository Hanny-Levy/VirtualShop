public class Employee extends User {

    private int rank;
    private double discountPercentage;


    public Employee() {

    }

    public void setDiscountPercentage() {
        if (rank==Def.REGULAR)
        this.discountPercentage=0.1 ;
        if (rank==Def.MANAGER)
            this.discountPercentage=0.2 ;
        if (rank==Def.MEMBER_OF_MANAGEMENT)
            this.discountPercentage=0.3 ;
    }
}

public interface ShopInterface {

    public void createUser(UserType type);
    public Employee login(UserType userType);
    public void purchase(Employee employee);
    public void addNewProduct();
    public void setDiscountForMember(boolean isMember);

}

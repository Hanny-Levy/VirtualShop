public class Product {
    private String name ;
    private  int price ;
    private double discountPercentage;
    private boolean isInStock ;
    private int amount ;

    public Product(String name, int price, double discountPercentage, int amount) {
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.amount=amount;
        this.isInStock = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    public void print (){
       if(this.isInStock)
        System.out.println("{"+this.name + " price :" + this.price +" } {"+ " amount in stock : " + this.amount+"}");
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        if(this.amount==0)
            this.isInStock=false;
    }


}

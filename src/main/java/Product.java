public class Product {
    private String name ;
    private  double price ;
    private double discountPercentage;
    private boolean isInStock ;
    private int amount ;


    public Product(String name, double price, double discountPercentage, int amount) {
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.setAmount(amount);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPriceMember() {
        this.price=(this.price)*(1-this.discountPercentage);

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
        if(!this.isInStock){
            this.amount=0;
        }else if(this.amount==0)
            this.amount++;
    }

    public void print (){
        System.out.println("{"+this.name + " price :" + this.price +" } {"+ " amount in stock : " + this.amount+"}");
    }

    public void setFinalPrice(Client client){
        if(client.isMember()){
            this.price=this.price*discountPercentage;
        }
    }


        public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        if(this.amount==0)
            this.isInStock=false;
        else this.isInStock=true;
    }





}

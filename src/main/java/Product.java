public class Product {
    private String name ;
    private  double price ;
    private double discountPercentage;
    private boolean isInStock ;
    private int amount ;


    public Product(String name, double price, double discountPercentage, int amount) {
        this.name = name;
        this.price =price;
        this.roundPrice();
        this.discountPercentage = discountPercentage;
        this.setAmount(amount);

    }

    public void roundPrice(){
        this.price = Math.round(this.price*10.00)/10.00;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }



    public void setPriceMember() {
        this.price=(this.price)*(1-this.discountPercentage);
        this.roundPrice();
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
        System.out.println("{"+this.name + " price :" + this.price+"0" +" } {"+ " amount in stock : " + this.amount+"}");
    }



        public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.isInStock= this.amount != 0;
    }





}

import java.util.*;

public class Shop implements ShopInterface{
    ArrayList<Employee> clientsAndEmployees;
    ArrayList<Product> products;
    Scanner scanner = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    public Shop() {
        this.clientsAndEmployees = new ArrayList<Employee>();
        this.products = new ArrayList<Product>();

        this.products.add(new Product("milk", 11.7888882 , 0.3, 3));
        this.products.add(new Product("chocolate", 9.089991, 0.1, 5));
        this.products.add(new Product("Bread", 7.58, 0.2, 6));
        this.clientsAndEmployees.add(new Employee("Hanny","Levy","hanny","123456",false,3));
    }

    // Creating account for both options : employee / client .
    public void createAccount() {
        System.out.println("Are you interested in creating an account for an employee or client ? \n" +
                "enter 0 for client \n" +
                "enter 1 for employee");
        int type;
        try {
            type = scannerInt.nextInt();
            UserType userType = UserType.values()[type];
            switch (userType) {
                case CLIENT: {
                    createUser(UserType.CLIENT);
                    break;
                }
                case EMPLOYEE: {
                    createUser(UserType.EMPLOYEE);
                    break;
                }
                default:
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid choice try again");
        } catch (InputMismatchException exception) {
            System.out.println("Invalid choice try again");
        }
    }

    // scanning and checking valid details for creating account
    public void createUser(UserType type) {
        int usernameTaken;
        String username = null , firstName = null, lastName = null , password = null;
        System.out.println("Enter your first name:");
        firstName = scanner.nextLine();
        while (!checkValidName(firstName)) {
            System.out.println("Invalid name , try again.");
            firstName = scanner.nextLine();
        }

        System.out.println("Enter your last name:");
        lastName = scanner.nextLine();
        while (!checkValidName(lastName)) {
            System.out.println("Invalid name , try again.");
            lastName = scanner.nextLine();
        }
        do {
            System.out.println("Enter username:");
            username = scanner.nextLine();
            usernameTaken=this.doesUsernameExist(username);
        }
        while (usernameTaken!=-1);
        boolean strongPassword = false;
        do {
            System.out.println("Enter a strong password: ");
            password = scanner.nextLine();
            strongPassword = this.checkIfStrongPassword(password);
        } while (!strongPassword);
        char answer ;boolean member = false; int correctAnswer=0;
        do{
            System.out.println("Are you a member ?\n write Y for yes \n and N for no ");
            answer = scanner.nextLine().charAt(0);
            switch (answer) {
                case 'y':
                case 'Y': {
                    member = true;
                    correctAnswer++;
                    break;
                }
                case 'n':
                case 'N': {
                    correctAnswer++;
                    member = false;
                    break;
                }
            }

        }
        while (correctAnswer==0);





        if (type == UserType.CLIENT) {
            Client newClient = new Client(firstName, lastName, username, password, member);
            this.clientsAndEmployees.add(new Employee(newClient));
        } else {
            System.out.println("Write your rank : \n" +
                    "1 - " + EmployeeRank.REGULAR + '\n' +
                    "2 - " + EmployeeRank.MANAGER + '\n' +
                    "3 - " + EmployeeRank.MEMBER_OF_MANAGEMENT + '\n');
            int rank = scannerInt.nextInt();
            Employee newEmployee = new Employee(firstName, lastName, username, password, member, rank);
            this.clientsAndEmployees.add(newEmployee);
        }

        System.out.println("User was added!");
    }

    public void setMember(){

    }

    public void loginMenu() {
        Employee current = null;
        System.out.println("would you like to login to an employee account or client? \n" +
                "0 - for client \n" +
                "1 - for employee");
            try{
            int type = scannerInt.nextInt();
            UserType userType = UserType.values()[type];
            current=login(userType);
            if (current == null) {
                System.out.println("No such username ,please create account.");
            }
            else {
                switch (userType) {
                    case CLIENT: {
                        this.purchase(current);
                        break;
                    }
                    case EMPLOYEE: {
                        int choice;
                        do {
                            current.optionMenu();
                            choice = scannerInt.nextInt();
                            switch (choice) {
                                case Def.PRINT_ALL_CLIENTS: {
                                    this.printAllClients();
                                    break;
                                }
                                case Def.PRINT_ALL_CLUB_MEMBER: {
                                    this.printAllMembers();
                                    break;
                                }
                                case Def.PRINT_CLIENT_WITH_AT_LEAST_ONE_ORDER: {
                                    this.printClientsWithAtLeastOneOrder();
                                    break;
                                }
                                case Def.PRINT_CLIENT_WITH_THE_HIGHEST_SUM_ORDERS: {
                                    this.printClientWithHighPurchaseAmount();
                                    break;
                                }
                                case Def.ADDING_NEW_PRODUCT: {
                                    this.addNewProduct();
                                    break;
                                }
                                case Def.CHANGING_PRODUCT_STATUS: {
                                    this.setProductIsInStock();
                                    break;
                                }
                                case Def.MAKING_ORDER: {
                                    this.purchase(current);
                                    break;
                                }
                            }
                        }
                        while (choice != Def.EMPLOYEE_LOGOUT);
                    }
                }
            }
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Invalid choice try again");
            }catch (InputMismatchException exception){

            }


    }

    public Employee login(UserType userType) {
        int found = -1;Employee current=null;String password;
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        int index=doesUsernameExist(username);
        if (index!=-1) {
            current = this.clientsAndEmployees.get(index);
            if ((userType == UserType.EMPLOYEE) && (current.getRank() == EmployeeRank.CLIENT)) {
                System.out.println("You are not an employee! Please connect through a client login");
                return null;
            }
                System.out.println("Enter your password");
                password = scanner.nextLine();
                while (!current.getPassword().equals(password)) {
                    System.out.println("Invalid password try again!");
                    password = scanner.nextLine();
                }


            if (userType == UserType.CLIENT)
                current.getClient().print();
            else current.print();

        }
        return current;
    }






    private boolean checkValidName(String name){
        for (int index=0 ;index<name.length(); index++){
            if (name.charAt(index)>47 && name.charAt(index)<58)
                return false;
        }
        return true;
    }

    private boolean checkIfStrongPassword(String password){
        boolean strong = false;
        if (password.length()>=6){
            strong = true;
        }
        return strong;
    }

    private int doesUsernameExist(String usernameToCheck){
        int exist=-1 , index=-1;
        for (Client currentUser : this.clientsAndEmployees){
            index++;
            if (currentUser.getUsername().equals(usernameToCheck)){
                exist=index;
                break;
            }
        }
        return exist;
    }

    public ArrayList<Employee> getClientsAndEmployees() {
        return clientsAndEmployees;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


    public void printAllClients(){
        System.out.println("The clients are:");
        int index=1;
        for (Client current: this.clientsAndEmployees ) {
                System.out.print(index+". " );
                current.printObject();
                index++;
        }
    }

    public void printAllMembers(){
        System.out.println("The clients that have a membership:");
        int index=1;
        for (Client current: this.clientsAndEmployees ) {
            if((current.isMember())){
                System.out.print(index +". " );
                current.printObject();
                index++;
            }
        }
        if (index==1){
            System.out.println("No club members yet .");
        }

    }

    public void printProductsInStock(){
        int index=-1;
        for(Product current : this.products) {
            index++;
            if (current.isInStock()){
                System.out.print(index+". ");
                current.print();
            }
        }
    }

    public void purchase(Employee employee) {
        int numberOfProduct,amount;
        double sum = 0;
        Order order = new Order(employee, employee.getShoppingCart());
        this.setDiscountForMember(employee.isMember());
        System.out.println("choose the number of product you want :");
        this.printProductsInStock();
        numberOfProduct = scannerInt.nextInt();
        while (numberOfProduct!=Def.FINISH_ORDER) {
            Product product = this.products.get(numberOfProduct);
            if ((product.isInStock())) {
                if (numberOfProduct < this.products.size()) {
                System.out.println("How many of " + product.getName() + " do you want?");
                amount = scannerInt.nextInt();
                while (amount < 0 || amount > product.getAmount()) {
                    System.out.println("Invalid amount try again.");
                    amount = scannerInt.nextInt();
                }
                employee.getShoppingCart().add(product, amount);
                employee.getShoppingCart().setSumOfCart(employee);
                sum = (employee.getShoppingCart().getSumOfCart());
                order.print();
                System.out.println("The total price of this cart is : " + sum + "0");
                System.out.println("________________________________________");
                product.setAmount(product.getAmount() - amount);
                }
            }
            this.printProductsInStock();
            System.out.println("choose the number of product you want :");
            numberOfProduct = scannerInt.nextInt();
        }

        order.setTotalPrice(sum);
        System.out.println("The total price of this cart is : "+ sum+"0");
        if(employee.getRank()!=EmployeeRank.CLIENT) {
            order.setTotalPrice(this.round(order.getTotalPrice() * employee.getDiscountPercentage()));
            System.out.println("The total price after employee discount : " + order.getTotalPrice());
        }

        employee.addOrder(order);
    }

    public void printClientsWithAtLeastOneOrder(){
        int index=1;
        System.out.println("The clients who made at least one order:");
        for (Client current:this.clientsAndEmployees) {
            if (current.getOrders().size()>=1){
                System.out.print(index+". ");
                current.printObject();
                index++;
            }
        }
    }

    public void addNewProduct(){
        System.out.println("Write the description of the product: ");
        String name=scanner.nextLine();
        System.out.println("Price of the product: ");
        double price = scannerInt.nextDouble();
        System.out.println("Discount percentage for club members : ");
        double discountPercentage=scannerInt.nextDouble();
        while (discountPercentage>1){
            System.out.println("Invalid discount percentage , try again.");
            discountPercentage=scannerInt.nextDouble();
        }
        System.out.println("How much to add this product to stock?");
        int amount=scannerInt.nextInt();
        while (amount<=0){
            System.out.println("Invalid amount, try again.");
            amount=scannerInt.nextInt();
        }
        this.products.add(new Product(name,price,discountPercentage,amount));
        System.out.println(name + " has been added! \n");
    }

    public void printProducts(){
        System.out.println("All the products in the shop: ");
        int index=-1;
        for(Product current : this.products) {
            index++;
                System.out.print(index+" ");
                current.print();
            }
        System.out.println();
    }

    public void setProductIsInStock(){
        this.printProducts();
        System.out.println("Enter the number of the product to change its' status :");
        int answer=scannerInt.nextInt();
        while (answer>this.products.size() || answer<0){
           System.out.println("Invalid choice try again!");
           answer=scannerInt.nextInt();
       }
        System.out.println("Would you like to update that this product is in stock or is it out of stock? \n" +
                "Enter T - for is in stock \n" +
                "and F - for out of stock ");
        char isInStock=scanner.nextLine().charAt(0);
        switch (isInStock){
            case 'T': case 't':{
                this.products.get(answer).setInStock(true);
                System.out.println("Changes saved , "+this.products.get(answer).getName()+" is now exists in stock! \n");

                break;
            }
            case 'F': case 'f':{
                this.products.get(answer).setInStock(false);
                System.out.println("Changes saved , "+this.products.get(answer).getName()+" is no longer exists in stock! \n");
                break;
            }
            default:{
                System.out.println("Invalid answer try again.");
                break;
            }
        }

    }

    public void setDiscountForMember(boolean isMember){
        if (isMember){
            for (Product current: this.products) {
                current.setPriceMember();
            }
        }
    }

    public void printClientWithHighPurchaseAmount(){
        Client client=this.clientsAndEmployees.get(0);
        for (Client current:this.clientsAndEmployees) {
            if (current.sumOfOrders()>client.sumOfOrders())
                client=current;
        }
        System.out.println("The client with the Highest purchase amount :");
        client.printObject();
    }

    private double round(double price){
        return ((double)(Math.round(price)*10.0)/10.0);
    }
}

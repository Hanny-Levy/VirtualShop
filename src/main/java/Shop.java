import java.util.*;

public class Shop {
    ArrayList<Employee> clientsAndEmployees;
    ArrayList<Product> products;
    Scanner scanner = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    public Shop() {
        this.clientsAndEmployees = new ArrayList<Employee>();
        this.products = new ArrayList<Product>();

        this.products.add(new Product("milk", 11.0 , 0.3, 3));
        this.products.add(new Product("chocolate", 9.0, 0.1, 5));
        this.products.add(new Product("Bread", 7.0, 0.2, 6));
    }

    // Creating account for both options : employee / client .
    public void createAccount() {
        System.out.println("Are you interested in creating an account for an employee or customer ? \n" +
                "enter 0 for customer \n" +
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
        boolean usernameTaken = false;
        String username = null;
        String firstName = null;
        String lastName = null;
        String password = null;
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
            usernameTaken = this.doesUsernameExist(username);
        }
        while (usernameTaken);
        boolean strongPassword = false;
        do {
            System.out.println("Enter a strong password: ");
            password = scanner.nextLine();
            strongPassword = this.checkIfStrongPassword(password);
        } while (!strongPassword);
        System.out.println("Are you a member ?\n write Y for yes \n and N for no ");
        char answer = scanner.nextLine().charAt(0);
        boolean member = false;
        switch (answer) {
            case 'y':
            case 'Y': {
                member = true;
                break;
            }
            case 'n':
            case 'N': {
                member = false;
                break;
            }
            default: {
                System.out.println("Invalid answer ! try again");
                answer = scanner.nextLine().charAt(0);
                break;
            }
        }

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


    public void loginMenu() {
        Employee current = null;
        System.out.println("would you like to login to an employee account or customer? \n" +
                "0 - for customer \n" +
                "1 - for employee");
        try {
            int type = scannerInt.nextInt();
            UserType userType = UserType.values()[type];
            int userIndex = login(userType);
            if (userIndex == -1)
                System.out.println("No such username ,please create account.");
            else {
                current = this.clientsAndEmployees.get(userIndex);
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
                                    this.printAllClientsMembers();
                                    break;
                                }
                                case Def.PRINT_CLIENT_WITH_AT_LEAST_ONE_ORDER:{
                                    this.printClientsWithAtLeastOneOrder();
                                    break;
                                }
                                case Def.PRINT_CLIENT_WITH_THE_HIGHEST_SUM_ORDERS:{
                                    break;
                                }
                                case Def.ADDING_NEW_PRODUCT:{
                                    this.addNewProduct();
                                    break;
                                }
                                case Def.CHANGING_PRODUCT_STATUS:{
                                    this.setProductIsInStock();
                                    break;
                                }
                                case Def.MAKING_ORDER:{
                                    Order order=this.purchase(current);
                                    order.setTotalPrice((int) (order.getTotalPrice()* current.getDiscountPercentage()));
                                    System.out.println("The total price after employee discount : "+order.getTotalPrice());
                                }
                            }
                        }
                        while (choice!=Def.EMPLOYEE_LOGOUT);
                        break;
                    }
                        }


            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid choice try again!");
        }
    }

    public int login(UserType userType) {
        int found = -1;
        int index = -1;
        System.out.println("Enter your username");
        String username = scanner.nextLine();

        for (Employee current : this.clientsAndEmployees) {
            index++;
            if (current.equals(username)) {
                {
                    if ((userType==UserType.EMPLOYEE)&&(current.getRank()==EmployeeRank.CLIENT)){
                        System.out.println("You are not an employee! Please connect through a client login");
                        break;
                    }

                    System.out.println("Enter your password");
                    String password = scanner.nextLine();
                    while (!current.getPassword().equals(password)) {
                        System.out.println("Invalid password try again!");
                        password = scanner.nextLine();
                    }
                    found = index;


                    if (userType == UserType.CLIENT)
                        current.getClient().print();
                    break;
                }

            }

        }
        return found;
    }






    public boolean checkValidName(String name){
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

    private boolean doesUsernameExist(String usernameToCheck){
        boolean exist = false;
        for (Client currentUser : this.clientsAndEmployees){
            if (currentUser.getUsername().equals(usernameToCheck)){
                exist = true;
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
        int index=1;
        for (Employee current: this.clientsAndEmployees ) {
                System.out.println(index+" " +current.getFirstName()+ " " + current.getLastName());
                index++;
        }
    }

    public void printAllClientsMembers(){
        int index=1;
        for (Employee current:
                this.clientsAndEmployees ) {
            if((current.isMember())){
                System.out.println(index +" " +current.getFirstName()+ " " + current.getLastName());
                index++;
            }
        }

    }

    public void printProductsInStock(){
        int index=-1;
        for(Product current : this.products) {
            index++;
            if (current.isInStock()){
                System.out.print(index+" ");
                current.print();
            }
        }
    }

    public Order purchase(Employee employee){
        int numberOfProduct;int amount;int sum=0;Order order=null;
            this.printProductsInStock();
            System.out.println("choose the number of product you want :");
            numberOfProduct=scannerInt.nextInt();

        while (numberOfProduct!=Def.FINISH_ORDER){
            Product product=this.products.get(numberOfProduct);
            if (numberOfProduct<this.products.size()){
                System.out.println("How many of "+product.getName() + " do you want?");
                amount=scannerInt.nextInt();
                while (amount<0 || amount>product.getAmount()) {
                    System.out.println("Invalid amount try again.");
                    amount = scannerInt.nextInt();
                }

                employee.getShoppingCart().getProducts().put(product,amount);
                sum+=employee.getShoppingCart().getSumOfCart(employee);
                order=new Order(employee, employee.getShoppingCart());
                order.setTotalPrice(sum+order.getTotalPrice());

                System.out.println("The total price of this cart is : "+ order.getTotalPrice());
                employee.getShoppingCart().print();
                product.setAmount(product.getAmount()-amount);

                this.printProductsInStock();
                System.out.println("choose the number of product you want :");
                numberOfProduct=scannerInt.nextInt();

            }

        }

        System.out.println("The total price of this cart is : "+ order.getTotalPrice());
        employee.getOrders().add(order);
        return order;
    }

    public void printClientsWithAtLeastOneOrder(){
        System.out.println("The client who made at least one order:");
        for (Employee current:this.clientsAndEmployees) {
            if (current.getOrders().size()>=1)
                current.print();
        }
    }

    public void addNewProduct(){
        System.out.println("Write the description of the product: ");
        String name=scanner.nextLine();
        System.out.println("Price of the product: ");
        int price = scannerInt.nextInt();
        System.out.println("Discount percentage for club members : ");
        double discountPercentage=scannerInt.nextDouble();
        System.out.println("How much to add this product to stock?");
        int amount=scannerInt.nextInt();
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
}

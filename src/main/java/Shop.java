import java.util.*;

public class Shop {
    ArrayList<Employee> clientsAndEmployees;
    ArrayList<Product> products;
    Scanner scanner = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    public Shop() {
        this.clientsAndEmployees = new ArrayList<Employee>();
        this.products = new ArrayList<Product>();

        this.products.add(new Product("milk",11,0.3,3));
        this.products.add(new Product("chocolate",9,0.1,5));
        this.products.add(new Product("Bread",7,0.2,6));
    }

    public void loginMenu() {
        Employee current=null;
        System.out.println("would you like to login to an employee account or customer? \n"+
                "0 - for customer \n" +
                "1 - for employee");
        int type = scannerInt.nextInt();
        try {
            UserType userType=UserType.values()[type];
            int userIndex=login(userType);
            if (userIndex==-1)
                System.out.println("No such username ,please create account.");
            else {
                current = this.clientsAndEmployees.get(userIndex);
                switch (userType) {
                    case CLIENT: {

                        this.purchase(current,0);
                        break;
                    }
                    case EMPLOYEE: {
                        int choice = scannerInt.nextInt();
                        switch (choice) {
                            case Def.PRINT_ALL_CLIENTS: {
                                this.printAllClients();
                                break;
                            }
                            case Def.PRINT_ALL_CLUB_MEMBER: {
                                this.printAllClientsMembers();
                                break;
                            }

                        }
                        break;

                    }
                    default:
                        break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid choice try again");
            type = scannerInt.nextInt();
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
                   System.out.println("Enter your password");
                   String password = scanner.nextLine();
                   while (!current.getPassword().equals(password)) {
                       System.out.println("Invalid password try again!");
                       password = scanner.nextLine();
                   }
                   found = index;


                   if (userType == UserType.CLIENT)
                       current.getClient().print();
                   else current.print();
                   break;
               }

           }

       }
       return found;
   }

        public void createAccount()   {
            System.out.println("Are you interested in creating an account for an employee or customer ? \n" +
                    "enter 0 for customer \n" +
                    "enter 1 for employee");

            int type;
            try {
                 type= scannerInt.nextInt();
                UserType userType=UserType.values()[type];
                switch (userType) {
                    case CLIENT: {
                        createUser(UserType.CLIENT);
                        break;
                    }
                    case EMPLOYEE: {
                        createUser(UserType.EMPLOYEE);
                        break;
                    }
                    default: break;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid choice try again");
                type = scannerInt.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("Invalid choice try again");
                type = scannerInt.nextInt();
            }
        }

        public void createUser (UserType type) {
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
            usernameTaken = this.doesUserNameExist(username);
        }
        while (usernameTaken) ;
        boolean strongPassword = false;
        do {
            System.out.println("Enter a strong password: ");
            password = scanner.nextLine();
            strongPassword = this.checkIfStrongPassword(password);
        } while (!strongPassword);
            System.out.println("Are you a member ?\n write Y for yes \n and N for no ");
            char answer = scanner.nextLine().charAt(0);
            boolean member=false;
            if (answer=='y' || answer=='Y') {
                member = true;
            } else {
                System.out.println("Invalid answer ! try again");
                answer = scanner.nextLine().charAt(0);
            }
            if (type==UserType.CLIENT) {
                Client newClient = new Client(firstName, lastName, username, password, member);
                this.clientsAndEmployees.add(new Employee(newClient));
            }
            else if (type==UserType.EMPLOYEE){
                System.out.println("Write your rank : \n" +
                        "1 - " + EmployeeRank.REGULAR+ '\n' +
                        "2 - " + EmployeeRank.MANAGER+ '\n' +
                        "3 - " + EmployeeRank.MEMBER_OF_MANAGEMENT+ '\n' );
                int rank=scannerInt.nextInt();
                Employee newEmployee= new Employee(firstName, lastName, username, password, member,rank);
                this.clientsAndEmployees.add(newEmployee);
            }

        System.out.println("User was added!");
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

    private boolean doesUserNameExist(String usernameToCheck){
        boolean exits = false;
        for (Client currentUser : this.clientsAndEmployees){
            if (currentUser.getUsername().equals(usernameToCheck)){
                exits = true;
                break;
            }
        }
        return exits;
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

    public void printProducts(){
        int index=-1;
        for(Product current : this.products) {
            index++;
            if (current.isInStock()){
                System.out.print(index+" ");
                current.print();
            }
        }
    }

    public void purchase(Employee employee,int sum){
        int numberOfProduct;int amount;
            this.printProducts();
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
                sum=employee.getShoppingCart().getSumOfCart(employee);
            //    employee.getShoppingCart().print();
                product.setAmount(product.getAmount()-amount);

                System.out.println("---------------------------------------------");
                this.printProducts();
                System.out.println("choose the number of product you want :");
                numberOfProduct=scannerInt.nextInt();
                //System.out.println("The total price of this cart is : "+ sum);
                //    this.purchase(employee,sum);
            }

        }


        Order order=new Order(employee, employee.getShoppingCart());

    }



}

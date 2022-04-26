import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main=new Main();
        main.userMenu();


    }


    private Shop virtualShop;
    public Main(){
        this.virtualShop = new Shop();

    }
    private void userMenu() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;

        try{
            do {
                System.out.println("Welcome to our virtual store of Hanny and Ofir :)" + '\n' +
                        "1 - Create new account\n" +
                        "2 - Login to an existing account\n" +
                        "3 - Exit\n");
                userChoice = scanner.nextInt();
                switch (userChoice) {
                    case Def.NEW_ACCOUNT: {
                        this.virtualShop.createAccount();
                        break;
                    }

                    case Def.LOGIN: {
                        this.virtualShop.loginMenu();
                        break;
                    }

                }

            }while (userChoice!=Def.EXIT);
        }
        catch (InputMismatchException exception) {
            System.out.println("Invalid choice try again");
            this.userMenu();
        }



    }

}

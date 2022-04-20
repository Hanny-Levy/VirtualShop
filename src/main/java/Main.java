import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main=new Main();
                while (true){
                    main.userMenu();
                }


        }


    private Shop virtualShop;
    public Main(){
        this.virtualShop = new Shop();

    }
    private void userMenu() {
        Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to our virtual store of Hanny and Ofir :)" + '\n' +
                    "1 - Create new account\n" +
                    "2 - Login to an existing account\n" +
                    "3 - Exit\n");
            try{
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case Def.NEW_ACCOUNT: {
                        this.virtualShop.createAccount();
                        break;
                    }

                    case Def.LOGIN: {
                        this.virtualShop.loginMenu();
                        break;
                    }

                    case Def.LOGOUT:
                        break;

                }
            }
         catch (InputMismatchException exception) {
                System.out.println("Invalid choice try again");
            }


        }

    }



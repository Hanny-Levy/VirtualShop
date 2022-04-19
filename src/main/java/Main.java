import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        new Main().userMenu();



    }

    private void userMenu(){
        Scanner scanner= new Scanner(System.in);
        Shop shop=new Shop();


        while (true) {
            System.out.println("Welcome to our virtual store of Hanny and Ofir :)" +'\n'+
                    "1 - Create new account\n"+
                    "2 - Login to an existing account\n" +
                    "3 - Exit\n");
            int userChoice;
                userChoice = scanner.nextInt();
                switch (userChoice) {
                    case Def.NEW_ACCOUNT: {
                        shop.createAccount();
                        break;
                    }

                    case Def.LOGIN:{
                       shop.loginMenu();
                        break;
                    }

                    case Def.LOGOUT:
                        break;

                }

            }


    }
}

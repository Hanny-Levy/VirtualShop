import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);



    }

    private void userMenu(){
        Scanner scanner= new Scanner(System.in);

        System.out.println("Welcome to our virtual store of Hanny and Ofir :)" +'\n'+
                " 1 - Create new account\n"+
                "2 - Login to an existing account\n" +
                "3 - Exit\n");

        while (true) {
            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case Def.NEW_ACCOUNT:
                    System.out.println(" C - creating account for client \n"+
                            "E - creating account for employee");
                    char type= scanner.nextLine().charAt(0);
                    switch (type){
                        case 'E': case 'e':
                            User employee = new Employee();
                            break;
                        case 'C': case 'c':
                            User client = new Client();
                            break;
                    }
                    break;
                case Def.LOGIN:
                    break;
                case Def.LOGOUT:
                    break;
                default:break;
            }
        }

    }
}

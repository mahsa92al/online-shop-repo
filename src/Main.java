import service.CustomerService;

import java.util.Scanner;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService;

    public static void main(String[] args) {
        customerService = new CustomerService();
        boolean error = true;

        System.out.println("***Welcome to online shop***");
        System.out.println("1. Create Account");
        do{
            try {
                String name = getNameFromCustomer();
                error = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (error);
        do{
            try {
                String email = getEmailFromCustomer();
                error = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());;
            }
        }while (error);

        String password = getPasswordFromCustomer();
        getPhoneFromCustomer();
        getAddressFromCustomer();

    }
    private static String getNameFromCustomer() throws Exception {
        System.out.println("Full name: ## like Mahsa Alikhani ##");
        String name = scanner.nextLine();
        if(!name.matches("^[a-zA-Z\\s]*$")){
            throw new Exception("You did not enter letters! please enter only letter.");
        }
        return name;
    }

    private static String getEmailFromCustomer() throws Exception {
        System.out.println("Email:");
        String email = scanner.next();
        if(!email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
            throw new Exception("You did not enter valid email!");
        }
        return email;
    }

    private static String getPasswordFromCustomer(){
        System.out.println("Password:");
        String password = scanner.next();
        return password;
    }
    private static void getPhoneFromCustomer(){}
    private static void getAddressFromCustomer(){}

}

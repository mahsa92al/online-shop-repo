import model.Address;
import model.Customer;
import model.Product;
import service.CustomerService;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService;
    private static ProductService productService;
    private static Customer customer;
    private static Address address;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        customerService = new CustomerService();

        boolean error = true;

        System.out.println("***Welcome to online shop***");
        System.out.println("1. Create Account");
        String choice;
        do{
            choice = scanner.next();
        }while (!choice.matches("[1-9]+"));
        int choiceNumber = Integer.parseInt(choice);
        switch (choiceNumber){
            case 1:
                String name = null;
                do{
                    try {
                        name = getNameFromCustomer();
                        error = false;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }while (error);
                String email = null;
                do{
                    try {
                        email = getEmailFromCustomer();
                        error = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());;
                    }
                }while (error);

                String password = getPasswordFromCustomer();
                String phone = null;
                do{
                    try {
                        phone = getPhoneFromCustomer();
                        error = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }while (error);
                Address address = null;
                do{
                    try {
                        address = getAddressFromCustomer();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());;
                    }
                }while (error);
                customer = new Customer(name, email, password, phone, address);
                customerService.addNewCustomer(address, customer);
                showProducts(productService.getAllProducts());
                break;
            default:
                System.out.println("Invalid value!");
        }

    }
    private static void showProducts(List<Product> products){
        for (Product product : products) {
            System.out.println(product);
        }
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
    private static String getPhoneFromCustomer() throws Exception {
        System.out.println("Phone");
        String phone = scanner.next();
        if(!phone.matches("^(\\+98|0)?9\\d{9}$")){
            throw new Exception("You did not enter valid phone number!");
        }
        return phone;
    }
    private static Address getAddressFromCustomer() throws Exception {
        String country = getCountry();
        String state = getState();
        String city = getCity();
        String postalCode = getPostalCode();
        address = new Address(country, state, city, postalCode);
        return address;
    }
    private static String getCountry() throws Exception {
        System.out.println("Country:");
        String country = scanner.next();
        if(!country.matches("^[a-zA-Z]*$")){
            throw new Exception("You did not enter letters! please enter only letter.");
        }
        return country;
    }
    private static String getState() throws Exception {
        System.out.println("State:");
        String state = scanner.next();
        if(!state.matches("^[a-zA-Z]*$")){
            throw new Exception("You did not enter letters! please enter only letter.");
        }
        return state;
    }
    private static String getCity() throws Exception {
        System.out.println("City:");
        String city = scanner.next();
        if(!city.matches("^[a-zA-Z]*$")){
            throw new Exception("You did not enter letters! please enter only letter.");
        }
        return city;
    }
    private static String getPostalCode() throws Exception {
        System.out.println("Postal code:");
        String postalCode = scanner.next();
        if(!postalCode.matches("[1-9]+")){
            throw new Exception("You did not enter numbers! please enter only numbers.");
        }
        return postalCode;
    }



}

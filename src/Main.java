import model.Address;
import model.Customer;
import model.Order;
import model.Product;
import service.CustomerService;
import service.OrderService;
import service.ProductService;

import java.sql.SQLException;
import java.util.*;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService;
    private static ProductService productService;
    private static OrderService orderService;
    private static Customer customer;
    private static Address address;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        customerService = new CustomerService();
        int choiceNumber;
        do {
            System.out.println("***Welcome to online shop***");
            System.out.println("1. Create Account");
            System.out.println("2. Exit");
            String choice;
            do {
                choice = scanner.next();
            } while (!choice.matches("[1-9]+"));
            choiceNumber = Integer.parseInt(choice);
            switch (choiceNumber) {
                case 1:
                    int customerId = createAccountByCustomer();
                    do{
                        showProducts(productService.getAllProducts());
                        System.out.println("----------------------------------------------");
                        System.out.println("1. Add to shopping bag\n2. Exit");
                        do {
                            choice = scanner.next();
                        } while (!choice.matches("[1-9]+"));
                        choiceNumber = Integer.parseInt(choice);
                        switch (choiceNumber){
                            case 1:
                                try {
                                    addToShoppingBag(customerId);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("Invalid value!");
                        }
                    }while (choiceNumber != 2);
                    break;
                case 2:
                    System.out.println("***Good Bye***");
                    break;
                default:
                    System.out.println("Invalid value!");
            }
        } while (choiceNumber != 2);
    }

    private static void removeAnOrderByCustomer(int customerId) throws SQLException {
        List<Order> orderList = orderService.getOrderList(customerId);
        System.out.println(orderList);
        Map<Integer, Integer> counterMap = new HashMap<>();
        for (Order item: orderList) {
            counterMap.put(item.getId(), item.getCounter());
        }
        System.out.println("which order do you want to remove from bags?\nEnter order Id:");
        String orderId;
        do {
            orderId = scanner.next();
        } while (!orderId.matches("[1-9]+"));
        int orderIdNumber = Integer.parseInt(orderId);
        boolean remove = false;
        try {
            remove = orderService.removeOrderFromBag(orderIdNumber);
            int deletedOrderCounter = counterMap.get(orderIdNumber);
            for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()){
                if(entry.getValue() > deletedOrderCounter){
                    int newCounter = entry.getValue() - 1;
                    orderService.decreaseOrderCounter(entry.getKey(), newCounter);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (remove) {
            System.out.println("The order was removed successfully.");
        } else {
            System.out.println("The order was not removed.");
        }
    }

    private static int createAccountByCustomer() throws SQLException {
        boolean error = true;
        String name = null;
        do {
            try {
                name = getNameFromCustomer();
                error = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (error);
        String email = null;
        do {
            try {
                email = getEmailFromCustomer();
                error = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        String password = getPasswordFromCustomer();
        String phone = null;
        do {
            try {
                phone = getPhoneFromCustomer();
                error = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (error);
        Address address = null;
        do {
            try {
                address = getAddressFromCustomer();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ;
            }
        } while (error);
        customer = new Customer(name, email, password, phone, address);
        int customerId = customerService.addNewCustomer(address, customer);
        return customerId;
    }

    private static void addToShoppingBag(int customerId) throws Exception {
        System.out.println("Enter product ID:");
        String productId = scanner.next();
        if (!productId.matches("[1-9]+")) {
            throw new Exception("You did not enter numbers! please enter only numbers.");
        }
        System.out.println("Enter quantity:");
        String quantity = scanner.next();
        if (!quantity.matches("[1-9]+")) {
            throw new Exception("You did not enter numbers! please enter only numbers.");
        }
        System.out.println("Enter date: like 1400-07-25");
        String date = scanner.next();
        if (!date.matches("[0-9]{4}(-)[0-9]{1,2}(-)[0-9]{1,2}")) {
            throw new Exception("You did not enter valid date.");
        }
        try {
            orderService.addNewOrderToBag(Integer.parseInt(productId), Integer.parseInt(quantity),
                    java.sql.Date.valueOf(date), customerId);
        } catch (Exception e) {
            int choiceNumber;
            do{
                System.out.println("1. remove an item\n 2. Exit");
                String choice;
                do {
                    choice = scanner.next();
                } while (!choice.matches("[1-9]+"));
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber) {
                    case 1:
                        removeAnOrderByCustomer(customerId);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid value!");
                }
            }while(choiceNumber != 2);
            do{
                System.out.println("1. Confirm\n2. Remove");
                String choice;
                do {
                    choice = scanner.next();
                } while (!choice.matches("[1-9]+"));
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber){
                    case 1:
                        List<Order> ordersList = orderService.getOrderList(customerId);
                        System.out.println(ordersList);
                        System.out.println(orderService.sumOfOrderPrices(customerId));
                        orderService.confirmOrdersByCustomer(ordersList);
                        break;
                    case 2:
                        removeAnOrderByCustomer(customerId);
                        break;
                    default:
                        System.out.println("Invalid value!");
                }
            }while (choiceNumber != 1 || choiceNumber != 2);
        }
    }

    private static void showProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static String getNameFromCustomer() throws Exception {
        System.out.println("Full name (like Mahsa Alikhani):");
        String name = scanner.nextLine();
        scanner.nextLine();

        if (!name.matches("^[a-zA-Z\\s]*$")) {
            throw new Exception("You did not enter letters! please enter only letter.");
        }
        return name;
    }

    private static String getEmailFromCustomer() throws Exception {
        System.out.println("Email:");
        String email = scanner.next();
        if (!email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            throw new Exception("You did not enter valid email!");
        }
        return email;
    }

    private static String getPasswordFromCustomer() {
        System.out.println("Password:");
        String password = scanner.next();
        return password;
    }

    private static String getPhoneFromCustomer() throws Exception {
        System.out.println("Phone");
        String phone = scanner.next();
        if (!phone.matches("^(\\+98|0)?9\\d{9}$")) {
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
        if (!country.matches("^[a-zA-Z]*$")) {
            throw new Exception("You did not enter letters! please enter only letter.");
        }
        return country;
    }

    private static String getState() throws Exception {
        System.out.println("State:");
        String state = scanner.next();
        if (!state.matches("^[a-zA-Z]*$")) {
            throw new Exception("You did not enter letters! please enter only letter.");
        }
        return state;
    }

    private static String getCity() throws Exception {
        System.out.println("City:");
        String city = scanner.next();
        if (!city.matches("^[a-zA-Z]*$")) {
            throw new Exception("You did not enter letters! please enter only letter.");
        }
        return city;
    }

    private static String getPostalCode() throws Exception {
        System.out.println("Postal code:");
        String postalCode = scanner.next();
        if (!postalCode.matches("[1-9]+")) {
            throw new Exception("You did not enter numbers! please enter only numbers.");
        }
        return postalCode;
    }


}

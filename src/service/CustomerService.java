package service;

import model.Address;
import model.Customer;
import repository.AddressDao;
import repository.CustomerDao;

import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class CustomerService {
    private final CustomerDao customerDao;
    private final AddressDao addressDao;

    public CustomerService() throws SQLException, ClassNotFoundException {
        customerDao = new CustomerDao();
        addressDao = new AddressDao();
    }

    public int addNewCustomer(Address address, Customer customer) throws SQLException {
    int addressId = addressDao.saveNewAddress(address);
    int customerId = customerDao.saveNewCustomer(customer, addressId);
    return customerId;
    }
}

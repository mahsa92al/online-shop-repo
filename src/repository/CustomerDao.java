package repository;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class CustomerDao extends BaseDao{
    private final Connection connection;

    public CustomerDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }
    public void saveNewCustomer(Customer customer) throws SQLException {
        String sqlQuery = "insert into customer name, email, password, phone, address_id" +
                "values(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getEmail());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getPhone());
        statement.setInt(5, customer.getAddress().getId());
        statement.executeUpdate();
    }
}

package repository;

import model.Customer;

import java.sql.*;

/**
 * @author Mahsa Alikhani m-58
 */
public class CustomerDao extends BaseDao{
    private final Connection connection;

    public CustomerDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }
    public int saveNewCustomer(Customer customer, int addressId) throws SQLException {
        String sqlQuery = "insert into customer (name, email, password, phone, address_id)" +
                "values(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getEmail());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getPhone());
        statement.setInt(5, addressId);
        statement.executeUpdate();
        ResultSet autoKey = statement.getGeneratedKeys();
        while(autoKey.next()){
            return autoKey.getInt(1);
        }
        return -1;
    }
}

package repository;

import model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class AddressDao {
    private final Connection connection;

    public AddressDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public int saveNewAddress(Address address) throws SQLException {
        String sqlQuery = "insert into address (country, state, city, postal_code)" +
                "values(?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, address.getCountry());
        statement.setString(2, address.getState());
        statement.setString(3, address.getCity());
        statement.setString(4, address.getPostalCode());
        statement.executeUpdate();
        ResultSet autoKey = statement.getGeneratedKeys();
        autoKey.next();
        return autoKey.getInt(1);
    }
}

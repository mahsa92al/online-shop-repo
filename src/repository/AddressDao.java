package repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class AddressDao {
    private final Connection connection;

    public AddressDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }
}

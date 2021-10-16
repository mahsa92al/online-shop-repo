package repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class CustomerDao extends BaseDao{
    private final Connection connection;

    public CustomerDao(Connection connection) throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }
}

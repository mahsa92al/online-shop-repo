package repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class ReadableDao {
    private final Connection connection;

    public ReadableDao(Connection connection) throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }
}

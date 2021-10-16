package repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class ElectronicDao extends BaseDao{
    private final Connection connection;

    public ElectronicDao(Connection connection) throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

}

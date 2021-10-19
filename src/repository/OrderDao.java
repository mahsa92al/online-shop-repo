package repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class OrderDao extends BaseDao{
    private final Connection connection;

    public OrderDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

}

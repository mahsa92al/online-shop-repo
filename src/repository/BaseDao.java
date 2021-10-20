package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class BaseDao {
    private static Connection connection;

    public BaseDao() throws SQLException, ClassNotFoundException {
        connection = getConnection();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/taxi","root","5103583");
        }
        return connection;
    }
}

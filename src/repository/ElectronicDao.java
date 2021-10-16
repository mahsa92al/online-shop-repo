package repository;

import model.Electronic;
import model.enumeration.ElectronicTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class ElectronicDao extends BaseDao{
    private final Connection connection;

    public ElectronicDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public int findIdByName(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format("select id from products" +
                "where name = '%s'", name) );
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            return resultSet.getInt("id");
        }
        return -1;
    }

    public void saveNewItem(int id, int quantity, int totalPrice) throws SQLException {
        String sqlQuery = "insert into order_details (product_id, quantity, price)" +
                "values(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, id);
        statement.setInt(2, quantity);
        statement.setInt(3, totalPrice);
        statement.executeUpdate();
    }
}

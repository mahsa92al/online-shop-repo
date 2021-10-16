package repository;

import model.Electronic;
import model.enumeration.ElectronicTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mahsa Alikhani m-58
 */
public class ElectronicDao extends BaseDao{
    private final Connection connection;

    public ElectronicDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public Map<Integer, Integer> findItemIdPriceByName(String name) throws Exception {
        PreparedStatement statement = connection.prepareStatement(String.format("select id, price from products" +
                "where name = '%s'", name) );
        ResultSet resultSet = statement.executeQuery();
        Map<Integer, Integer> map = new HashMap<>();
        while (resultSet.next()){
            if(resultSet == null){
                throw new Exception("Item not found!");
            }
             int itemId = resultSet.getInt("id");
             int price = resultSet.getInt("price");
             map.put(itemId, price);
             return map;
        }
        return null;
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

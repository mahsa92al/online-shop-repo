package repository;

import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class OrderDao extends BaseDao{
    private final Connection connection;

    public OrderDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public void saveNewOrder(int productId, int quantity, int totalPrice, Date date, int customerId) throws SQLException {
        String sqlQuery = "insert into orders (product_id, quantity, total_price, date, customer_id)" +
                "values(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, productId);
        statement.setInt(2, quantity);
        statement.setInt(3, totalPrice);
        statement.setDate(4, date);
        statement.setInt(5, customerId);

        statement.executeUpdate();
    }

    public int deleteAnOrderByOrderId(int orderId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from orders" +
                "where id = ?");
        statement.setInt(1,orderId);
        int row = statement.executeUpdate();
        return row;
    }

    public List<Order> getAllOrders(int customerId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format("select * from orders where" +
                "customer_id = %d", customerId));
        ResultSet resultSet = statement.executeQuery();
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()){
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setProductId(resultSet.getInt("product_id"));
            order.setQuantity(resultSet.getInt("quantity"));
            order.setTotalPrice(resultSet.getInt("total_price"));
            order.setDate(resultSet.getDate("date"));
            order.setCustomerId(resultSet.getInt("customer_id"));
            orders.add(order);
        }
        return orders;
    }
}

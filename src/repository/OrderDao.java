package repository;

import model.Order;
import model.enumeration.OrderStatus;

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

    public void saveNewOrder(int productId, int quantity, int totalPrice, Date date, int customerId, OrderStatus status) throws SQLException {
        String sqlQuery = "insert into orders (product_id, quantity, total_price, date, customer_id, status)" +
                "values(?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, productId);
        statement.setInt(2, quantity);
        statement.setInt(3, totalPrice);
        statement.setDate(4, date);
        statement.setInt(5, customerId);
        statement.setString(6, status.name());

        statement.executeUpdate();
    }

    public void updateOrderStatus(int orderId, OrderStatus status) throws SQLException {
        String sqlQuery = "update orders set stock = '" + status + "'" +
                "where id = '" + orderId + "'";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.executeUpdate();
    }

    public int deleteAnOrderByOrderId(int orderId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from orders" +
                "where id = ?");
        statement.setInt(1,orderId);
        int row = statement.executeUpdate();
        return row;
    }

    public int findQuantityOrderById(int orderId) throws Exception {
        PreparedStatement statement = connection.prepareStatement(String.format("select quantity from orders" +
                "where id = %d", orderId));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int quantity = resultSet.getInt("quantity");
            if(quantity == 0){
                throw new Exception("Order quantity is not set!");
            }
            return quantity;
        }
        return 0;
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

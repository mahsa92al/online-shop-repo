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

    public void saveNewOrder(int productId, int quantity, int totalPrice, Date date, int customerId, OrderStatus status, int counter) throws SQLException {
        String sqlQuery = "insert into orders (product_id, quantity, total_price, date, customer_id, status, counter)" +
                "values(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, productId);
        statement.setInt(2, quantity);
        statement.setInt(3, totalPrice);
        statement.setDate(4, date);
        statement.setInt(5, customerId);
        statement.setString(6, status.name());
        statement.setInt(7, counter);

        statement.executeUpdate();
    }

    public void updateOrderStatus(int orderId, String status) throws SQLException {
        String sqlQuery = "update orders set status = '" + status + "'" +
                "where id = '" + orderId + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
    }

    public int deleteAnOrderByOrderId(int orderId) throws SQLException {
        Statement statement = connection.createStatement();
        int row = statement.executeUpdate("delete from orders where id = '"+orderId+"'");
        return row;
    }

    public int findTotalOrdersPrice(int customerId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select sum(total_price) as sum_price from orders" +
                " where customer_id = '"+customerId+"'");
        int sumPrice = 0;
        while (resultSet.next()){
            sumPrice = resultSet.getInt("sum_price");
        }
        return sumPrice;
    }

    public int findMaxOrderCounterByCustomerId(int customerId) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "select max(counter)" +
                "as max_counter from orders where customer_id = '" + customerId + "' " +
                "and status = '" + OrderStatus.NOT_CONFIRMED + "'";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()){
            int maxCounter = resultSet.getInt("max_counter");
            return maxCounter;
        }
        return 0;
    }

    public OrderStatus findOrderStatusById(int orderId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select status from orders where id = '"+orderId+"'");
        while (resultSet.next()){
            return OrderStatus.valueOf(resultSet.getString("status"));
        }
        return null;
    }

    public void updateOrderCounter(int orderId, int newCounter) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "update orders set counter = '"+newCounter+"'" +
                "where id = '"+orderId+"'";
        statement.executeUpdate(sqlQuery);
    }

    public List<Order> getAllOrders(int customerId) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "select * from orders where customer_id = '"+customerId+"'";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()){
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setProductId(resultSet.getInt("product_id"));
            order.setQuantity(resultSet.getInt("quantity"));
            order.setTotalPrice(resultSet.getInt("total_price"));
            order.setDate(resultSet.getDate("date"));
            order.setCustomerId(resultSet.getInt("customer_id"));
            order.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
            order.setCounter(resultSet.getInt("counter"));
            orders.add(order);
        }
        return orders;
    }
}

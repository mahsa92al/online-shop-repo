package service;

import repository.OrderDao;

import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class OrderService {
    private final OrderDao orderDao;

    public OrderService() throws SQLException, ClassNotFoundException {
        orderDao = new OrderDao();
    }
}

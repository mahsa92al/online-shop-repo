package service;

import model.Order;
import model.enumeration.OrderStatus;
import repository.OrderDao;
import repository.ProductDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class OrderService {
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderService() throws SQLException, ClassNotFoundException {
        orderDao = new OrderDao();
        productDao = new ProductDao();
    }

    public void addNewOrderToBag(int itemId, int quantity, Date date, int customerId) throws Exception {
        int maxCounter = orderDao.findMaxOrderCounterByCustomerId(customerId);
        if (maxCounter <= 5) {

            int stock = productDao.findStockByProductId(itemId);
            if (stock == 0) {
                throw new Exception("The product stock is zero.");
            } else {
                int price = productDao.findItemPriceById(itemId);
                int totalPrice = price * quantity;
                orderDao.saveNewOrder(itemId, quantity, totalPrice, date, customerId, OrderStatus.NOT_CONFIRMED);
            }
        } else {
            throw new Exception("Your shopping bag is full!");
        }
    }

    public int sumOfOrderPrices(int customerId) throws SQLException {
        int sumPrice = orderDao.findTotalOrdersPrice(customerId);
        return sumPrice;
    }

    public void confirmOrdersByCustomer(List<Order> ordersList) throws SQLException {
        for (Order item : ordersList) {
            if (item.getStatus() == OrderStatus.NOT_CONFIRMED) {
                orderDao.updateOrderStatus(item.getId(), OrderStatus.CONFIRMED);
                int stock = productDao.findStockByProductId(item.getId());
                int newStock = stock - item.getQuantity();
                productDao.updateProductStock(item.getId(), newStock);
            }
        }
    }

    public List<Order> getOrderList(int customerId) throws SQLException {
        List<Order> orders = orderDao.getAllOrders(customerId);
        return orders;
    }

    public boolean removeOrderFromBag(int orderId) throws Exception {
        int row = orderDao.deleteAnOrderByOrderId(orderId);
        if (row == 0) {
            throw new Exception("No order is removed.");
        } else {
            return true;
        }
    }
}

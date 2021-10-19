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
        int stock = productDao.findStockByProductId(itemId);
        int counter = 1;
        if(stock == 0){
            throw new Exception("The product stock is zero.");
        }else{
            if(counter <= 5){
                int price = productDao.findItemPriceById(itemId);
                int totalPrice = price * quantity;
                orderDao.saveNewOrder(itemId, quantity, totalPrice, date, customerId, OrderStatus.NOT_CONFIRMED);
                counter++;
            }else{
                throw new Exception("Your shopping bag is full!");
            }
        }
    }

    public void confirmOrderByCustomer(int orderId, int quantity) throws SQLException {
        orderDao.updateOrderStatus(orderId, OrderStatus.CONFIRMED);
        int stock = productDao.findStockByProductId(orderId);
        int newStock = stock - quantity;
        productDao.updateProductStock(orderId, newStock);
    }

    public List<Order> getOrderList(int customerId) throws SQLException {
        List<Order> orders =  orderDao.getAllOrders(customerId);
        return orders;
    }
    public boolean removeOrderFromBag(int orderId) throws Exception {
        int row = orderDao.deleteAnOrderByOrderId(orderId);
        if(row == 0){
            throw new Exception("No order is removed.");
        }else{
            int quantity = orderDao.findQuantityOrderById(orderId); //if order id was wrong???////////
            int stock = productDao.findStockByProductId(orderId);
            int newStock = stock + quantity;
            productDao.updateProductStock(orderId, newStock);
            return true;
        }
    }
}

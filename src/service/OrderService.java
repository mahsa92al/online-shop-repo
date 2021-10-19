package service;

import model.Order;
import repository.OrderDao;
import repository.ProductDao;

import java.sql.Date;
import java.sql.SQLException;
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
        if(stock == 0){
            throw new Exception("The product stock is zero.");
        }else{
            int counter = 1;
            if(counter <= 5){
                int price = productDao.findItemPriceById(itemId);
                int totalPrice = price * quantity;
                orderDao.saveNewOrder(itemId, quantity, totalPrice, date, customerId);
                int newStock = stock - quantity;
                productDao.updateProductStock(itemId, newStock);
                counter++;
            }else{
                throw new Exception("Your shopping bag is full!");
            }
        }
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
            return true;
        }
    }
}

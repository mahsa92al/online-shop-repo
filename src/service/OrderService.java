package service;

import repository.OrderDao;

import java.sql.Date;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class OrderService {
    private final OrderDao orderDao;

    public OrderService() throws SQLException, ClassNotFoundException {
        orderDao = new OrderDao();
    }

    public void addNewOrderToBag(int itemId, int quantity, Date date, int customerId) throws Exception {
        int stock = orderDao.findStockByProductId(itemId);
        if(stock == 0){
            throw new Exception("The product stock is zero.");
        }else{
            int counter = 1;
            if(counter <= 5){
                int price = orderDao.findItemPriceById(itemId);
                int totalPrice = price * quantity;
                orderDao.saveNewOrder(itemId, quantity, totalPrice, date, customerId);
                int newStock = stock - quantity;
                orderDao.updateProductStock(itemId, newStock);
                counter++;
            }else{
                throw new Exception("Your shopping bag is full!");
            }
        }
    }
}

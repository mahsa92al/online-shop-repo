package service;

import model.Product;
import repository.ProductDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Mahsa Alikhani m-58
 */
public class ProductService {
    private final ProductDao productDao;

    public ProductService() throws SQLException, ClassNotFoundException {
        this.productDao = new ProductDao();
    }
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = productDao.findAllProducts();
        return products;
    }

    public void addNewOrderToBag(int itemId, int quantity, Date date, int customerId) throws Exception {
        int counter = 1;
        if(counter <= 5){
            int price = productDao.findItemPriceById(itemId);
            int totalPrice = price * quantity;
            productDao.saveNewOrder(itemId, quantity, totalPrice, date, customerId);
            counter++;
        }else{
            throw new Exception("Your shopping bag is full!\n1. Confirm\n2. remove some items");
        }
    }
}

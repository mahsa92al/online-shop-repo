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
}

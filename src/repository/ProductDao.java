package repository;

import model.Product;
import model.enumeration.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class ProductDao {
    private final Connection connection;

    public ProductDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }
     public List<Product> findAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("select * from products");
         while (resultSet.next()){
             Product product = new Product();
             product.setId(resultSet.getInt("id"));
             product.setName(resultSet.getString("name"));
             product.setPrice(resultSet.getInt("price"));
             product.setStock(resultSet.getInt("stock"));
             product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));
             products.add(product);
         }
         return products;
     }
}

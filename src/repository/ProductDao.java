package repository;

import model.Product;
import model.enumeration.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, Integer> findItemPriceByName(String name) throws Exception {
        PreparedStatement statement = connection.prepareStatement(String.format("select id, price from products" +
                "where name = '%s'", name) );
        ResultSet resultSet = statement.executeQuery();
        Map<Integer, Integer> map = new HashMap<>();
        while (resultSet.next()){
            if(resultSet == null){
                throw new Exception("Item not found!");
            }
            int itemId = resultSet.getInt("id");
            int price = resultSet.getInt("price");
            map.put(itemId, price);
            return map;
        }
        return null;
    }

    public void saveNewOrder(int productId, int quantity, int totalPrice, Date date) throws SQLException {
        String sqlQuery = "insert into orders (product_id, quantity, total_price, date, customer_id)" +
                "values(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, productId);
        statement.setInt(2, quantity);
        statement.setInt(3, totalPrice);
        statement.setDate(4, date);

        statement.executeUpdate();
    }
}

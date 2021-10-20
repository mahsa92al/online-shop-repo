package repository;

import model.Order;
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
        while (resultSet.next()) {
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

    public Integer findItemPriceById(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("select price from products" +
                "where id = ?");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            if (resultSet == null) {
                throw new Exception("Item's price is not set!");
            }
            int price = resultSet.getInt("price");
            return price;
        }
        return null;
    }

    public int findStockByProductId(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select stock from products" +
                "where id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int stock = resultSet.getInt("stock");
            return stock;
        }
        return 0;
    }

    public void updateProductStock(int id, int newStock) throws SQLException {
        String sqlQuery = "update products set stock = '" + newStock + "'" +
                "where id = '" + id + "'";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.executeUpdate();
    }
}

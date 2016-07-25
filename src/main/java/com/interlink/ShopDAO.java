package com.interlink;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by employee on 7/21/16.
 */
public class ShopDAO extends AbstractDAO {

    public ShopDAO(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public HashMap<Category, Integer> getCategoriesWithItemNumber() throws SQLException {
        HashMap<Category, Integer> result = new HashMap<Category, Integer>();
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT Categories.*,COUNT(items.id) as unique_results " +
                "FROM Categories LEFT JOIN items on " +
                "categories.id=items.category_id GROUP BY Categories.id");
        while (rs.next()) {
            Category category = new Category(rs.getString("name"));
            Integer number = rs.getInt("unique_results");
            result.put(category, number);
        }
        return result;
    }

    public List<Item> getTop3ItemsFromCategory(String categoryName, LocalDateTime now) throws SQLException {
        List<Item> result;
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT Items.*,SUM(orders_items.number) FROM items " +
                "JOIN orders_items ON items.id=orders_items.item_id INNER JOIN Categories on Categories.name='"
                + categoryName + "' WHERE items.category_id=categories.id GROUP BY Items.id " +
                "ORDER BY SUM(orders_items.number) DESC LIMIT 3 ");
        result = parseResultSetToItem(rs);
        rs.close();
        statement.close();
        return result;
    }

    private List<Item> parseResultSetToItem(ResultSet rs) throws SQLException {
        List<Item> result = new ArrayList<>();
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setNumber(rs.getInt("number"));
            item.setPrice(rs.getBigDecimal("price"));
            item.setCategoryId(rs.getInt("category_id"));
            result.add(item);
        }
        return result;
    }

    public Order getOrderForUser(int userId) throws SQLException {
        Order result = new Order();
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT Orders.* FROM Orders " +
                "WHERE Orders.user_id= " + userId + " GROUP BY Orders.id");
        while (rs.next()) {
            result.setId(rs.getInt("id"));
            result.setUserId(rs.getInt("user_id"));
            result.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
            result.setTotalSum(rs.getBigDecimal("total"));
        }
        rs.close();
        statement.close();
        return result;
    }

    public List<Item> getItemsForOrder(int orderId) throws SQLException {
        List<Item> result;
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT Items.* FROM Orders_items " +
                "JOIN Items on Orders_items.item_id=items.id WHERE orders_items.order_id='" + orderId + "';");
        result = parseResultSetToItem(rs);
        rs.close();
        statement.close();
        return result;
    }
}

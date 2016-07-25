package com.interlink;

import com.interlink.entity.Category;
import com.interlink.entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    Session session;
    public ShopDAO(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public ShopDAO(Session session) {
        this.session = session;
    }

    public HashMap<Category, Integer> getCategoriesWithItemNumber() throws SQLException {
        HashMap<Category, Integer> result = new HashMap<Category, Integer>();
        List<Category> categories = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            categories = session.createQuery("FROM Category").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        for (Category category : categories) {
            result.put(category, category.getItems().size());
        }
        return result;
    }


    public List<Item> getTop3ItemsFromCategory(String categoryName, LocalDateTime now) throws SQLException {
        List<Item> result = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            result = session.createQuery("Select order.items FROM Order order WHERE order.items.category").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

//        Connection connection = connectionFactory.getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery("SELECT Items.* FROM items " +
//                "JOIN orders_items ON items.id=orders_items.item_id INNER JOIN Categories on Categories.name='"
//                + categoryName + "'JOIN Orders ON Orders.id=orders_items.order_id" +
//                " WHERE items.category_id=categories.id AND(Orders.dateTime>=" +
//                "DATE_SUB('" + Timestamp.valueOf(now) + "', INTERVAL 2 MONTH)) GROUP BY Items.id " +
//                "ORDER BY SUM(orders_items.number) DESC LIMIT 3 ");
//        result = parseResultSetToItem(rs);
//        rs.close();
//        statement.close();
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
            // item.setCategory(rs.getInt("category_id"));
            result.add(item);
        }
        return result;
    }

//    public Order getOrderForUser(int userId) throws SQLException {
//        Order result = new Order();
//        Connection connection = connectionFactory.getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery("SELECT Orders.* FROM Orders " +
//                "WHERE Orders.user_id= " + userId + " GROUP BY Orders.id");
//        while (rs.next()) {
//            result.setId(rs.getInt("id"));
//            result.setUser(rs.getInt("user_id"));
//            result.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
//            result.setTotalSum(rs.getBigDecimal("total"));
//        }
//        rs.close();
//        statement.close();
//        return result;
//    }

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

package com.interlink;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by employee on 7/21/16.
 */
public class CategoryDAO extends AbstractDAO {

    public CategoryDAO(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public List<Category> get() throws SQLException {
        List<Category> result = new ArrayList<Category>();
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select * From Categories");
        while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            result.add(category);
        }
        rs.close();
        statement.close();
        return result;
    }

    public void put(Category category) throws SQLException {
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO categories(name) VALUES('" + category.getName() + "');");
        statement.close();
    }

    public HashMap<Category, Integer> getCategoriesWithItemQuantity() throws SQLException {
        HashMap<Category, Integer> result = new HashMap<Category, Integer>();
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT Category,COUNT(item.id) FROM categories Join items on " +
                "categories.id=items.category.id");
        while (rs.next()) {
            Category category = new Category();
            category.setName(rs.getString("name"));
            Integer number = rs.getInt("COUNT(item.id)");
            result.put(category, number);
        }
        return result;
    }
}

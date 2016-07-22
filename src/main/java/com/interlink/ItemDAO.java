package com.interlink;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 7/22/16.
 */
public class ItemDAO extends AbstractDAO {
    public ItemDAO(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public List<Item> getCategories() throws SQLException {
        List<Item> result = new ArrayList<Item>();
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select Name From Categories");
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setQuantity(rs.getInt("number"));
            item.setCategoryId(rs.getInt("category_id"));
            item.setPrice(rs.getBigDecimal("price"));
            result.add(item);
        }
        rs.close();
        statement.close();
        connection.close();
        return result;
    }
}

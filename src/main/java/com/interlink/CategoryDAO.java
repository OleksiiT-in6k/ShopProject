package com.interlink;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 7/21/16.
 */
public class CategoryDAO {
    ConnectionFactory connectionFactory;

    public CategoryDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<String> getCategoryNames() throws SQLException {
        List<String> result = new ArrayList<String>();
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select Name From Categories");
        while (rs.next()) {
            result.add(rs.getString("name"));
        }
        rs.close();
        statement.close();
        connection.close();
        return result;
    }
}

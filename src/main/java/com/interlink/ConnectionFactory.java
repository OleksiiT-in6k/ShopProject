package com.interlink;

import java.sql.Connection;

/**
 * Created by employee on 7/21/16.
 */
public interface ConnectionFactory {
    Connection getConnection();
}

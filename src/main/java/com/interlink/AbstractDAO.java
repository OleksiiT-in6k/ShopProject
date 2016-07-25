package com.interlink;

/**
 * Created by employee on 7/22/16.
 */
public abstract class AbstractDAO {
    ConnectionFactory connectionFactory;

    public AbstractDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public AbstractDAO() {

    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}

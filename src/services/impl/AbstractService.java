package services.impl;

import db.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractService {

    private static final Logger LOGGER = Logger.getLogger(AbstractService.class);

    public void startTransaction() throws SQLException {
        ConnectionManager.getConnection().setAutoCommit(false);
    }

    public Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    public void commit() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            LOGGER.error("Error commit connection" + e);
        }
    }

    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            LOGGER.error("Error rollback connection" + e);
        }
    }
}

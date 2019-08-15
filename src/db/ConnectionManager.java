package db;

import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() {

        if (threadLocal.get() == null) {
            try {
                threadLocal.set(DataSource.getInstance().getConnection());
            } catch (SQLException | PropertyVetoException e) {
            LOGGER.error("Error get connection from database");
            }
        }
        return threadLocal.get();
    }
}



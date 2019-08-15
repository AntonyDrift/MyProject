package db;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {

    private static DataSource dataSource;
    private ComboPooledDataSource cpds;

    private final String URL;
    private final String DRIVER;
    private final String USER;
    private final String PASSWORD;

    {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        if (rb == null) {
            URL = DRIVER = USER = PASSWORD = "WRONG";
            System.out.println("Bundle is not initialized");
        } else {
            URL = rb.getString("url");
            DRIVER = rb.getString("driver");
            USER = rb.getString("user");
            PASSWORD = rb.getString("password");
        }
    }

    public DataSource() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
            cpds.setDriverClass(DRIVER);
            cpds.setJdbcUrl(URL);
            cpds.setUser(USER);
            cpds.setPassword(PASSWORD);

            cpds.setMinPoolSize(5);
            cpds.setMaxPoolSize(20);
            cpds.setAcquireIncrement(10);
            cpds.setMaxStatements(200);
    }

    public static synchronized DataSource getInstance() throws PropertyVetoException {
        if (dataSource == null) {
            dataSource = new DataSource();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}

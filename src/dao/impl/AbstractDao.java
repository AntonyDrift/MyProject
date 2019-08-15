package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionManager;
import org.apache.log4j.Logger;


public abstract class AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    protected PreparedStatement preparedStatement(String sql) throws SQLException {

        return ConnectionManager.getConnection().prepareStatement(sql);
    }

    protected void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Error get ResultSet" + e);
        }
    }
}


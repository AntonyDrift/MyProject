package dao;

import entities.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends GenericDao<Client> {

    List<String> getClientsEmail() throws SQLException;
}

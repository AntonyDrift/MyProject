package dao.impl;

import dao.ClientDao;
import entities.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends AbstractDao implements ClientDao {

    private static final String GET_CLIENTS_EMAIL_QUERY = "SELECT email FROM clients";
    private static final String GET_ALL_CLIENTS_QUERY = "SELECT * FROM clients";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM clients WHERE client_id=?";
    private static final String UPDATE_CLIENT_QUERY = "UPDATE clients SET surname=?, email=?," +
            "phone_number=? WHERE client_id=?";
    private static final String ADD_CLIENT_QUERY = "INSERT INTO clients" +
            "(surname, email, phone_number) VALUES (?, ?, ?)";

    private PreparedStatement getClientsEmail;
    private PreparedStatement getAllClients;
    private PreparedStatement updateClient;
    private PreparedStatement deleteClient;
    private PreparedStatement addClient;

    private static volatile ClientDao INSTANCE = null;

    public static ClientDao getInstance() {

        ClientDao clientDao = INSTANCE;
        if (clientDao == null) {
            synchronized (ClientDaoImpl.class) {
                clientDao = INSTANCE;
                if (clientDao == null) {
                    INSTANCE = clientDao = new ClientDaoImpl();
                }
            }
        }
        return clientDao;
    }

    private Client populateEntity(ResultSet resultSet) throws SQLException {

        Client entity = new Client();

        entity.setClient_id(resultSet.getLong(1));
        entity.setSurname(resultSet.getString(2));
        entity.setEmail(resultSet.getString(3));
        entity.setPhone_number(resultSet.getInt(4));

        return entity;
    }

    @Override
    public List<String> getClientsEmail() throws SQLException {

        getClientsEmail = preparedStatement(GET_CLIENTS_EMAIL_QUERY);

        List<String> clientList = new ArrayList<>();
        ResultSet resultSet = getClientsEmail.executeQuery();

        while (resultSet.next()) {
            clientList.add(resultSet.getString(1));
        }
        close(resultSet);
        return clientList;
    }

    @Override
    public Client add(Client client) throws SQLException {

        addClient = preparedStatement(ADD_CLIENT_QUERY);
        addClient.setString(1, client.getSurname());
        addClient.setString(2, client.getEmail());
        addClient.setInt(3, client.getPhone_number());
        addClient.executeUpdate();

        return client;
    }

    @Override
    public List<Client> getAll() throws SQLException {

        getAllClients = preparedStatement(GET_ALL_CLIENTS_QUERY);

        List<Client> clientList = new ArrayList<>();
        ResultSet resultSet = getAllClients.executeQuery();

        while (resultSet.next()) {
            clientList.add(populateEntity(resultSet));
        }
        close(resultSet);
        return clientList;
    }

    @Override
    public Client update(Client client) throws SQLException {

        updateClient = preparedStatement(UPDATE_CLIENT_QUERY);
        updateClient.setString(1, client.getSurname());
        updateClient.setString(2, client.getEmail());
        updateClient.setInt(3, client.getPhone_number());
        updateClient.setLong(4, client.getClient_id());
        updateClient.executeUpdate();

        return client;
    }

    @Override
    public void delete(long client_id) throws SQLException {

        deleteClient = preparedStatement(DELETE_CLIENT_QUERY);
        deleteClient.setLong(1, client_id);
        deleteClient.executeUpdate();
    }
}

package services.impl;

import dao.ClientDao;
import dao.impl.ClientDaoImpl;
import entities.Client;
import org.apache.log4j.Logger;
import services.ClientService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl extends AbstractService implements ClientService {

    private static volatile ClientService INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);
    private ClientDao clientDao = ClientDaoImpl.getInstance();
    public static boolean clientErrorStatusLog;

    public static ClientService getInstance() {
        ClientService clientService = INSTANCE;
        if (clientService == null) {
            synchronized (ClientServiceImpl.class) {
                clientService = INSTANCE;
                if (clientService == null) {
                    INSTANCE = clientService = new ClientServiceImpl();
                }
            }
        }
        return clientService;
    }



    @Override
    public Client add(Client client) {

        try {
            client = clientDao.add(client);
            clientErrorStatusLog = false;
            LOGGER.error("GOOD add client " + client);

        } catch (SQLException e) {
            LOGGER.error("Error add client " + client);
            clientErrorStatusLog = true;
        }

        return client;
    }

    @Override
    public Client update(Client client) {

        try {
            client = clientDao.update(client);
            clientErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error update client" + client);
            clientErrorStatusLog = true;
        }
        return client;
    }

    @Override
    public void delete(long id) {

        try {
            clientDao.delete(id);
            clientErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error delete client #" + id);
            clientErrorStatusLog = true;
        }
    }

    @Override
    public List<Client> getAll() {

        List<Client> allClients = new ArrayList<>();

        try {
            allClients = clientDao.getAll();
            clientErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error get all clients");
            clientErrorStatusLog = true;
        }
        return allClients;
    }

    @Override
    public List<String> getClientsEmail() {

        List<String> clientsEmail = new ArrayList<>();

        try {
            clientsEmail = clientDao.getClientsEmail();
            clientErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error get clients email list " + clientsEmail);
            clientErrorStatusLog = true;
        }
        return clientsEmail;
    }
}
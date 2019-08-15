package services;

import entities.Client;

import java.util.List;

public interface ClientService extends GenericService<Client> {

    List<String> getClientsEmail();
}

package services;

import entities.Client;
import services.impl.ClientServiceImpl;

public class TestClientService {

    ClientServiceImpl clientSI = new ClientServiceImpl();
    private static Client client = new Client();

    public static void main(String[] args) {

        TestClientService run = new TestClientService();
        run.methodGetAllClients();
        run.methodGetClientsEmail();
        run.methodAddClient();
        run.methodUpdateClient();
        run.methodDeleteClient();
    }

    private void methodGetAllClients() {

        System.out.println("Get all clients list method");
        for (Client clients : clientSI.getAll()) {
            System.out.println(clients.getClient_id() + " " + clients.getSurname() + " " +
                    clients.getEmail() + " " + clients.getPhone_number());
        }
    }

    private void methodGetClientsEmail() {

        System.out.println("Get clients email list");
        for (String emails : clientSI.getClientsEmail()) {
            System.out.println(emails + " ");
        }
    }

    private void methodAddClient(){

        System.out.println("Add client: surname, email, phone_number");
        client.setSurname("test");
        client.setEmail("test@test.net");
        client.setPhone_number(123456789);
        clientSI.add(client);
    }

    private void methodUpdateClient() {

        System.out.println("Update client: surname WHERE client_id=?");
        client.setSurname("another test name");
        client.setClient_id(14);
        clientSI.update(client);
    }
    private void  methodDeleteClient() {

        System.out.println("Delete client WHERE client_id=?");
        clientSI.delete(15);
    }

}

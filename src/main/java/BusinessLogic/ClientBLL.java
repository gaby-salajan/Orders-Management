package BusinessLogic;

import DataAccess.ClientDAO;
import Model.Client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClientBLL {
    private final ClientDAO clientDAO;

    /**
     * Constructor which initializes clientDAO
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * Returns all the clients from the DB
     * @return list of Clients
     */
    public List<Client> getClients() {
        return clientDAO.findAll();
    }
    /**
     * Inserts a client into the DB
     * @param c Client to be inserted
     */
    public void insertClient(Client c) {
        clientDAO.insert(c);
    }
    /**
     * Updates the matching row from the DB with the given client
     * @param c Client to be updated with current value
     */
    public void updateClient(Client c) {
        clientDAO.update(c);
    }

    /**
     * Deletes the given client from the DB
     * @param c Client to be deleted
     */
    public void deleteClient(Client c) {
        clientDAO.delete(c);
        System.out.println("Client "+c+" deleted");
    }

    /**
     * Generates the headers for the Client Table based on the declared fields of the class.
     * @return list of Client class fields
     */
    public List<String> generateHeaders(){
        List<String> res = new ArrayList<>();
        for (Field field : Client.class.getDeclaredFields()) {
            res.add(field.getName());
        }
        return res;
    }

}

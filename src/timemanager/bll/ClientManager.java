package timemanager.bll;

import java.io.IOException;
import java.util.ArrayList;
import timemanager.be.Client;
import timemanager.dal.ClientManagerFacade;
import timemanager.dal.database.ClientManagerDBDAO;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class ClientManager {
    public ArrayList<Client> clients = new ArrayList<>();
    
    ClientManagerFacade cm;

    /**
     * ClientManager Constructor
     * @throws IOException 
     */
    public ClientManager() throws IOException {
        cm = new ClientManagerDBDAO();
    }
    
    /**
     * Get all the clients from DAL
     * @return clients
     */
    public ArrayList<Client> getClients() {
        clients = cm.getClients();
        return clients; // return cm.getClients();
    }
    
    /**
     * Get a client
     * @param client
     * @return client
     */
    public Client getClient(Client client) {
        return cm.getClient(client);
    }
    
    /**
     * Store a new client
     * @param client
     * @return boolean
     */
    public boolean storeClient(Client client) {
        return cm.storeClient(client);
    }
    
    /**
     * Update a given client
     * @param client
     * @return boolean
     */
    public boolean updateClient(Client client) {
        return cm.updateClient(client);
    }
    
    /**
     * Delete a given client
     * @param client
     * @return boolean
     */
    public boolean deleteClient(Client client) {
        return cm.deleteClient(client);
    }
    
}
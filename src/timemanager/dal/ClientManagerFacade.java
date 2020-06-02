package timemanager.dal;

import java.util.ArrayList;
import timemanager.be.Client;

/**
 *
 * @author andreasvillumsen & Christian
 */
public interface ClientManagerFacade {
    
    /**
     * Get clients
     * @return clients
     */
    public ArrayList<Client> getClients();
    
    /**
     * Get client
     * @param client
     * @return client
     */
    public Client getClient(Client client);
    
    /**
     * Store a new client
     * @param client
     * @return boolean
     */
    public boolean storeClient(Client client);
    
    /**
     * Update a client in database
     * @param client
     * @return boolean
     */
    public boolean updateClient(Client client);
    
    /**
     * Delete a project from database
     * @param client
     * @return boolean
     */
    public boolean deleteClient(Client client);
}

package timemanager.gui.model;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Client;
import timemanager.bll.ClientManager;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class ClientModel {
    private static ClientModel single_instance = null;
    private ClientManager cm;
    private ObservableList<Client> clients;
    
    /**
     * Get ClientModel instnace
     * @return ClientModel
     * @throws IOException 
     */
    public static ClientModel getInstance() throws IOException {
        if (single_instance == null) 
            single_instance = new ClientModel(); 
  
        return single_instance; 
    }
    
    /**
     * ClientModel constructor
     * @throws IOException 
     */
    private ClientModel() throws IOException {
        cm = new ClientManager();
        clients = FXCollections.observableArrayList();
    }
    
    /**
     * Refresh clients arraylist data
     */
    public void refreshData() {
        clients.clear();
        clients.addAll(cm.getClients());
    }
    
    /**
     * Get ObservableList of clients
     * @return clients
     */
    public ObservableList<Client> getClients() {
        refreshData();
        return clients;
    }
    
    /**
     * Store a client to database
     * @param client 
     */
    public void storeClient(Client client){
        cm.storeClient(client);
        refreshData();
    }
    
    /**
     * Update a client in database
     * @param client 
     */
    public void updateClient(Client client) {
        cm.updateClient(client);
        refreshData();
    }
    
    /**
     * Delete a client from database
     * @param client 
     */
    public void deleteClient(Client client) {
        cm.deleteClient(client);
        refreshData();
    }
}

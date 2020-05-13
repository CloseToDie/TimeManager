package timemanager.gui.model;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Client;
import timemanager.bll.ClientManager;

/**
 *
 * @author andreasvillumsen
 */
public class ClientModel {
    private static ClientModel single_instance = null; 
    private ClientManager cm;
    private ObservableList<Client> clients;
    
    public static ClientModel getInstance() throws IOException {
        if (single_instance == null) 
            single_instance = new ClientModel(); 
  
        return single_instance; 
    }
    
    private ClientModel() throws IOException {
        cm = new ClientManager();
        clients = FXCollections.observableArrayList();
    }
    
    public void refreshData() {
        clients.clear();
        clients.addAll(cm.getClients());
    }
    
    public ObservableList<Client> getClients() {
        refreshData();
        return clients;
    }
    
    public void storeClient(Client client){
        cm.storeClient(client);
        refreshData();
    }
}

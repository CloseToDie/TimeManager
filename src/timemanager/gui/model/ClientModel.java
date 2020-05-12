package timemanager.gui.model;

import java.io.IOException;
import timemanager.be.Client;
import timemanager.bll.ClientManager;



/**
 *
 * @author andreasvillumsen
 */
public class ClientModel {
    private ClientManager cm;
    public ClientModel() throws IOException {
        cm = new ClientManager();
    }
    public void storeClient(Client client){
        cm.storeClient(client);
        
    }
    
    

}

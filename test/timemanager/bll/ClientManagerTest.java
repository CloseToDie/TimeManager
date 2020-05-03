/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import timemanager.be.Client;

/**
 *
 * @author kaspe
 */
public class ClientManagerTest
{
    
    
    
    public ClientManagerTest() throws IOException
    {
        ClientManager cm = new ClientManager();
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    public ObservableList<Client> createClientData() throws IOException
    {
        ClientManager cm = new ClientManager();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        
        Client client1 = new Client(1, "Kasper");
        Client client2 = new Client(2, "Andreas");
        Client client3 = new Client(3, "Christian");
        Client client4 = new Client(4, "Mikkel");
        client1.setId(1);
        client2.setId(2);
        client3.setId(3);
        client4.setId(4);
        
        return clients;
               
    }
    
    @Test
    public void firstClientTest() throws IOException
    {
        ClientManager cm = new ClientManager();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        
        Client client1 = clients.get(1);
        Client client2 = clients.get(2);
        Client client3 = clients.get(3);
        Client client4 = clients.get(4);
    
        ObservableList<Client> results = FXCollections.observableArrayList();
        
        int getName = (1);
        
        results = (ObservableList<Client>) clients.get(getName);
        
        assertEquals(1, results.size());
        assertEquals(client1, results.get(0));
        
    }
}

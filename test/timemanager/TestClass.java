/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

import java.io.IOException;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import timemanager.be.Client;
import timemanager.be.Project;
import timemanager.be.Timer;
import timemanager.be.User;
import timemanager.bll.ClientManager;
import timemanager.bll.ProjectManager;
import timemanager.bll.TimeManager;
import timemanager.bll.UserManager;
/**
 *
 * @author Mikkel H
 */
public class TestClass
{

    public ClientManager cm;
    public ProjectManager pm;
    public TimeManager tm;
    public UserManager um;
    
    public TestClass() throws IOException, Exception
    {
        cm = new ClientManager();
        pm = new ProjectManager();
        tm = new TimeManager();
        um = new UserManager();
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
        ObservableList<Client> clients = FXCollections.observableArrayList();
        
        Client client1 = new Client(1, "Kasper");
        Client client2 = new Client(2, "Andreas");
        Client client3 = new Client(3, "Christian");
        Client client4 = new Client(4, "Mikkel");
        clients.add(1, client1);
        clients.add(2, client2);
        clients.add(3, client3);
        clients.add(4, client4);
        
        return clients;
    };
    public ObservableList<Project> createProjectData() throws IOException 
    {
        ObservableList<Project> projects = FXCollections.observableArrayList();
        
        Project project1 = new Project(1, "Project1", 30.50, 1);
        Project project2 = new Project(2, "Project2", 50.50, 2);
        Project project3 = new Project(3, "Project3", 70.50, 3);
        Project project4 = new Project(4, "Project4", 90.50, 4);
        projects.add(1, project1);
        projects.add(2, project2);
        projects.add(3, project3);
        projects.add(4, project4);
        
        return projects;
    };
    public ObservableList<Timer> createTimerData() throws IOException 
    {
        ObservableList<Timer> timers = FXCollections.observableArrayList();
        
        Timer timer1 = new Timer(1, LocalDateTime.MIN, LocalDateTime.MIN, 10, 1);
        Timer timer2 = new Timer(2, LocalDateTime.MIN, LocalDateTime.MIN, 20, 2);
        Timer timer3 = new Timer(3, LocalDateTime.MIN, LocalDateTime.MIN, 30, 3);
        Timer timer4 = new Timer(4, LocalDateTime.MIN, LocalDateTime.MIN, 40, 4);
        timers.add(1, timer1);
        timers.add(2, timer2);
        timers.add(3, timer3);
        timers.add(4, timer4);
        
        return timers;
    };
    public ObservableList<User> createUserData() throws IOException 
    {
        ObservableList<User> users = FXCollections.observableArrayList();
        
        User user1 = new User(1, "Bo", "Bo@Bo.Com", "1234", null, true);
        User user2 = new User(2, "Bob", "Bob@Bo.Com", "2345", null, false);
        User user3 = new User(3, "Bobby", "Bobby@Bo.Com", "3456", null, false);
        User user4 = new User(4, "Bobbynson", "Bobbynson@Bo.Com", "4567", null, false);
        users.add(1, user1);
        users.add(2, user2);
        users.add(3, user3);
        users.add(4, user4);
        
        return users;
    };
}

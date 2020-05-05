/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import static org.junit.Assert.*;
import org.junit.Test;
import timemanager.be.User;
import timemanager.dal.UserManagerFacade;
import timemanager.dal.database.MSSQLDatabaseConnector;

/**
 *
 * @author kaspe
 */
public class DefaultPrepAndExpectedTestCase
{
    UserManagerFacade um;
    private final SQLServerDataSource dataSource;     
    private final MSSQLDatabaseConnector dbCon;
    /**
     * DatabaseConnector constructor
     * the class takes the info from the DBSettings.txt file and establishes a connection to the database. 
     * @throws java.io.FileNotFoundException
     */
    public DefaultPrepAndExpectedTestCase() throws FileNotFoundException, IOException{
        Properties props = new Properties();
        dbCon = new MSSQLDatabaseConnector();
        props.load(new FileReader("DBSettings.txt"));
        
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }

    
   
    protected void setUp() throws Exception
    {
        setUp();

       
        IDatabaseConnection connection = null;
                
        IDataSet dataSet = null;
        
        try
        {
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        }
        finally
        {
            connection.close();
        }
    }
    protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.NONE;
    }
    
@Test
public void testGetAllUsers()
{
    ArrayList<User> users = this.um.getUsers();
    assertEquals( 1,users.size());
}

@Test
public void testGetUser()
{
    User user = this.um.getUserByName("1234");
    assertEquals( "username",user.getName());
}

}
        
            
            
    
//    public boolean createStoreUser(User user) {
//        try ( Connection con = dbCon.getConnection()) {
//            PreparedStatement ps = con.prepareStatement("INSERT INTO user "
//                    + "(name, email, password, salt, isAdmin) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, "Kasper");
//            ps.setString(2, "Kasper@email");
//            ps.setString(3, "1234");
//            ps.setString(4, new String(user.getSalt()));
//            ps.setBoolean(5, false);
//
//            ps.executeUpdate();
//            ResultSet rs = ps.getGeneratedKeys();
//
//            if (rs.next()) {
//                user.setId((int) rs.getLong(1));
//            } else {
//                return false;
//            }
//
//            return true;
//
//        } catch (SQLServerException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return false;
//    }
    
    

  


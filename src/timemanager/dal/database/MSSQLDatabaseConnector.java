package timemanager.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class MSSQLDatabaseConnector {

    private final SQLServerDataSource dataSource;

    /**
     * DatabaseConnector constructor
     * the class takes the info from the DBSettings.txt file and establishes a connection to the database. 
     * @throws java.io.FileNotFoundException
     */
    public MSSQLDatabaseConnector() throws FileNotFoundException, IOException{
        Properties props = new Properties();
       
        props.load(new FileReader("DBSettings.txt"));
        
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }

    /**
     * Get the connection to the database
     *
     * @return Database connection
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}

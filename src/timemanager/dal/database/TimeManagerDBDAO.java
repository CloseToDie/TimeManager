/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.dal.database;
        
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import timemanager.be.Timer;
import timemanager.dal.TimeManagerFacade;

/**
 *
 * @author andreasvillumsen
 */
public class TimeManagerDBDAO implements TimeManagerFacade {
    private final MSSQLDatabaseConnector dbCon;

    /**
     * TimeManagerDBDAO constructor
     * @throws java.lang.Exception
     */
    public TimeManagerDBDAO() throws Exception {
        dbCon = new MSSQLDatabaseConnector();
    }
    
    /**
     * Create a new playlist in the database
     *
     * @param playlist
     * @return playlist or null
     */
    public Timer createPlaylist(Timer timer) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO playlist "
                    + "(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, timer.getName());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                timer.setId((int) rs.getLong(1));
            } else {
                return null;
            }

            return timer;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
}

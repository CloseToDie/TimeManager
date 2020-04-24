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
     * Get a list of all playlists from the database
     *
     * @return list of playlist or null
     */
    public List<Timer> getAllPlaylists() {
        ArrayList<Timer> playlists = new ArrayList<>();

        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM timelog");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                playlists.add(new Timer());
            }
            return playlists;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
}

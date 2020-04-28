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
import java.sql.Timestamp;
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
     * Store a timer to the database.
     * @param timer
     * @return boolean of success
     */
    public boolean storeTimer(Timer timer) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO timer "
                    + "(startTime, stopTime, spentTime) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(timer.getStartTime()));
            ps.setTimestamp(2, Timestamp.valueOf(timer.getStopTime()));
            ps.setDouble(3, timer.getSpentTime());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                timer.setId((int) rs.getLong(1));
            } else {
                return false;
            }

            return true;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    
    public boolean updateTimer(Timer timer) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE timer SET "
                    + "startTime = ?, stopTime = ?, spentTime = ? WHERE id = ?");
            ps.setTimestamp(1, Timestamp.valueOf(timer.getStartTime()));
            ps.setTimestamp(2, Timestamp.valueOf(timer.getStopTime()));
            ps.setDouble(3, timer.getSpentTime());
            ps.setInt(4, timer.getId());
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    
}

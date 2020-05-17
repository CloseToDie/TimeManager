package timemanager.dal.database;
        
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
     * Get a list of all timers
     * @return timers
     */
    @Override
    public ArrayList<Timer> getTimers() {
        ArrayList<Timer> timers = new ArrayList<>();

        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM timelog");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                LocalDateTime startTime = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime stopTime = rs.getTimestamp("stop").toLocalDateTime();
                double spentTime = rs.getDouble("spent_time");
                boolean billable = rs.getBoolean("billable");
                int task_id = rs.getInt("task_id");
                int user_id = rs.getInt("user_id");
                timers.add(new Timer(id, date, startTime, stopTime, spentTime, billable, task_id, user_id));
            }
            return timers;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    @Override
    public ArrayList<Timer> getTimers(int taskId) {
        ArrayList<Timer> timers = new ArrayList<>();

        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM timelog WHERE task_id = ?");
            ps.setInt(1, taskId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                LocalDateTime startTime = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime stopTime = rs.getTimestamp("stop").toLocalDateTime();
                double spentTime = rs.getDouble("spent_time");
                boolean billable = rs.getBoolean("billable");
                int task_id = rs.getInt("task_id");
                int user_id = rs.getInt("user_id");
                timers.add(new Timer(id, date, startTime, stopTime, spentTime, billable, task_id, user_id));
            }
            return timers;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Get a timer from the database
     * @param timer
     * @return timer
     */
    @Override
    public Timer getTimer(Timer timer) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM timelog WHERE id = ?");
            ps.setInt(1, timer.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                LocalDateTime startTime = rs.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime stopTime = rs.getTimestamp("stopTime").toLocalDateTime();
                double spentTime = rs.getDouble("spentTime");
                boolean billable = rs.getBoolean("billable");
                int task_id = rs.getInt("task_id");
                int user_id = rs.getInt("user_id");
                return new Timer(id, date, startTime, stopTime, spentTime, billable, task_id, user_id);
            }

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    /**
     * Store a timer to the database.
     * @param timer
     * @return boolean of success
     */
    @Override
    public boolean storeTimer(Timer timer) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO timelog "
                    + "(date, start, stop, billable, spent_time, task_id, user_id) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(timer.getDate()));
            ps.setTimestamp(2, Timestamp.valueOf(timer.getStartTime()));
            ps.setTimestamp(3, Timestamp.valueOf(timer.getStopTime()));
            ps.setBoolean(4, timer.isBillable());
            ps.setDouble(5, timer.getSpentTime());
            ps.setInt(6, timer.getTaskId());
            ps.setInt(7, timer.getUserId());

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
    
    /**
     * Update a given timer in the database
     * @param timer
     * @return boolean of success
     */
    @Override
    public boolean updateTimer(Timer timer) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE timelog SET "
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
    
    /**
     * Delete a given timer from the database.
     * @param timer
     * @return boolean of success
     */
    @Override
    public boolean deleteTimer(Timer timer) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM timelog WHERE id = ?");
            ps.setInt(1, timer.getId());
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

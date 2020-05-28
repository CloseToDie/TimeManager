/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import timemanager.be.Task;
import timemanager.dal.TaskManagerFacade;

/**
 *
 * @author andreasvillumsen
 */
public class TaskManagerDBDAO implements TaskManagerFacade{
    private final MSSQLDatabaseConnector dbCon;

    public TaskManagerDBDAO() throws IOException {
        dbCon = new MSSQLDatabaseConnector();
    }

    @Override
    public ArrayList<Task> getTasks(int projectId) {
        ArrayList<Task> tasks = new ArrayList<>();

        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM task WHERE project_id = ?");
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                int project_id = rs.getInt("project_id");
                tasks.add(new Task(id, description, project_id));
            }
            return tasks;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean storeTask(Task task) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO task "
                    + "(description, project_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getDescription());
            ps.setInt(2, task.getProjectId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                task.setId((int) rs.getLong(1));
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

    @Override
    public boolean updateTask(Task task) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE task SET "
                    + "description = ?, project_id = ? WHERE id = ?");
            ps.setString(1, task.getDescription());
            ps.setInt(2, task.getProjectId());
            ps.setInt(3, task.getId());
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteTask(Task task) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM task WHERE id = ?");
            ps.setInt(1, task.getId());
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

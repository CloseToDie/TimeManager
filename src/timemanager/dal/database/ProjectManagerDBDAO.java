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
import java.sql.Timestamp;
import java.util.ArrayList;
import timemanager.be.Project;
import timemanager.dal.ProjectManagerFacade;

/**
 *
 * @author andreasvillumsen
 */
public class ProjectManagerDBDAO implements ProjectManagerFacade {
    private final MSSQLDatabaseConnector dbCon;

    public ProjectManagerDBDAO() throws IOException {
        dbCon = new MSSQLDatabaseConnector();
    }

    @Override
    public ArrayList<Project> getProjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Project getProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Store a new project to the database
     * @param project
     * @return boolean of success
     */
    @Override
    public boolean storeProject(Project project) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO project "
                    + "(name, salary, clientId) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, project.getName());
            ps.setDouble(2, project.getSalary());
            ps.setInt(3, project.getClientId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                project.setId((int) rs.getLong(1));
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
     * Update a given project's info in the database
     * @param project
     * @return boolean of success
     */
    @Override
    public boolean updateProject(Project project) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE project SET "
                    + "name = ?, salary = ?, clientId = ? WHERE id = ?");
            ps.setString(1, project.getName());
            ps.setDouble(2, project.getSalary());
            ps.setInt(3, project.getClientId());
            ps.setInt(4, project.getId());
            
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
     * Delete a project in the database
     * @param project
     * @return boolean of success
     */
    @Override
    public boolean deleteProject(Project project) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM project WHERE id = ?");
            ps.setInt(1, project.getId());
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

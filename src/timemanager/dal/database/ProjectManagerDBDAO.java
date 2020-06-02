package timemanager.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import timemanager.be.Project;
import timemanager.dal.ProjectManagerFacade;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class ProjectManagerDBDAO implements ProjectManagerFacade {
    private final MSSQLDatabaseConnector dbCon;

    /**
     * ProjectManagerDBDAO Constructor
     * @throws IOException 
     */
    public ProjectManagerDBDAO() throws IOException {
        dbCon = new MSSQLDatabaseConnector();
    }

    /**
     * Get a list of all projects
     * @return projects
     */
    @Override
    public ArrayList<Project> getProjects() {
        ArrayList<Project> projects = new ArrayList<>();

        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM project");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double salary = rs.getDouble("salary");
                int clientId = rs.getInt("client_id");
                projects.add(new Project(id, name, salary, clientId));
            }
            return projects;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    /**
     * Get a list of projects for a client.
     * @param clientId
     * @return projects
     */
    @Override
    public ArrayList<Project> getProjects(int clientId) {
        ArrayList<Project> projects = new ArrayList<>();

        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM project WHERE client_id = ?");
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double salary = rs.getDouble("salary");
                int client_id = rs.getInt("client_id");
                projects.add(new Project(id, name, salary, client_id));
            }
            return projects;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Get a project from a database by id
     * @param project
     * @return project
     */
    @Override
    public Project getProject(Project project) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM project WHERE id = ?");
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double salary = rs.getDouble("salary");
                int clientId = rs.getInt("client_id");
                return new Project(id, name, salary, clientId);
            }

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
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
                    + "(name, salary, client_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
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
                    + "name = ?, salary = ?, client_id = ? WHERE id = ?");
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

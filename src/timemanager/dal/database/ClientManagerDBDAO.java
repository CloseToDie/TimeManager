package timemanager.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import timemanager.be.Client;
import timemanager.dal.ClientManagerFacade;

/**
 *
 * @author andreasvillumsen
 */
public class ClientManagerDBDAO implements ClientManagerFacade{
    private final MSSQLDatabaseConnector dbCon;

    /**
     * ClientManagerDBDAO Constructor
     * @throws java.io.IOException
     */
    public ClientManagerDBDAO() throws IOException {
        dbCon = new MSSQLDatabaseConnector();
    }

    /**
     * Get a list of all clients
     * @return clients
     */
    @Override
    public ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();

        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM clients");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                clients.add(new Client(id, name));
            }
            return clients;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Get a client
     * @param client
     * @return client
     */
    @Override
    public Client getClient(Client client) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM clients WHERE id = ?");
            ps.setInt(1, client.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                return new Client(id, name);
            }

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Store a new client to the database
     * @param client
     * @return boolean of success
     */
    @Override
    public boolean storeClient(Client client) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO client "
                    + "(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getName());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                client.setId((int) rs.getLong(1));
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
     * Update a given client's info in the database
     * @param client
     * @return boolean of success
     */
    @Override
    public boolean updateClient(Client client) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE client SET "
                    + "name = ? WHERE id = ?");
            ps.setString(1, client.getName());
            ps.setInt(2, client.getId());
            
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
     * Delete a client in the database
     * @param client
     * @return boolean of success
     */
    @Override
    public boolean deleteClient(Client client) {
        try ( Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM client WHERE id = ?");
            ps.setInt(1, client.getId());
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

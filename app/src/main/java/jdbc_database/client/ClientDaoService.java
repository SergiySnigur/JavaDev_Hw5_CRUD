package jdbc_database.client;

import jdbc_database.settings.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoService {
    private PreparedStatement createClientSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement setNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement listAllSt;

    private PreparedStatement selectMaxId;

    public ClientDaoService() throws SQLException {
        Connection connection = Database.getInstance().getConnection();

//      Create client by name
        this.createClientSt = connection.prepareStatement("INSERT INTO client(name) VALUES(?)");

//      Get client by id
        this.getByIdSt = connection.prepareStatement("SELECT name FROM client WHERE id = ?");

//      Update client by id
        this.setNameSt = connection.prepareStatement("UPDATE client SET name = ? WHERE name = ?");

//      Delete client by id
        this.deleteByIdSt = connection.prepareStatement("DELETE FROM client WHERE id = ?");

//      Select all client
        this.listAllSt = connection.prepareStatement("SELECT * FROM client");

//      Select max id client
        this.selectMaxId = connection.prepareStatement("SELECT max(id) AS maxId FROM client");
    }

    public long create(String name) {

        if (name.length() <= 2) {
            throw new IllegalArgumentException("Name is short!");
        }
        if (name.length() >= 1000) {
            throw new IllegalArgumentException("Name is long!");
        }

        long id = 0;
        try {
            this.createClientSt.setString(1, name);
            this.createClientSt.executeUpdate();

            try (ResultSet rs = this.createClientSt.getGeneratedKeys()) {
                rs.next();
                id = rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getById(long id) {
        String result = "";
        try {
            this.getByIdSt.setLong(1, id);
            try (ResultSet rs = this.getByIdSt.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                Client client = new Client();
                client.setId(id);
                client.setName(rs.getString("name"));
                result = client.getName();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void setName(long id, String name) {
        if (name.length() <= 2) {
            throw new IllegalArgumentException("Name is short!");
        }
        if (name.length() >= 1000) {
            throw new IllegalArgumentException("Name is long!");
        }

        try {
            this.setNameSt.setLong(1, id);
            this.setNameSt.setString(2, name);
            this.setNameSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try {
            this.deleteByIdSt.setLong(1, id);
            this.deleteByIdSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();

        try (ResultSet rs = this.listAllSt.executeQuery()) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                clients.add(client);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clients;
    }
}

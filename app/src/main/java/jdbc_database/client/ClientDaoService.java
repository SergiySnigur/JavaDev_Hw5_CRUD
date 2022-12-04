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

    public long create(String name) throws SQLException {

        if (name.length() <= 2) {
            throw new IllegalArgumentException("Name is short!");
        }
        if (name.length() >= 1000) {
            throw new IllegalArgumentException("Name is long!");
        }

        this.createClientSt.setString(1, name);
        this.createClientSt.executeUpdate();

        long id;
        try (ResultSet rs = this.selectMaxId.executeQuery();) {
            rs.next();
            id = rs.getLong("maxId");
        }

        return id;
    }

    public String getById(long id) throws SQLException {

        this.getByIdSt.setLong(1, id);
        String result;

        try (ResultSet rs = this.getByIdSt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            Client client = new Client();
            client.setId(id);
            client.setName(rs.getString("name"));
            result = client.getName();
            return result;
        }
    }

    public void setName(long id, String name) throws SQLException {

        if (name.length() <= 2) {
            throw new IllegalArgumentException("Name is short!");
        }
        if (name.length() >= 1000) {
            throw new IllegalArgumentException("Name is long!");
        }

        this.setNameSt.setLong(1, id);
        this.setNameSt.setString(2, name);
        this.setNameSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        this.deleteByIdSt.setLong(1, id);
        this.deleteByIdSt.executeUpdate();
    }

    public List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try (ResultSet rs = this.listAllSt.executeQuery()) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                clients.add(client);
            }
        }

        return clients;
    }
}

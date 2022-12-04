
package jdbc_database;

import jdbc_database.client.ClientDaoService;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        ClientDaoService clientDaoService = new ClientDaoService();
        System.out.println(clientDaoService.listAll().toString());
    }
}

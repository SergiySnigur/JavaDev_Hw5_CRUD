
package jdbc_database;

import jdbc_database.client.ClientDaoService;
import jdbc_database.db_service.DatabaseInitService;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        DatabaseInitService databaseInitService = new DatabaseInitService();
        databaseInitService.initDb();
        ClientDaoService clientDaoService = new ClientDaoService();
        System.out.println(clientDaoService.listAll().toString());
    }
}

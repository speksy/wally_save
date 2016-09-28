package wallet.main.login;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by SVT on 27.9.2016 г..
 */
public class RegisterModel {
    Connection connection;

    public RegisterModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

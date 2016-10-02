package wallet.main.login;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sveto on 9/25/2016.
 */
public class LoginModel {
    private MongoCollection collection;

    public LoginModel() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("wally");

        collection = db.getCollection("test", BasicDBObject.class);
    }

    public MongoCollection getCollection() {
        return collection;
    }
}

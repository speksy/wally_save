package wallet.main.login;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by SVT on 27.9.2016 г..
 */
public class RegisterModel {
    private MongoCollection collection;

    public RegisterModel(){
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("wally");

        collection = db.getCollection("test", BasicDBObject.class);
    }

    public MongoCollection getCollection() {
        return collection;
    }

}

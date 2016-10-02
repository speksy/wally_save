package wallet.main.login;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by sveto on 9/26/2016.
 */
public class CategoriesModel {
    private MongoCollection collection;

    public CategoriesModel() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("wally");

        collection = db.getCollection("test", BasicDBObject.class);
    }

    public MongoCollection getCollection() {
        return collection;
    }


//    public boolean hasCategory(String category) {
//        getCollection();
//        if (resultSet.next()) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }


}

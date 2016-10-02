package wallet.main.samples;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sveto on 10/1/2016.
 */
public class InsertDocumentApp {

    public static void main(String[] args) {

        try {

            MongoClient mongo = new MongoClient("localhost", 27017);
            MongoDatabase db = mongo.getDatabase("wally");

            MongoCollection collection = db.getCollection("test", BasicDBObject.class);

            // 1. BasicDBObject example
            System.out.println("BasicDBObject example...");
            BasicDBObject user_info = new BasicDBObject();
            user_info.put("username", "speksy");
            user_info.put("password", "speksy");
            user_info.put("email" , "some@some.com");
            user_info.put("age" , "24");


            BasicDBObject user_categories = new BasicDBObject();
            String categoryName = "FOOD";
            user_categories.put("category", categoryName);

            user_categories.put("cat_entry_description", "drink");
            user_categories.put("cet_entry_amount", "99");
            user_info.put("parent_category_name", user_categories);
            List<DBObject> userInfoList = new ArrayList<>();
            userInfoList.add(user_info);
            collection.insertMany(userInfoList);

            MongoCursor cursorDoc = collection.find().iterator();
            while (cursorDoc.hasNext()) {
                System.out.println(cursorDoc.next());
            }
//            collection.insert();
//            collection.remove(new BasicDBObject());
//
//            // 2. BasicDBObjectBuilder example
//            System.out.println("BasicDBObjectBuilder example...");
//            BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
//                    .add("database", "mkyongDB")
//                    .add("table", "hosting");
//
//            BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
//                    .add("records", "99")
//                    .add("index", "vps_index1")
//                    .add("active", "true");
//
//            documentBuilder.add("detail", documentBuilderDetail.get());
//
//            collection.insert(documentBuilder.get());
//
//            DBCursor cursorDocBuilder = collection.find();
//            while (cursorDocBuilder.hasNext()) {
//                System.out.println(cursorDocBuilder.next());
//            }
//
//            collection.remove(new BasicDBObject());
//
//            // 3. Map example
//            System.out.println("Map example...");
//            Map<String, Object> documentMap = new HashMap<String, Object>();
//            documentMap.put("database", "mkyongDB");
//            documentMap.put("table", "hosting");
//
//            Map<String, Object> documentMapDetail = new HashMap<String, Object>();
//            documentMapDetail.put("records", "99");
//            documentMapDetail.put("index", "vps_index1");
//            documentMapDetail.put("active", "true");
//
//            documentMap.put("detail", documentMapDetail);
//
//            collection.insert(new BasicDBObject(documentMap));
//
//            DBCursor cursorDocMap = collection.find();
//            while (cursorDocMap.hasNext()) {
//                System.out.println(cursorDocMap.next());
//            }
//
//            collection.remove(new BasicDBObject());
//
//            // 4. JSON parse example
//            System.out.println("JSON parse example...");
//
//            String json = "{'database' : 'mkyongDB','table' : 'hosting'," +
//                    "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";
//
//            DBObject dbObject = (DBObject) JSON.parse(json);
//
//            collection.insert(dbObject);
//
//            DBCursor cursorDocJSON = collection.find();
//            while (cursorDocJSON.hasNext()) {
//                System.out.println(cursorDocJSON.next());
//            }
//
//            collection.remove(new BasicDBObject());

        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}

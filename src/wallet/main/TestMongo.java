package wallet.main;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

/**
 * Created by sveto on 10/1/2016.
 */
public class TestMongo {
    public static void main (String args[]){
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("yeahMongo");

        Employee employee = new Employee();
        employee.setNo(1L);
        employee.setName("yogesh");

        DBCollection employeeCollection = null ;
        employeeCollection = (DBCollection) db.getCollection(Employee.COLLECTION_NAME);

        employeeCollection.save(employee);

        System.err.println(employeeCollection.findOne());
    }
}

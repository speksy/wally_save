package wallet.main;

import com.mongodb.BasicDBObject;

/**
 * Created by sveto on 10/1/2016.
 */
public class Employee extends BasicDBObject {

    private static final long serialVersionUID = 2105061907470199595L;
    //should be something shorter as "name" like "n"
//here just use name to conform your  sample
    public static final String NAME = "name";
    public static final String NO = "no";
    public static final String COLLECTION_NAME = "test";

    public Long getNo() {
        return getLong(NO);
    }

    public void setNo(long no) {
        put(NO, no);
    }

    public String getName() {
        return getString(NAME);
    }

    public void setName(String name) {
        put(NAME, name);
    }
}

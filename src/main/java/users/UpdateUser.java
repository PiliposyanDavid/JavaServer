package users;

import com.mongodb.*;

import java.net.UnknownHostException;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.mindrot.jbcrypt.BCrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;


public class UpdateUser {



    public static boolean isAddUser(String email, String username, String password) throws NoSuchProviderException, NoSuchAlgorithmException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        String pair = keyGen.generateKeyPair().toString();

        password = hashString(password);
        UserforJson user = new UserforJson(email, username, password, pair);
        DBObject doc = createDBObject(user);
        MongoClient mongo = null;

        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            return false;
        }

        DB db = mongo.getDB("PicsArt");
        DBCollection col = db.getCollection("Users");
        WriteResult result = col.insert(doc);
        mongo.close();
        return true;

    }

    public static UserforJson FindUserinEmail(String email) {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            return null;

        }
        UserforJson users = new UserforJson(null, null, null, null);
        DB db = mongo.getDB("PicsArt");
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        DBCollection col = db.getCollection("Users");
        DBCursor cursor = col.find(query);
        Boolean bool = false;
        bool = setParametrInUser(users, cursor, bool);
        if (bool) return users;
        return null;

    }

    public static UserforJson findUserinEmailPassword(String email, String password) {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {

            e.printStackTrace();
            return null;
        }

        UserforJson users = new UserforJson(null, null, null, null);
        DB db = mongo.getDB("PicsArt");
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        DBCollection col = db.getCollection("Users");
        DBCursor cursor = col.find(query);

        Boolean bool = false;
        bool = setParametrInUser(users, cursor, bool);
        if (bool) {
            String test_password = users.getPassword();

            if (BCrypt.checkpw(password, test_password))
                return users;

        }
        return null;
    }

    public static UserforJson findUser(String email, String username, String password) {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {

            e.printStackTrace();
            return null;
        }

        UserforJson users = new UserforJson(null, null, null, null);
        DB db = mongo.getDB("PicsArt");
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        query.put("username", username);
        DBCollection col = db.getCollection("Users");
        DBCursor cursor = col.find(query);

        Boolean bool = false;
        bool = setParametrInUser(users, cursor, bool);
        if (bool) {
            String test_password = users.getPassword();

            if (BCrypt.checkpw(password, test_password))
                return users;

        }
        return null;
    }

    public static UserforJson findUserinKey(String key) {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            return null;

        }
        UserforJson users = new UserforJson(null, null, null, null);
        DB db = mongo.getDB("PicsArt");
        BasicDBObject query = new BasicDBObject();
        query.put("key", key);
        DBCollection col = db.getCollection("Users");
        DBCursor cursor = col.find(query);
        Boolean bool = false;
        bool = setParametrInUser(users, cursor, bool);
        if (bool) return users;
        return null;
    }


    private static DBObject createDBObject(UserforJson user) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("username", user.getUsername());
        docBuilder.append("password", user.getPassword());
        docBuilder.append("email", user.getEmail());
        docBuilder.append("key", user.getKey());
        return docBuilder.get();
    }

    private static Boolean setParametrInUser(UserforJson users, DBCursor cursor, Boolean bool) {
        if (cursor.hasNext()) {

            users.setEmail((String) cursor.next().get("email"));
            users.setKey((String) cursor.curr().get("key"));
            users.setPassword((String) cursor.curr().get("password"));
            users.setUsername((String) cursor.curr().get("username"));
            bool = true;
        }
        return bool;
    }

    public static String hashString(String str) {

        String salt = BCrypt.gensalt(5);
        String hashed_string = BCrypt.hashpw(str, salt);

        return (hashed_string);
    }
}




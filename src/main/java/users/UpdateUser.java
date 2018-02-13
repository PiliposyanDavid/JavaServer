package users;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.text.Document;
import java.net.UnknownHostException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


public class UpdateUser {
//    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException, UnknownHostException {
//        changePassword("p.dzavid99@mail.ru","10137","passnew");
//        System.out.println(isFindUserInPasswordKey("5889"));
//        for(int i = 0; i < 10; i++)
//        isAddUser("p.david99@mail.ru"+i,"dav77"+i*2,"passdavid"+i*5);
//         addKeyFromPassword("p.david99@mail.ru0", "13112");
//         addKeyFromPassword("p.david99@mail.ru5", "13772");
//        addKeyFromPassword("p.david99@mail.ru7", "5572");
//
//        System.out.println(FindUserInPasswordKey("p.david99@mail.ru6", "5572").getKey());
//        System.out.println(changePassword("p.david99@mail.ru5","13772","passneww"));
//
//    }

    public static boolean isAddUser(String email, String username, String password) throws NoSuchProviderException, NoSuchAlgorithmException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        String pair = keyGen.generateKeyPair().toString();

        password = hashString(password);
        UserforJson user = new UserforJson(email, username, password, pair, null);
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
        UserforJson users = new UserforJson(null, null, null, null, null);
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

        UserforJson users = new UserforJson(null, null, null, null, null);
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

        UserforJson users = new UserforJson(null, null, null, null, null);
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
        UserforJson users = new UserforJson(null, null, null, null, null);
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

    public static void addKeyFromPassword(String email, String key_from_password) throws UnknownHostException {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            return;

        }
        UserforJson users = FindUserinEmail(email);
        DB db = mongo.getDB("PicsArt");
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        query.put("username", users.getUsername());
        query.put("password", users.getPassword());
        query.put("key", users.getKey());
        query.put("key_from_password", hashString(key_from_password));
        db.getCollection("Users").update(
                new BasicDBObject("email", email),
                new BasicDBObject("$set", new BasicDBObject(query)), true, false);
    }

    public static UserforJson FindUserInPasswordKey(String email, String key_from_password) {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            return null;

        }
        UserforJson users = new UserforJson(null, null, null, null, null);
        DB db = mongo.getDB("PicsArt");
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        DBCollection col = db.getCollection("Users");
        DBCursor cursor = col.find(query);
        Boolean bool = false;
        if (cursor.hasNext()) {

            users.setEmail((String) cursor.next().get("email"));
            users.setKey((String) cursor.curr().get("key"));
            users.setPassword((String) cursor.curr().get("password"));
            users.setUsername((String) cursor.curr().get("username"));
            users.setKey_from_password((String) cursor.curr().get("key_from_password"));
            bool = true;
        }
        if (bool) {
            String test_key_password = users.getKey_from_password();

            if (BCrypt.checkpw(key_from_password, test_key_password))
                return users;

        }
        return null;
    }


    public static Boolean changePassword(String email, String key_from_password, String new_password) {
        UserforJson users = FindUserInPasswordKey(email, key_from_password);
        if (users == null) return false;
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            return false;

        }

        new_password = hashString(new_password);
        System.out.println(new_password);
        System.out.println(users.getPassword());
        DB db = mongo.getDB("PicsArt");
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        query.put("username", users.getUsername());
        query.put("password", new_password);
        query.put("key", users.getKey());
        query.put("key_from_password",null);
        db.getCollection("Users").update(
                new BasicDBObject("email", email),
                new BasicDBObject("$set", new BasicDBObject(query)), true, false);
        return true;
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

    private static String hashString(String str) {

        String salt = BCrypt.gensalt(5);
        String hashed_string = BCrypt.hashpw(str, salt);

        return (hashed_string);
    }
}




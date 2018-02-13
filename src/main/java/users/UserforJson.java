package users;

public class UserforJson {
    String email;
    String username;
    String password;
    String key;
    String key_from_password;



    public UserforJson(String email, String username, String password, String key, String key_from_password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.key = key;
        this.key_from_password = key_from_password;

    }

    public String getKey_from_password() {
        return key_from_password;
    }

    public void setKey_from_password(String key_from_password) {
        this.key_from_password = key_from_password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

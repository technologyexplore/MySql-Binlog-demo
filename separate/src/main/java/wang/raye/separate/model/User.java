package wang.raye.separate.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体bean
 */
@Table(name="users")
public class User {
    @Id
    private int id;
    private String username;
    private String userpass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }
}

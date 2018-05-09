package de.rgse.brewlogs.domain;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User extends BaseEntity {

    private String username;
    private String email;
    private String password;
    private String avatarUrl;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPassword() {
        return password;
    }

    public Map<String,Object> toClaims() {
        Map<String, Object> result = new HashMap<>();

        result.put("username", username);
        result.put("email", email);
        result.put("avatarUrl", avatarUrl);

        return result;
    }
}

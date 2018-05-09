package de.rgse.brewlogs.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.rgse.brewlogs.domain.User;

public class UserVo {

    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    @JsonProperty
    private String avatarUrl;

    public UserVo(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.avatarUrl = user.getAvatarUrl();
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
}

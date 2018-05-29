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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public static UserVo of(User user) {
        UserVo userVo = new UserVo();
        userVo.username = user.getUsername();
        userVo.email = user.getEmail();
        userVo.avatarUrl = user.getAvatarUrl();
        return userVo;
    }
}

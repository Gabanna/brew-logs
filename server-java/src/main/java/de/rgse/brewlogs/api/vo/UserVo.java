package de.rgse.brewlogs.api.vo;

import de.rgse.brewlogs.domain.User;

public class UserVo {

    private String username;

    private String email;

    private String avatarUrl;

    public UserVo(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.avatarUrl = user.getAvatarUrl();
    }
}

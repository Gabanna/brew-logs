package de.rgse.brewlogs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class BrewLog extends BaseEntity {

    @JsonProperty
    @Column(nullable = false)
    private String name;

    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
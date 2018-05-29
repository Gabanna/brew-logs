package de.rgse.brewlogs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.rgse.brewlogs.domain.converters.LocalDateTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty
    private long id;

    @Version
    private long version;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime created;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updated;

    public long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    @PrePersist
    private void onPersist() {
        this.created = LocalDateTime.now();
        this.version = 1;
    }

    @PreUpdate
    private void onUpdate() {
        this.updated = LocalDateTime.now();
    }
}

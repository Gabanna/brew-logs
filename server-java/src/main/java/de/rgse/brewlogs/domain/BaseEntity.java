package de.rgse.brewlogs.domain;

import de.rgse.brewlogs.domain.converters.LocalDateTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
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

    @PostPersist
    private void onPersist() {
        this.created = LocalDateTime.now();
        this.version = 1;
    }

    @PreUpdate
    private void onUpdate() {
        this.updated = LocalDateTime.now();
    }
}

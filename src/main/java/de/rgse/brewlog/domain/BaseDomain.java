package de.rgse.brewlog.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseDomain<T> implements IdProvider<T> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private T id;

	@Version
	private long version;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	public T getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public Date getCreated() {
		return created;
	}

	public Date getMofified() {
		return modified;
	}

	@PrePersist
	private void prePersist() {
		created = new Date();
	}

	@PreUpdate
	private void preUpdate() {
		modified = new Date();
	}
}

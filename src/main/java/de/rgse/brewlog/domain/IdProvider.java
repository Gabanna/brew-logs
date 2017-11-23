package de.rgse.brewlog.domain;

import java.io.Serializable;

public interface IdProvider<T> extends Serializable {

	T getId();
}

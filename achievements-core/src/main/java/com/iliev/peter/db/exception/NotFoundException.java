package com.iliev.peter.db.exception;

import java.util.UUID;
import java.util.function.Predicate;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundException(final UUID uuid) {
		super(String.format("UUID %s not found", uuid.toString()));
	}

	public NotFoundException(final Predicate<?> p) {
		super(String.format("Predicate %s not found", p.toString()));
	}
}

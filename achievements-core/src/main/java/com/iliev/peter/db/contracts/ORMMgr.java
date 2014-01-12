package com.iliev.peter.db.contracts;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.exception.NotFoundException;

public interface ORMMgr<T> {

	public UUID create(final T a);

	public List<T> read(final Predicate<T> predicate) throws NotFoundException;

	public T readSingle(final Predicate<UUIDObject> predicate) throws NotFoundException;
	
	public T readSingle(final UUID uuid) throws NotFoundException;

	public void update(final UUID uuid, final T a) throws NotFoundException;

	public void delete(final UUID uuid) throws NotFoundException;
}
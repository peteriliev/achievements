package com.iliev.peter.db.contracts;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.exception.NotFoundException;
import com.iliev.peter.user.User;

public interface UserMgr extends ORMMgr<User> {

	@Override
	public UUID create(final User a);

	@Override
	public List<User> read(final Predicate<User> p) throws NotFoundException;

	@Override
	public User readSingle(final Predicate<UUIDObject> p) throws NotFoundException;

	@Override
	public void update(final UUID uuid, final User a) throws NotFoundException;

	@Override
	public void delete(final UUID uuid) throws NotFoundException;

}
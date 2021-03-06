package com.iliev.peter.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.user.User;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.contracts.UserMgr;
import com.iliev.peter.db.exception.NotFoundException;

public class MockUserManager implements UserMgr {

	private final Map<UUID, User> usersMap = new HashMap<>(64);

	@Override
	public UUID create(User a) {
		final UUID uuid = UUID.randomUUID();

		usersMap.put(uuid, User.newInstance(uuid, a.getLogin()));
		return uuid;
	}

	@Override
	public List<User> read(final Predicate<User> predicate) throws NotFoundException {
		final List<User> result = new ArrayList<>(64);

		for (final User user : usersMap.values()) {
			if (predicate.test(user)) {
				result.add(user);
			}
		}

		return result;
	}

	@Override
	public void update(final UUID uuid, final User user) throws NotFoundException {
		if (!usersMap.containsKey(uuid)) {
			throw new NotFoundException(uuid);
		}

		usersMap.remove(uuid);
		usersMap.put(uuid, user);
	}

	@Override
	public void delete(UUID uuid) throws NotFoundException {
		if (!usersMap.containsKey(uuid)) {
			throw new NotFoundException(uuid);
		}

		usersMap.remove(uuid);
	}

	@Override
	public User readSingle(Predicate<UUIDObject> p) throws NotFoundException {
		if (null == p) {
			throw new IllegalArgumentException("NULL predicate");
		}

		for (final User usr : usersMap.values()) {
			if (p.test(usr)) {
				return usr;
			}
		}

		throw new NotFoundException(p);
	}
	
	@Override
	public User readSingle(final UUID uuid) throws NotFoundException {
		final Predicate<UUIDObject> p = new Queries.ObjectByUUID(uuid);
		return readSingle(p);
	}
}
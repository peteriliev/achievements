package com.iliev.peter.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.user.User;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.contracts.UserMgr;
import com.iliev.peter.db.exception.NotFoundException;

public class MockUserManager implements UserMgr {

	private final Map<UUID, User> categoriesMap = new HashMap<>(64);

	@Override
	public UUID create(User a) {
		final UUID uuid = UUID.randomUUID();

		categoriesMap.put(uuid, User.newInstance(uuid, a.getLogin()));
		return uuid;
	}

	@Override
	public List<User> read(final Predicate<User> predicate) throws NotFoundException {
		final List<User> result = new ArrayList<>(64);

		for (final User c : categoriesMap.values()) {
			if (predicate.test(c)) {
				result.add(c);
			}
		}

		return result;
	}

	@Override
	public void update(final UUID uuid, final User a) throws NotFoundException {
		if (!categoriesMap.containsKey(uuid)) {
			throw new NotFoundException(uuid);
		}

		categoriesMap.remove(uuid);
		categoriesMap.put(uuid, a);
	}

	@Override
	public void delete(UUID uuid) throws NotFoundException {
		if (!categoriesMap.containsKey(uuid)) {
			throw new NotFoundException(uuid);
		}

		categoriesMap.remove(uuid);
	}

	@Override
	public User readSingle(Predicate<UUIDObject> p) throws NotFoundException {
		if (null == p) {
			throw new IllegalArgumentException("NULL predicate");
		}

		for (final User cat : categoriesMap.values()) {
			if (p.test(cat)) {
				return cat;
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
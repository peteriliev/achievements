package com.iliev.peter.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.Category;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.contracts.CategoryMgr;
import com.iliev.peter.db.exception.NotFoundException;

public class MockCategoryManager implements CategoryMgr {

	private final Map<UUID, Category> categoriesMap = new HashMap<>(64);

	@Override
	public UUID create(Category a) {
		final UUID uuid = UUID.randomUUID();

		categoriesMap.put(uuid, Category.newInstance(uuid, a.getParentUUID(), a.getName(), a.getDescription()));
		return uuid;
	}

	@Override
	public List<Category> read(final Predicate<Category> predicate) throws NotFoundException {
		final List<Category> result = new ArrayList<>(64);

		for (final Category c : categoriesMap.values()) {
			if (predicate.test(c)) {
				result.add(c);
			}
		}

		return result;
	}

	@Override
	public void update(final UUID uuid, final Category a) throws NotFoundException {
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
	public Category readSingle(Predicate<UUIDObject> predicate) throws NotFoundException {
		if (null == predicate) {
			throw new IllegalArgumentException("NULL predicate");
		}

		for (final Category cat : categoriesMap.values()) {
			if (predicate.test(cat)) {
				return cat;
			}
		}

		throw new NotFoundException(predicate);
	}

	@Override
	public Category readSingle(final UUID uuid) throws NotFoundException {
		final Predicate<UUIDObject> p = new Queries.ObjectByUUID(uuid);
		return readSingle(p);
	}
}
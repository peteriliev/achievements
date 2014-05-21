package com.iliev.peter.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.contracts.ARecordMgr;
import com.iliev.peter.db.exception.NotFoundException;

public class MockARecordManager implements ARecordMgr {

	private final Map<UUID, ARecord> recordsMap = new HashMap<>(64);

	@Override
	public UUID create(ARecord a) {
		final UUID uuid = UUID.randomUUID();

		// TODO: clone
		recordsMap.put(uuid, a);
		return uuid;
	}

	@Override
	public List<ARecord> read(Predicate<ARecord> predicate) {

		final List<ARecord> result = new ArrayList<>(64);

		for (final ARecord a : recordsMap.values()) {
			if (predicate.test(a)) {
				result.add(a);
			}
		}

		return result;

	}

	@Override
	public ARecord readSingle(Predicate<UUIDObject> predicate) throws NotFoundException {
		if (null == predicate) {
			throw new IllegalArgumentException("NULL predicate");
		}

		for (final ARecord aRec : recordsMap.values()) {
			if (predicate.test(aRec)) {
				return aRec;
			}
		}

		throw new NotFoundException(predicate);
	}

	@Override
	public void update(UUID uuid, ARecord a) throws NotFoundException {
		if (!recordsMap.containsKey(uuid)) {
			throw new NotFoundException(uuid);
		}

		recordsMap.remove(uuid);
		recordsMap.put(uuid, a);
	}

	@Override
	public void delete(UUID uuid) throws NotFoundException {
		if (!recordsMap.containsKey(uuid)) {
			throw new NotFoundException(uuid);
		}

		recordsMap.remove(uuid);
	}

	@Override
	public List<IAchievement> readByUser(final Predicate<ARecord> userPredicate, final List<IAchievement> allAchievements) {

		final List<IAchievement> result = new ArrayList<>(64);

		final List<ARecord> userRecords = read(userPredicate);

		for (final IAchievement a : allAchievements) {

			ARecord matchingRec = null;

			for (final ARecord ar : userRecords) {
				if (ar.getAchievementUUID().equals(a.getUUID())) {
					matchingRec = ar;
					break;
				}
			}

			if (null == matchingRec) {
				result.add(a);
			}
			// TODO:peteri
		}

		return result;
	}

	@Override
	public ARecord readSingle(final UUID uuid) throws NotFoundException {
		final Predicate<UUIDObject> p = new Queries.ObjectByUUID(uuid);
		return readSingle(p);
	}
}
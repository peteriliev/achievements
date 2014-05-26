package com.iliev.peter.db;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import predicates.AvailActions;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.AchieveWrapper;
import com.iliev.peter.achieve.Action;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.contracts.ARecordMgr;
import com.iliev.peter.db.exception.NotFoundException;

public class MockARecordManager implements ARecordMgr {

	private static final int SINGLE_RECORD = 0;
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

	public List<AchieveWrapper> readByUser2(final Predicate<ARecord> userPredicate, final List<IAchievement> currentCatAchievements) {

		// TODO:peteri - read from db join
		final List<AchieveWrapper> result = new ArrayList<>(64);

		final List<ARecord> userRecords = read(userPredicate);

		for (final IAchievement a : currentCatAchievements) {

			ARecord matchingRec = null;

			for (final ARecord ar : userRecords) {
				if (ar.getAchievementUUID().equals(a.getUUID())) {
					matchingRec = ar;
					break;
				}
			}

			final EnumSet<Action> actions = AvailActions.INSTANCE.apply(a.getType(), matchingRec != null ? matchingRec.getStatus() : null);

			final AchieveWrapper aw = AchieveWrapper.newInstance(a, matchingRec, actions);

			result.add(aw);
		}

		return result;
	}

	@Override
	public ARecord readSingle(final UUID uuid) throws NotFoundException {
		final Predicate<UUIDObject> p = new Queries.ObjectByUUID(uuid);
		return readSingle(p);
	}

	@Override
	public List<ARecord> genericRead(final Predicate<ARecord> predicate) {
		if (null == predicate) {
			throw new IllegalArgumentException("Illegal Predicate<ARecord>: NULL");
		}

		final List<ARecord> result = new ArrayList<>();

		for (final ARecord rec : recordsMap.values()) {
			if (predicate.test(rec)) {
				// TODO:peteri - clone or not ?
				result.add(rec);
			}
		}

		return result;
	}
	
	@Override
	public ARecord genericReadSingle(Predicate<ARecord> predicate) {
		final List<ARecord> list = genericRead(predicate);
		assert null != list;
		
		if (list.size() == 0)
		{
			// TODO:peteri
			return null;
		}
		
		return list.get(SINGLE_RECORD);
	}
}
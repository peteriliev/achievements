package com.iliev.peter.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.achieve.Achievement;
import com.iliev.peter.achieve.Log;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.contracts.AchievementMgr;
import com.iliev.peter.db.exception.NotFoundException;

public class MockAchievementManager implements AchievementMgr {

	private final Map<UUID, IAchievement> achivementsMap = new HashMap<>(64);

	@Override
	public UUID create(IAchievement a) {
		final UUID uuid = UUID.randomUUID();
		achivementsMap.put(uuid, Achievement.newInstance(uuid, a.getName(), a.getDescription(), a.getType(), a.getPoints(), a.getCategory()));

		return uuid;
	}

	@Override
	public List<IAchievement> read(final Predicate<IAchievement> predicate) throws NotFoundException {
		if (null == predicate) {
			throw new IllegalArgumentException("NULL predicate");
		}

		final List<IAchievement> result = new ArrayList<>(64);

		for (final IAchievement a : achivementsMap.values()) {
			if (predicate.test(a)) {
				result.add(a);
			}
		}

		return result;
	}

	@Override
	public void update(final UUID uuid, final IAchievement a) throws NotFoundException {
		if (!achivementsMap.containsKey(uuid)) {
			throw new NotFoundException(uuid);

		}
		achivementsMap.remove(uuid);
		achivementsMap.put(uuid, a);
	}

	@Override
	public void delete(UUID uuid) throws NotFoundException {
		if (!achivementsMap.containsKey(uuid)) {
			throw new NotFoundException(uuid);
		}

		achivementsMap.remove(uuid);
	}

	@Override
	public IAchievement readSingle(Predicate<UUIDObject> predicate) throws NotFoundException {
		if (null == predicate) {
			throw new IllegalArgumentException("NULL predicate");
		}

		for (final IAchievement a : achivementsMap.values()) {
			if (predicate.test(a)) {
				return a;
			}
		}

		throw new NotFoundException(predicate);
	}

	@Override
	public IAchievement readSingle(final UUID uuid) throws NotFoundException {
		final Predicate<UUIDObject> p = new Queries.ObjectByUUID(uuid);
		return readSingle(p);
	}

	private final UUID NO_ADMIN = null;

	@Override
	public void claim(final UUID usrUUID, final UUID achievementUUID, final String note) {
		// TODO:peteri - move to record manager

		// TODO:peteri - improve current date
		final Date dateEarned = new Date();
		final ARecord rec = ARecord.newDTOInstance(achievementUUID, usrUUID, dateEarned, NO_ADMIN, ARecordStatus.CLAIM);
		Initializer.aRecordMgr.create(rec);
		final Log log = Log.newInstance(rec.getUUID(), dateEarned, NO_ADMIN, usrUUID, ARecordStatus.CLAIM, note);
		Initializer.logMgr.create(log);
	}

	@Override
	public void reClaim(final UUID recordUUID, final String note) throws NotFoundException {

		internal(recordUUID, NO_ADMIN, note, ARecordStatus.RECLAIM);
	}

	@Override
	public void reject(final UUID recordUUID, final UUID adminUUID, final String note) throws NotFoundException {
		internal(recordUUID, adminUUID, note, ARecordStatus.REJECTED);
	}

	@Override
	public void approve(final UUID recordUUID, final UUID adminUUID, final String note) throws NotFoundException {
		internal(recordUUID, adminUUID, note, ARecordStatus.APPROVED);
	}

	private void internal(final UUID recordUUID, final UUID adminUUID, final String note, final ARecordStatus status) throws NotFoundException {

		// TODO:peteri - improve current date
		final Date dateEarned = new Date();

		final ARecord rec = Initializer.aRecordMgr.readSingle(recordUUID);

		final ARecord tmp = ARecord.newDTOInstance(rec.getAchievementUUID(), rec.getUserUUID(), rec.getDateEarned(), rec.getAdminUUID(), status);
		Initializer.aRecordMgr.update(rec.getUUID(), tmp);

		final Log log = Log.newInstance(rec.getUUID(), dateEarned, adminUUID, tmp.getUserUUID(), status, note);
		Initializer.logMgr.create(log);
	}
}
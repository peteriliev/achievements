package com.iliev.peter.db.contracts;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.AchieveWrapper;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.exception.NotFoundException;

public interface ARecordMgr extends ORMMgr<ARecord> {

	@Override
	public UUID create(final ARecord a);

	@Override
	public List<ARecord> read(final Predicate<ARecord> predicate);

	@Override
	public ARecord readSingle(final Predicate<UUIDObject> predicate)
			throws NotFoundException;

	@Override
	public void update(final UUID uuid, final ARecord a)
			throws NotFoundException;

	@Override
	public void delete(final UUID uuid) throws NotFoundException;

	public List<IAchievement> readByUser(final Predicate<ARecord> predicate,
			final List<IAchievement> allAchievements);

	public List<AchieveWrapper> readByUser2(final Predicate<ARecord> predicate,
			final List<IAchievement> allAchievements);
}

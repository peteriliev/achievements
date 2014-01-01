package com.iliev.peter.db.contracts;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.exception.NotFoundException;

public interface AchievementMgr extends ORMMgr<IAchievement> {

	@Override
	public UUID create(final IAchievement a);

	@Override
	public List<IAchievement> read(final Predicate<IAchievement> predicate)
			throws NotFoundException;

	@Override
	public IAchievement readSingle(final Predicate<UUIDObject> predicate)
			throws NotFoundException;

	@Override
	public void update(final UUID uuid, final IAchievement a)
			throws NotFoundException;

	@Override
	public void delete(final UUID uuid) throws NotFoundException;
}

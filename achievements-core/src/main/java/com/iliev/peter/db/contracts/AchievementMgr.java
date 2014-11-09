package com.iliev.peter.db.contracts;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.AchieveWrapper;
import com.iliev.peter.achieve.UserType;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.exception.NotFoundException;

public interface AchievementMgr extends ORMMgr<IAchievement> {

	@Override
	public UUID create(IAchievement a);

	@Override
	public List<IAchievement> read(Predicate<IAchievement> predicate) throws NotFoundException;

	@Override
	public IAchievement readSingle(Predicate<UUIDObject> predicate) throws NotFoundException;

	@Override
	public void update(UUID uuid, IAchievement a) throws NotFoundException;

	@Override
	public void delete(UUID uuid) throws NotFoundException;

	public void claim(UUID usrUUID, UUID achievementUUID, String note) throws NotFoundException;

	public void reClaim(UUID recordUUID, String note) throws NotFoundException;

	public void reject(UUID recordUUID, UUID adminUUID, String note) throws NotFoundException;

	public void approve(UUID recordUUID, UUID adminUUID, String note) throws NotFoundException;

	public List<AchieveWrapper> getMyAchievements(UUID catUUID, UUID targetUsrUUID, UserType userType) throws NotFoundException;
}
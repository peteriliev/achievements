package com.iliev.peter.achieve;

import java.util.Date;
import java.util.UUID;

import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.user.User;

public class ARecord implements IAchievement, UUIDObject {

	private final UUID uuid;
	private final UUID achievementUUID;
	private final IAchievement achievement;
	private final Date dateEarned;
	private final RecordStatus status;
	private final User user;

	private ARecord(final UUID uuid, final IAchievement a, final User user, final Date dateEarned) {
		this.uuid = uuid;
		this.achievementUUID = a.getUUID();
		this.achievement = a; // TODO:clone
		this.user = user;
		this.dateEarned = dateEarned;
		this.status = RecordStatus.CLAIM;
	}

	public static ARecord newInstance(final UUID uuid, final IAchievement a, final User user, final Date dateEarned) {
		return new ARecord(uuid, a, user, dateEarned);
	}

	@Override
	public String getName() {
		return achievement.getName();
	}

	@Override
	public String getDescription() {
		return achievement.getDescription();
	}

	@Override
	public Type getType() {
		return achievement.getType();
	}

	@Override
	public int getPoints() {
		return achievement.getPoints();
	}

	@Override
	public Category getCategory() {
		return achievement.getCategory();
	}

	public Date getDateEarned() {
		return dateEarned;
	}

	public RecordStatus getStatus() {
		return status;
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	public User getUser() {
		return user;
	}

	public UUID getAchievementUUID() {
		return achievementUUID;
	}
}
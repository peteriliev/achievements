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
	private final ARecordStatus status;
	private final User user;
	private final User admin;
	
	private static final UUID NO_UUID = null;

	private ARecord(final UUID uuid, final IAchievement a, final User user, final Date dateEarned, final User admin) {
		this.uuid = uuid;
		this.achievementUUID = a.getUUID();
		this.achievement = a; // TODO:clone
		this.user = user;
		this.dateEarned = dateEarned;
		this.status = ARecordStatus.CLAIM;
		this.admin = admin;
	}

	public static ARecord newPOInstance(final UUID uuid, final IAchievement a, final User user, final Date dateEarned, final User admin) {
		if (null == uuid)
		{
			throw new IllegalArgumentException("uuid is NULL");
		}
		
		return new ARecord(uuid, a, user, dateEarned, admin);
	}

	public static ARecord newDTOInstance(final IAchievement a, final User user, final Date dateEarned, final User admin)
	{
		return new ARecord(NO_UUID, a, user, dateEarned, admin);
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

	public ARecordStatus getStatus() {
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

	public User getAdmin() {
		return admin;
	}
}
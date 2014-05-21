package com.iliev.peter.achieve;

import java.util.Date;
import java.util.UUID;

import com.iliev.peter.contracts.UUIDObject;

public class ARecord implements UUIDObject {

	private final UUID uuid;
	private final UUID achievementUUID;
	private final Date dateEarned;
	private final ARecordStatus status;
	private final UUID userUUID;
	private final UUID adminUUID;

	private static final UUID NO_UUID = null;

	private ARecord(final UUID uuid, final UUID aUUID, final UUID user, final Date dateEarned, final UUID admin, final ARecordStatus status) {
		this.uuid = uuid;
		this.achievementUUID = aUUID;
		this.userUUID = user;
		this.dateEarned = dateEarned;
		this.status = status;
		this.adminUUID = admin;
	}

	public static ARecord newPOInstance(final UUID uuid, final UUID a, final UUID user, final Date dateEarned, final UUID admin, final ARecordStatus status) {
		if (null == uuid) {
			throw new IllegalArgumentException("uuid is NULL");
		}

		return new ARecord(uuid, a, user, dateEarned, admin, status);
	}

	public static ARecord newDTOInstance(final UUID achivementUUID, final UUID userUUID, final Date dateEarned, final UUID adminUUID, final ARecordStatus status) {

		return new ARecord(NO_UUID, achivementUUID, userUUID, dateEarned, adminUUID, status);
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	public UUID getAchievementUUID() {
		return achievementUUID;
	}

	public Date getDateEarned() {
		return dateEarned;
	}

	public ARecordStatus getStatus() {
		return status;
	}

	public UUID getUserUUID() {
		return userUUID;
	}

	public UUID getAdminUUID() {
		return adminUUID;
	}
}
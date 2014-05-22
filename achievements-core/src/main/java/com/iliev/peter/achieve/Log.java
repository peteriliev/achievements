package com.iliev.peter.achieve;

import java.util.Date;
import java.util.UUID;

import com.iliev.peter.contracts.UUIDObject;

public class Log implements UUIDObject {

	private final UUID recordUUID;
	private final Date dateEarned;
	private final UUID adminUUID;
	private final UUID usrUUID;
	private final ARecordStatus recordStatus;
	private final String note;

	@Override
	public UUID getUUID() {
		return null;
	}

	private Log(final UUID aRecordUUID, final Date dateEarned, final UUID adminUUID, final UUID usrUUID, final ARecordStatus status, final String note) {
		this.recordUUID = aRecordUUID;
		this.dateEarned = dateEarned;
		this.adminUUID = adminUUID;
		this.usrUUID = usrUUID;
		this.recordStatus = status;
		this.note = note;
	}

	public static Log newInstance(final UUID aRecordUUID, final Date dateEarned, final UUID adminUUID, final UUID usrUUID, final ARecordStatus status, final String note) {
		return new Log(aRecordUUID, dateEarned, adminUUID, usrUUID, status, note);
	}
}
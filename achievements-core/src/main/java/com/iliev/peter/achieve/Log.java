package com.iliev.peter.achieve;

import java.util.Date;
import java.util.UUID;

import com.iliev.peter.contracts.UUIDObject;

public class Log implements UUIDObject {

	private final UUID uuid;

	private Log(final UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	public static Log newInstance(final UUID aRecordUUID, final Date dateEarned, final UUID adminUUID, final UUID usrUUID, final ARecordStatus status, final String note) {
		// TODO Auto-generated method stub
		return null;
	}
}
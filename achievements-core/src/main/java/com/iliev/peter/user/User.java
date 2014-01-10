package com.iliev.peter.user;

import java.util.UUID;

import com.iliev.peter.contracts.UUIDObject;

public class User implements UUIDObject {
	private final String login;
	private final UUID uuid;

	private User(final UUID uuid, final String login) {
		this.uuid = uuid;
		this.login = login;
	}

	public static User newInstance(final UUID uuid, final String login) {
		return new User(uuid, login);
	}

	public String getLogin() {
		return login;
	}

	public UUID getUUID() {
		return uuid;
	}
}
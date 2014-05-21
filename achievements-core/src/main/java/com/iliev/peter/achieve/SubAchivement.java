package com.iliev.peter.achieve;

import java.util.UUID;

import com.iliev.peter.contracts.UUIDObject;

public class SubAchivement implements UUIDObject {

	private final UUID uuid;
	private final String name;
	private final String description;

	private SubAchivement(final UUID uuid, final String name, final String description) {

		this.uuid = uuid;
		this.name = name;
		this.description = description;
	}

	public static SubAchivement newInstance(final UUID uuid, final String name, final String description) {
		return new SubAchivement(uuid, name, description);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}
}
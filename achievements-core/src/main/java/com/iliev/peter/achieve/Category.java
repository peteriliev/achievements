package com.iliev.peter.achieve;

import java.util.UUID;

import com.iliev.peter.contracts.UUIDObject;

public class Category implements UUIDObject {
	private final String name;
	private final String description;
	private final UUID uuid;

	private Category(final String name, final String description) {
		this.uuid = null;
		this.name = name;
		this.description = description;
	}

	private Category(final UUID uuid, final String name, final String description) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
	}

	public static Category newInstance(final String name, final String description) {
		return new Category(name, description);
	}

	public static Category newInstance(final UUID uuid, final String name, final String description) {
		return new Category(uuid, name, description);
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
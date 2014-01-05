package com.iliev.peter.achieve;

import java.util.UUID;

import com.iliev.peter.contracts.UUIDObject;

public class Category implements UUIDObject, ICategory {
	private final String name;
	private final String description;
	private final UUID uuid;
	private final UUID parentUUID;

	private Category(final UUID parentUUID, final String name, final String description) {
		this.uuid = null;
		this.parentUUID = parentUUID;
		this.name = name;
		this.description = description;
	}

	private Category(final UUID uuid, final UUID parentUUID, final String name, final String description) {
		this.uuid = uuid;
		this.parentUUID = parentUUID;
		this.name = name;
		this.description = description;
	}

	public static Category newInstance(final UUID parentUUID, final String name, final String description) {
		return new Category(parentUUID, name, description);
	}

	public static Category newInstance(final UUID uuid, final UUID parentUUID, final String name, final String description) {
		return new Category(uuid, parentUUID, name, description);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public UUID getParentUUID() {
		return parentUUID;
	}
}
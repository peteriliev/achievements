package com.iliev.peter.achieve;

import java.util.UUID;

import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;

public class Achievement implements IAchievement, UUIDObject {

	private final UUID uuid;
	private final String name;
	private final String description;
	private final Type type;
	private final int points;

	private final Category category;

	private Achievement(final UUID uuid, final String name,
			final String description, final Type type, final int points,
			final Category category) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
		this.type = type;
		this.points = points;
		this.category = category;
	}

	public static Achievement newInstance(final UUID uuid, final String name,
			final String description, final Type type, final int points,
			final Category category) {
		return new Achievement(uuid, name, description, type, points, category);
	}

	public static Achievement newInstance(final String name,
			final String description, final Type type, final int points,
			final Category category) {
		return new Achievement(null, name, description, type, points, category);
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
	public Type getType() {
		return type;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public Category getCategory() {
		return category;
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}
}
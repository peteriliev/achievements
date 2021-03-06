package com.iliev.peter.achieve;

import java.util.EnumSet;
import java.util.UUID;

import com.iliev.peter.achieve.contracts.IAchievement;

public class AchieveWrapper {
	private final IAchievement achieve;
	private final ARecord record;
	private final EnumSet<com.iliev.peter.achieve.Action> actions;

	private AchieveWrapper(final IAchievement a, final ARecord rec, final EnumSet<com.iliev.peter.achieve.Action> actions) {
		this.achieve = a;
		this.record = rec;
		this.actions = actions;
	}

	public static AchieveWrapper newInstance(final IAchievement a, final ARecord rec, final EnumSet<com.iliev.peter.achieve.Action> actions) {
		// TODO:peteri - validate args
		if (null == a)
		{
			throw new IllegalArgumentException("Illegal achievement: NULL");
		}
		return new AchieveWrapper(a, rec, actions);
	}

	public int getPoints() {
		return achieve.getPoints();
	}

	public String getName() {
		return achieve.getName();
	}

	public String getDescription() {
		return achieve.getDescription();
	}

	public ARecordStatus getStatus() {
		return record == null ? ARecordStatus.NULL : record.getStatus();
	}

	public UUID getAchievementUUID() {
		return achieve.getUUID();
	}

	public Type getAchievementType() {
		return achieve.getType();
	}

	public UUID getRecordUUID() {
		if (null != record) {
			return record.getUUID();
		}
		return null;
	}

	public EnumSet<Action> getActions() {
		return actions;
	}
}
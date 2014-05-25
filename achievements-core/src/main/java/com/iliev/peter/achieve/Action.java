package com.iliev.peter.achieve;

import java.util.EnumSet;

public enum Action {
	ADMIN_APPROVE, ADMIN_REJECT, USR_CLAIM, USR_RECLAIM;

	public final static EnumSet<Action> USR_ACTIONS = EnumSet.of(Action.USR_CLAIM, Action.USR_RECLAIM);

	public final static EnumSet<Action> ADMIN_ACTIONS = EnumSet.of(Action.ADMIN_REJECT, Action.ADMIN_APPROVE);
}

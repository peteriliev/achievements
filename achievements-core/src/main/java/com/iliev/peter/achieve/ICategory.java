package com.iliev.peter.achieve;

import java.util.UUID;

public interface ICategory {

	public abstract String getName();

	public abstract String getDescription();

	public abstract UUID getUUID();

	public abstract UUID getParentUUID();

}
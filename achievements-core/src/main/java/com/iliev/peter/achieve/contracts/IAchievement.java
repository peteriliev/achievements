package com.iliev.peter.achieve.contracts;

import com.iliev.peter.achieve.Category;
import com.iliev.peter.achieve.Type;
import com.iliev.peter.contracts.UUIDObject;

public interface IAchievement extends UUIDObject {

	public abstract String getName();

	public abstract String getDescription();

	public abstract Type getType();

	public abstract int getPoints();

	public abstract Category getCategory();

}
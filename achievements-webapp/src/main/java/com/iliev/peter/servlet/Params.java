package com.iliev.peter.servlet;

public class Params {
	private Params() {
		throw new RuntimeException("Trying to instantiate Params class");
	}

	public static final String RECORD_UUID = "record_uuid";

	public static final String ADMIN_UUID = "admin_uuid";

	public static final String ACHIEVEMENT_UUID = "achievement_uuid";

	public static final String USER_UUID = "user_uuid";

	public static final String USER_TYPE = "user_type";

	public static final String NOTE = "note";

	public static final String RECORD_STATUS = "record_status";

	public static final String ACHIEVEMENT_TYPE = "achievement_type";
}

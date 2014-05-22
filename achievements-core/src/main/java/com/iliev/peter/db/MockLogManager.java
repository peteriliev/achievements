package com.iliev.peter.db;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.Log;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.contracts.LogMgr;
import com.iliev.peter.db.exception.NotFoundException;

public class MockLogManager implements LogMgr {

	@Override
	public UUID create(Log a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Log> read(Predicate<Log> predicate) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log readSingle(Predicate<UUIDObject> predicate) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log readSingle(UUID uuid) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UUID uuid, Log a) throws NotFoundException {
		
	}

	@Override
	public void delete(UUID uuid) throws NotFoundException {
		
	}
}

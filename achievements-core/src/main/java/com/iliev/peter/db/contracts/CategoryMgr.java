package com.iliev.peter.db.contracts;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.Category;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.db.exception.NotFoundException;

public interface CategoryMgr extends ORMMgr<Category> {

	@Override
	public UUID create(final Category a);

	@Override
	public List<Category> read(final Predicate<Category> p)
			throws NotFoundException;

	@Override
	public Category readSingle(final Predicate<UUIDObject> p)
			throws NotFoundException;

	@Override
	public void update(final UUID uuid, final Category a)
			throws NotFoundException;

	@Override
	public void delete(final UUID uuid) throws NotFoundException;

}
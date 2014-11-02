package com.iliev.peter.achieve;

import java.util.List;
import java.util.UUID;

public class CategoryNode implements ICategory {

	private final ICategory w;
	private final List<Category> c;

	private CategoryNode(final ICategory w, final List<Category> c) {
		// TODO:peteri - clone
		this.w = w;
		this.c = c;
	}

	public static CategoryNode newInstance(final ICategory w, final List<Category> c) {
		// TODO:peteri - validate arguments
		if (null == w) {
			throw new IllegalArgumentException("Illegal ICategory: NULL");
		}

		return new CategoryNode(w, c);
	}

	@Override
	public String getName() {
		return w.getName();
	}

	@Override
	public String getDescription() {
		return w.getDescription();
	}

	@Override
	public UUID getUUID() {
		return w.getUUID();
	}

	@Override
	public UUID getParentUUID() {
		return w.getParentUUID();
	}

	public List<Category> getChildren() {
		return c;
	}
}
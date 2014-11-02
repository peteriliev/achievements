package com.iliev.peter.db.compare;

import java.util.Comparator;

import com.iliev.peter.achieve.CategoryNode;

public class CatComparer implements Comparator<CategoryNode> {

	@Override
	public int compare(final CategoryNode c1, final CategoryNode c2) {
		return c1.getName().compareToIgnoreCase(c2.getName());
	}

	public static final Comparator<CategoryNode> INSTANCE = new CatComparer();
}

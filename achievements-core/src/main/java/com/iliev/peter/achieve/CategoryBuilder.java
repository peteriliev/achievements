package com.iliev.peter.achieve;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.iliev.peter.db.Queries;
import com.iliev.peter.db.TempDB;
import com.iliev.peter.db.compare.CatComparer;
import com.iliev.peter.db.contracts.CategoryMgr;
import com.iliev.peter.db.exception.NotFoundException;

public class CategoryBuilder {

	// TODO:inject
	private final static CategoryMgr catMgr = TempDB.cateogryMgr;

	// TODO:hanlde exception
	public final static List<CategoryNode> build() throws NotFoundException {
		final List<CategoryNode> result = new ArrayList<>();

		final List<Category> topLevelCats = catMgr.read(Queries.TOP_LEVEL_CATS);
		for (final Category tlc : topLevelCats) {
			final Predicate<Category> childCatQuery = new Queries.CatByParent(tlc.getUUID());
			final List<Category> children = catMgr.read(childCatQuery);
			final CategoryNode tln = CategoryNode.newInstance(tlc, children);

			result.add(tln);
		}

		result.sort(CatComparer.INSTANCE);

		return result;
	}
}
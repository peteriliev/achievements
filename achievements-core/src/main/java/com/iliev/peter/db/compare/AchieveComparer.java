package com.iliev.peter.db.compare;

import java.util.Comparator;

import com.iliev.peter.achieve.AchieveWrapper;

public class AchieveComparer implements Comparator<AchieveWrapper> {

	@Override
	public int compare(final AchieveWrapper a1, final AchieveWrapper a2) {
		return a1.getName().compareToIgnoreCase(a2.getName());
	}
	
	public static final Comparator<AchieveWrapper> INSTANCE = new AchieveComparer();
}

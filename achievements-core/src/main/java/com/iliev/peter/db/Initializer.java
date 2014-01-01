package com.iliev.peter.db;

import java.util.Date;
import java.util.UUID;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.Achievement;
import com.iliev.peter.achieve.Category;
import com.iliev.peter.achieve.Type;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.db.contracts.ARecordMgr;
import com.iliev.peter.db.contracts.AchievementMgr;
import com.iliev.peter.db.contracts.CategoryMgr;
import com.iliev.peter.db.contracts.UserMgr;
import com.iliev.peter.db.exception.NotFoundException;
import com.iliev.peter.user.User;

public class Initializer {

	public static final AchievementMgr achievementMgr = new MockAchievementManager();
	public static final CategoryMgr cateogryMgr = new MockCategoryManager();
	public static final ARecordMgr aRecordMgr = new MockARecordManager();
	public static final UserMgr userMgr = new MockUserManager();

	static {
		try {
			init(achievementMgr, cateogryMgr, aRecordMgr, userMgr);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void init(final AchievementMgr aMgr, final CategoryMgr cMgr, final ARecordMgr aRecordMgr, final UserMgr userMgr) throws NotFoundException {

		final Category blackCat = Category.newInstance("Black Cat", "Black cat descr");
		final Category whiteCat = Category.newInstance("White Cat", "White cat descr");
		final Category redCat = Category.newInstance("Red Cat", "Red cat descr");

		final UUID blackUUID = cMgr.create(blackCat);
		final UUID whiteUUID = cMgr.create(whiteCat);
		final UUID redUUID = cMgr.create(redCat);

		final Category blackCatPO = cMgr.readSingle(new Queries.ObjectByUUID(blackUUID));
		final Category whiteCatPO = cMgr.readSingle(new Queries.ObjectByUUID(whiteUUID));
		final Category redCatPO = cMgr.readSingle(new Queries.ObjectByUUID(redUUID));

		final IAchievement bAchievementOne = Achievement.newInstance("Black Ach. One", "Black ach. one descr", Type.REGULAR, 10, blackCatPO);
		final UUID bAchievementOneUUID = aMgr.create(bAchievementOne);
		final IAchievement bAchievementOnePO = aMgr.readSingle(new Queries.ObjectByUUID(bAchievementOneUUID));
		
		final IAchievement bAchievementTwo = Achievement.newInstance("Black Ach. Two", "Black ach. two descr", Type.REGULAR, 20, blackCatPO);
		final UUID bAchievementTwoUUID = aMgr.create(bAchievementTwo);
		final IAchievement bAchievementTwoPO = aMgr.readSingle(new Queries.ObjectByUUID(bAchievementTwoUUID));
		

		final IAchievement bAchievementThree = Achievement.newInstance("Black Ach. Three", "Black ach. three descr", Type.PROGRESS, 30, blackCatPO);
		aMgr.create(bAchievementThree);
		final IAchievement bAchievementFour = Achievement.newInstance("Black Ach. Four", "Black ach. four descr", Type.PROGRESS, 40, blackCatPO);
		aMgr.create(bAchievementFour);

		final IAchievement bAchievementFive = Achievement.newInstance("Black Ach. Five", "Black ach. five descr", Type.COMPOSITE, 50, blackCatPO);
		aMgr.create(bAchievementFive);
		final IAchievement bAchievementSix = Achievement.newInstance("Black Ach. Six", "Black ach. six descr", Type.COMPOSITE, 60, blackCatPO);
		aMgr.create(bAchievementSix);

		//
		final IAchievement wAchievementOne = Achievement.newInstance("White Ach. One", "White ach. one descr", Type.REGULAR, 70, whiteCatPO);
		aMgr.create(wAchievementOne);

		//
		final IAchievement rAchievementOne = Achievement.newInstance("Red Ach. One", "Red ach. one descr", Type.REGULAR, 80, redCatPO);
		aMgr.create(rAchievementOne);

		final User userLonestarr = User.newInstance(null, "lonestarr");
		final UUID lonestarrUUID = userMgr.create(userLonestarr);
		final User userLonestarrPO = userMgr.readSingle(new Queries.ObjectByUUID(lonestarrUUID));
	
		final User userPeteri = User.newInstance(null, "peteri");
		final UUID peteriUUID = userMgr.create(userPeteri);
		final User userPeteriPO = userMgr.readSingle(new Queries.ObjectByUUID(peteriUUID));
		
		final ARecord aRecBlackOne = ARecord.newInstance(null, bAchievementOnePO, userLonestarrPO, new Date());
		final ARecord aRecBlackTwo = ARecord.newInstance(null, bAchievementTwoPO, userPeteriPO, new Date());
		
		aRecordMgr.create(aRecBlackOne);
		aRecordMgr.create(aRecBlackTwo);
	}
}
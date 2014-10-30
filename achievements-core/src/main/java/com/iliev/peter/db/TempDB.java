package com.iliev.peter.db;

import java.util.Date;
import java.util.UUID;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.achieve.Achievement;
import com.iliev.peter.achieve.Category;
import com.iliev.peter.achieve.Type;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.db.contracts.ARecordMgr;
import com.iliev.peter.db.contracts.AchievementMgr;
import com.iliev.peter.db.contracts.CategoryMgr;
import com.iliev.peter.db.contracts.LogMgr;
import com.iliev.peter.db.contracts.UserMgr;
import com.iliev.peter.db.exception.NotFoundException;
import com.iliev.peter.user.User;

public class TempDB {

	public static final AchievementMgr achievementMgr = new MockAchievementManager();
	public static final CategoryMgr cateogryMgr = new MockCategoryManager();
	public static final ARecordMgr aRecordMgr = new MockARecordManager();
	public static final UserMgr userMgr = new MockUserManager();
	public static final LogMgr logMgr = new MockLogManager();

	static {
		try {
			init(achievementMgr, cateogryMgr, aRecordMgr, userMgr);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final UUID NO_PARENT = null;

	private static void init(final AchievementMgr aMgr, final CategoryMgr cMgr, final ARecordMgr aRecordMgr, final UserMgr userMgr) throws NotFoundException {

		final Category hyegineCat = Category.newInstance(NO_PARENT, "Hyegine", "Hyegine description");
		final Category disciplineCat = Category.newInstance(NO_PARENT, "Discipline", "Disciplne description");
		final Category athleticsCat = Category.newInstance(NO_PARENT, "Athletics", "Athletics description");
		final Category tourismCat = Category.newInstance(NO_PARENT, "Tourism", "Tourism description");
		final Category householdCat = Category.newInstance(NO_PARENT, "Household", "Household description");
		final Category englishCat = Category.newInstance(NO_PARENT, "English", "English description");
		final Category mathCat = Category.newInstance(NO_PARENT, "Math", "Math description");

		final UUID hyegineUUID = cMgr.create(hyegineCat);
		final UUID disciplineUUID = cMgr.create(disciplineCat);
		final UUID athleticsUUID = cMgr.create(athleticsCat);
		final UUID tourismUUID = cMgr.create(tourismCat);
		final UUID householdUUID = cMgr.create(householdCat);
		final UUID englishUUID = cMgr.create(englishCat);
		final UUID mathUUID = cMgr.create(mathCat);

		final Category hyegineSubCat1 = Category.newInstance(hyegineUUID, "Hyegine SCat1", "Hyegine SCat descr 1");
		cMgr.create(hyegineSubCat1);
		final Category blackSubCat2 = Category.newInstance(hyegineUUID, "Hyegine SCat2", "Hyegine SCat descr 2");
		cMgr.create(blackSubCat2);
		final Category blackSubCat3 = Category.newInstance(hyegineUUID, "Hyegine SCat3", "Hyegine SCat descr 3");
		cMgr.create(blackSubCat3);

		final Category whiteSubCat1 = Category.newInstance(disciplineUUID, "White SCat1", "White scat descr 1");
		cMgr.create(whiteSubCat1);
		final Category whiteSubCat2 = Category.newInstance(disciplineUUID, "White SCat2", "White scat descr 2");
		cMgr.create(whiteSubCat2);
		final Category whiteSubCat3 = Category.newInstance(disciplineUUID, "White SCat3", "White scat descr 3");
		cMgr.create(whiteSubCat3);
		final Category whiteSubCat4 = Category.newInstance(disciplineUUID, "White SCat4", "White scat descr 4");
		cMgr.create(whiteSubCat4);

		final Category redSubCat1 = Category.newInstance(athleticsUUID, "Red SCat1", "Red scat descr 1");
		cMgr.create(redSubCat1);
		final Category redSubCat2 = Category.newInstance(athleticsUUID, "Red SCat2", "Red scat descr 2");
		final UUID redSubCat2UUID = cMgr.create(redSubCat2);
		final Category redSubCat2PO = cMgr.readSingle(new Queries.ObjectByUUID(redSubCat2UUID));

		final Category blackCatPO = cMgr.readSingle(new Queries.ObjectByUUID(hyegineUUID));
		final Category whiteCatPO = cMgr.readSingle(new Queries.ObjectByUUID(disciplineUUID));
		final Category redCatPO = cMgr.readSingle(new Queries.ObjectByUUID(athleticsUUID));

		final IAchievement bAchievementOne = Achievement.newInstance("Hyegine Ach. One", "Hyegine Ach. one descr", Type.REGULAR, 10, blackCatPO);
		final UUID bAchievementOneUUID = aMgr.create(bAchievementOne);
		final IAchievement bAchievementOnePO = aMgr.readSingle(new Queries.ObjectByUUID(bAchievementOneUUID));

		final IAchievement bAchievementTwo = Achievement.newInstance("Hyegine Ach. Two", "Hyegine Ach. two descr", Type.REGULAR, 20, blackCatPO);
		final UUID bAchievementTwoUUID = aMgr.create(bAchievementTwo);
		final IAchievement bAchievementTwoPO = aMgr.readSingle(new Queries.ObjectByUUID(bAchievementTwoUUID));

		final IAchievement bAchievementThree = Achievement.newInstance("Hyegine Ach. Three", "Hyegine Ach. three descr", Type.REGULAR, 30, blackCatPO);
		aMgr.create(bAchievementThree);
		final IAchievement bAchievementFour = Achievement.newInstance("Hyegine Ach. Four", "Hyegine Ach. four descr", Type.REGULAR, 40, blackCatPO);
		aMgr.create(bAchievementFour);

		final IAchievement bAchievementFive = Achievement.newInstance("Hyegine Ach. Five", "Hyegine Ach. five descr", Type.REGULAR, 50, blackCatPO);
		aMgr.create(bAchievementFive);
		final IAchievement bAchievementSix = Achievement.newInstance("Hyegine Ach. Six", "Hyegine Ach. six descr", Type.REGULAR, 60, blackCatPO);
		aMgr.create(bAchievementSix);

		//
		final IAchievement wAchievementOne = Achievement.newInstance("White Ach. One", "White ach. one descr", Type.REGULAR, 70, whiteCatPO);
		aMgr.create(wAchievementOne);

		//
		final IAchievement rAchievementOne = Achievement.newInstance("Red Ach. One", "Red ach. one descr", Type.REGULAR, 80, redCatPO);
		aMgr.create(rAchievementOne);

		final IAchievement rSAchievementOne = Achievement.newInstance("Red S-Ach. One", "Red s-ach. one descr", Type.REGULAR, 90, redSubCat2PO);
		aMgr.create(rSAchievementOne);
		final IAchievement rSAchievementTwo = Achievement.newInstance("Red S-Ach. Two", "Red s-ach. two descr", Type.REGULAR, 100, redSubCat2PO);
		aMgr.create(rSAchievementTwo);
		final IAchievement rSAchievementThree = Achievement.newInstance("Red S-Ach. Thre", "Red s-ach. three descr", Type.REGULAR, 110, redSubCat2PO);
		aMgr.create(rSAchievementThree);

		final User userLonestarr = User.newInstance(null, "lonestarr");
		final UUID lonestarrUUID = userMgr.create(userLonestarr);
		final User userLonestarrPO = userMgr.readSingle(new Queries.ObjectByUUID(lonestarrUUID));

		final User userPeteri = User.newInstance(null, "peteri");
		final UUID peteriUUID = userMgr.create(userPeteri);
		final User userPeteriPO = userMgr.readSingle(new Queries.ObjectByUUID(peteriUUID));

		final User userAdmin = User.newInstance(null, "admin");
		final UUID adminUUID = userMgr.create(userAdmin);
		final User userAdminPO = userMgr.readSingle(new Queries.ObjectByUUID(adminUUID));

		final ARecord aRecBlackOne = ARecord.newDTOInstance(bAchievementOnePO.getUUID(), userLonestarrPO.getUUID(), new Date(), userAdminPO.getUUID(), ARecordStatus.APPROVED);
		final ARecord aRecBlackTwo = ARecord.newDTOInstance(bAchievementTwoPO.getUUID(), userPeteriPO.getUUID(), new Date(), userAdminPO.getUUID(), ARecordStatus.APPROVED);

		aRecordMgr.create(aRecBlackOne);
		aRecordMgr.create(aRecBlackTwo);
	}
}
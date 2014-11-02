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

		final Category hyegineCat = Category.newInstance(NO_PARENT, "1. Hyegine", "Hyegine description");
		final Category disciplineCat = Category.newInstance(NO_PARENT, "2. Discipline", "Disciplne description");
		final Category athleticsCat = Category.newInstance(NO_PARENT, "3. Athletics", "Athletics description");
		final Category tourismCat = Category.newInstance(NO_PARENT, "4. Tourism", "Tourism description");
		final Category householdCat = Category.newInstance(NO_PARENT, "5. Household", "Household description");
		final Category englishCat = Category.newInstance(NO_PARENT, "6. English", "English description");
		final Category mathCat = Category.newInstance(NO_PARENT, "7. Math", "Math description");

		final UUID hyegineUUID = cMgr.create(hyegineCat);
		final UUID disciplineUUID = cMgr.create(disciplineCat);
		final UUID athleticsUUID = cMgr.create(athleticsCat);
		final UUID tourismUUID = cMgr.create(tourismCat);
		final UUID householdUUID = cMgr.create(householdCat);
		final UUID englishUUID = cMgr.create(englishCat);
		final UUID mathUUID = cMgr.create(mathCat);

		final Category hyegineSubCat1 = Category.newInstance(hyegineUUID, "1. Hyegine SCat1", "Hyegine SCat descr 1");
		cMgr.create(hyegineSubCat1);
		final Category blackSubCat2 = Category.newInstance(hyegineUUID, "2. Hyegine SCat2", "Hyegine SCat descr 2");
		cMgr.create(blackSubCat2);
		final Category blackSubCat3 = Category.newInstance(hyegineUUID, "3. Hyegine SCat3", "Hyegine SCat descr 3");
		cMgr.create(blackSubCat3);

		final Category whiteSubCat1 = Category.newInstance(disciplineUUID, "1. White SCat1", "White scat descr 1");
		cMgr.create(whiteSubCat1);
		final Category whiteSubCat2 = Category.newInstance(disciplineUUID, "2. White SCat2", "White scat descr 2");
		cMgr.create(whiteSubCat2);
		final Category whiteSubCat3 = Category.newInstance(disciplineUUID, "3. White SCat3", "White scat descr 3");
		cMgr.create(whiteSubCat3);
		final Category whiteSubCat4 = Category.newInstance(disciplineUUID, "4. White SCat4", "White scat descr 4");
		cMgr.create(whiteSubCat4);

		final Category redSubCat1 = Category.newInstance(athleticsUUID, "1. Red SCat1", "Red scat descr 1");
		cMgr.create(redSubCat1);
		final Category redSubCat2 = Category.newInstance(athleticsUUID, "2. Red SCat2", "Red scat descr 2");
		final UUID redSubCat2UUID = cMgr.create(redSubCat2);
		final Category redSubCat2PO = cMgr.readSingle(new Queries.ObjectByUUID(redSubCat2UUID));

		final Category blackCatPO = cMgr.readSingle(new Queries.ObjectByUUID(hyegineUUID));
		final Category whiteCatPO = cMgr.readSingle(new Queries.ObjectByUUID(disciplineUUID));
		final Category redCatPO = cMgr.readSingle(new Queries.ObjectByUUID(athleticsUUID));

		final IAchievement bAchievementOne = Achievement.newInstance("1. Hyegine Ach. One", "Hyegine Ach. one descr", Type.REGULAR, 10, blackCatPO);
		final UUID bAchievementOneUUID = aMgr.create(bAchievementOne);
		final IAchievement bAchievementOnePO = aMgr.readSingle(new Queries.ObjectByUUID(bAchievementOneUUID));

		final IAchievement bAchievementTwo = Achievement.newInstance("2. Hyegine Ach. Two", "Hyegine Ach. two descr", Type.REGULAR, 20, blackCatPO);
		final UUID bAchievementTwoUUID = aMgr.create(bAchievementTwo);
		final IAchievement bAchievementTwoPO = aMgr.readSingle(new Queries.ObjectByUUID(bAchievementTwoUUID));

		final IAchievement bAchievementThree = Achievement.newInstance("3. Hyegine Ach. Three", "Hyegine Ach. three descr", Type.REGULAR, 30, blackCatPO);
		final UUID bAchievementThreeUUID = aMgr.create(bAchievementThree);
		final IAchievement bAchievementThreePO = aMgr.readSingle(new Queries.ObjectByUUID(bAchievementThreeUUID));

		final IAchievement bAchievementFour = Achievement.newInstance("4. Hyegine Ach. Four", "Hyegine Ach. four descr", Type.REGULAR, 40, blackCatPO);
		final UUID bAchievementFourUUID = aMgr.create(bAchievementFour);
		final IAchievement bAchievementFourPO = aMgr.readSingle(new Queries.ObjectByUUID(bAchievementFourUUID));

		final IAchievement bAchievementFive = Achievement.newInstance("5. Hyegine Ach. Five", "Hyegine Ach. five descr", Type.REGULAR, 50, blackCatPO);
		aMgr.create(bAchievementFive);
		final IAchievement bAchievementSix = Achievement.newInstance("6. Hyegine Ach. Six", "Hyegine Ach. six descr", Type.REGULAR, 60, blackCatPO);
		aMgr.create(bAchievementSix);

		//
		final IAchievement wAchievementOne = Achievement.newInstance("1. White Ach. One", "White ach. one descr", Type.REGULAR, 70, whiteCatPO);
		aMgr.create(wAchievementOne);

		//
		final IAchievement rAchievementOne = Achievement.newInstance("2. Red Ach. One", "Red ach. one descr", Type.REGULAR, 80, redCatPO);
		aMgr.create(rAchievementOne);

		final IAchievement rSAchievementOne = Achievement.newInstance("1. Red S-Ach. One", "Red s-ach. one descr", Type.REGULAR, 90, redSubCat2PO);
		aMgr.create(rSAchievementOne);
		final IAchievement rSAchievementTwo = Achievement.newInstance("2. Red S-Ach. Two", "Red s-ach. two descr", Type.REGULAR, 100, redSubCat2PO);
		aMgr.create(rSAchievementTwo);
		final IAchievement rSAchievementThree = Achievement.newInstance("3. Red S-Ach. Thre", "Red s-ach. three descr", Type.REGULAR, 110, redSubCat2PO);
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

		final ARecord aRecBlackOne = ARecord.newDTOInstance(bAchievementOnePO.getUUID(), userLonestarrPO.getUUID(), new Date(), userAdminPO.getUUID(), ARecordStatus.CLAIM);
		final ARecord aRecBlackTwo = ARecord.newDTOInstance(bAchievementTwoPO.getUUID(), userLonestarrPO.getUUID(), new Date(), userAdminPO.getUUID(), ARecordStatus.RECLAIM);
		final ARecord aRecBlackThree = ARecord.newDTOInstance(bAchievementThreePO.getUUID(), userLonestarrPO.getUUID(), new Date(), userAdminPO.getUUID(), ARecordStatus.APPROVED);
		final ARecord aRecBlackFour = ARecord.newDTOInstance(bAchievementFourPO.getUUID(), userLonestarrPO.getUUID(), new Date(), userAdminPO.getUUID(), ARecordStatus.REJECTED);

		final ARecord aRecBlackOnePI = ARecord.newDTOInstance(bAchievementOnePO.getUUID(), userPeteriPO.getUUID(), new Date(), userAdminPO.getUUID(), ARecordStatus.APPROVED);

		aRecordMgr.create(aRecBlackOne);
		aRecordMgr.create(aRecBlackTwo);
		aRecordMgr.create(aRecBlackThree);
		aRecordMgr.create(aRecBlackFour);

		aRecordMgr.create(aRecBlackOnePI);
	}
}
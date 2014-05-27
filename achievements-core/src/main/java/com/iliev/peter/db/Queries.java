package com.iliev.peter.db;

import java.util.UUID;
import java.util.function.Predicate;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.Category;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.contracts.UUIDObject;
import com.iliev.peter.user.User;

public class Queries {
	public static final Predicate<Category> SELECT_ALL = new Predicate<Category>() {

		@Override
		public boolean test(Category cat) {
			if (null == cat) {
				return false;
			}

			return true;
		}
	};

	public static final Predicate<IAchievement> SELECT_ALL_ACHIEVEMENTS = new Predicate<IAchievement>() {

		@Override
		public boolean test(final IAchievement a) {
			if (null == a) {
				return false;
			}

			return true;
		}
	};

	public static class ObjectByUUID implements Predicate<UUIDObject> {

		private final UUID uuid;

		public ObjectByUUID(final UUID uuid) {
			this.uuid = uuid;
		}

		@Override
		public boolean test(final UUIDObject obj) {
			if (null == obj) {
				return false;
			}

			return this.uuid.equals(obj.getUUID());
		}
	}

	public static class AchieveByCat implements Predicate<IAchievement> {

		private final UUID catUUID;

		public AchieveByCat(final UUID uuid) {
			this.catUUID = uuid;
		}

		@Override
		public boolean test(final IAchievement achieve) {
			if (null == achieve) {
				return false;
			}

			return this.catUUID.equals(achieve.getCategory().getUUID());
		}
	}

	public static class ARecordByUser implements Predicate<ARecord> {

		private final UUID userUUID;

		public ARecordByUser(final UUID userUUID) {
			this.userUUID = userUUID;
		}

		@Override
		public boolean test(final ARecord aRecord) {
			if (null == aRecord) {
				return false;
			}

			return this.userUUID.equals(aRecord.getUserUUID());
		}
	}

	public static class UserByLogin implements Predicate<User> {

		private final String login;

		public UserByLogin(final String login) {
			this.login = login;
		}

		@Override
		public boolean test(final User aRecord) {
			if (null == aRecord) {
				return false;
			}

			return this.login.equalsIgnoreCase(aRecord.getLogin());
		}
	}

	public static class CatByParent implements Predicate<Category> {

		private final UUID uuid;

		public CatByParent(final UUID cat) {
			this.uuid = cat;
		}

		@Override
		public boolean test(final Category cat) {
			if (null == cat) {
				return false;
			}

			return this.uuid.equals(cat.getParentUUID());
		}
	}

	public static final Predicate<Category> TOP_LEVEL_CATS = new Predicate<Category>() {

		@Override
		public boolean test(final Category c) {
			if (null == c) {
				return false;
			}
			return c.getParentUUID() == null;
		}
	};

	public static final Predicate<User> ALL_TARGET_USERS = new Predicate<User>() {

		@Override
		public boolean test(final User c) {
			// TODO:peteri
			final Predicate<User> p1 = new Queries.UserByLogin("peteri");
			final Predicate<User> p2 = new Queries.UserByLogin("lonestarr");
			final Predicate<User> composite = p1.or(p2);

			return composite.test(c);
		}
	};

	public static class REC_BY_USR_AND_ACHIEVE implements Predicate<ARecord> {
		private final UUID user_uuid;
		private final UUID achieve_uuid;

		private REC_BY_USR_AND_ACHIEVE(final UUID usrUUID, final UUID achieveUUID) {
			this.user_uuid = usrUUID;
			this.achieve_uuid = achieveUUID;
		}

		public static Predicate<ARecord> newInstance(final UUID usrUUID, final UUID achieveUUID) {
			if (null == usrUUID) {
				throw new IllegalArgumentException("Invalid usrUUID: NULL");
			}
			if (null == achieveUUID) {
				throw new IllegalArgumentException("Invalid achieveUUID: NULL");
			}

			return new REC_BY_USR_AND_ACHIEVE(usrUUID, achieveUUID);
		}

		@Override
		public boolean test(final ARecord rec) {
			if (null == rec) {
				return false;
			}

			return this.user_uuid.equals(rec.getUserUUID()) && this.achieve_uuid.equals(rec.getAchievementUUID());
		}
	}
}
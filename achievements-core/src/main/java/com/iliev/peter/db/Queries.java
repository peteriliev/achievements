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

	public static class ARecordByUserAndCat implements Predicate<ARecord> {

		private final UUID userUUID;

		public ARecordByUserAndCat(final UUID userUUID) {
			this.userUUID = userUUID;
		}

		@Override
		public boolean test(final ARecord aRecord) {
			if (null == aRecord) {
				return false;
			}

			return this.userUUID.equals(aRecord.getUser().getUUID());
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
}

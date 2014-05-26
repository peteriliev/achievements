package predicates;

import java.util.EnumSet;
import java.util.function.BiFunction;

import com.iliev.peter.achieve.Action;
import com.iliev.peter.achieve.UserType;

public class AvailActionsByUsrType implements BiFunction<UserType, EnumSet<Action>, EnumSet<Action>> {

	private AvailActionsByUsrType() {

	}

	public static final BiFunction<UserType, EnumSet<Action>, EnumSet<Action>> INSTANCE = new AvailActionsByUsrType();

	@Override
	public EnumSet<Action> apply(final UserType userType, final EnumSet<Action> actions) {
		if (null == userType) {
			throw new IllegalArgumentException("Illegal user: NULL");
		}

		final EnumSet<Action> result = EnumSet.noneOf(Action.class);

		for (final Action a : actions) {
			if (UserType.ADMIN.equals(userType) && Action.ADMIN_ACTIONS.contains(a)) {
				result.add(a);
			}

			if (UserType.REGULAR.equals(userType) && Action.USR_ACTIONS.contains(a)) {
				result.add(a);
			}
		}

		return result;
	}
}

package predicates;

import java.util.EnumSet;
import java.util.function.BiFunction;

import com.iliev.peter.achieve.Action;
import com.iliev.peter.user.User;

public class AvailActionsByUsrType implements BiFunction<User, EnumSet<Action>, EnumSet<Action>> {

	private AvailActionsByUsrType() {

	}

	public static final BiFunction<User, EnumSet<Action>, EnumSet<Action>> INSTANCE = new AvailActionsByUsrType();

	@Override
	public EnumSet<Action> apply(final User usr, final EnumSet<Action> actions) {
		if (null == usr) {
			throw new IllegalArgumentException("Illegal user: NULL");
		}

		final EnumSet<Action> result = EnumSet.noneOf(Action.class);

		for (final Action a : actions) {
			if (usr.isAdmin() && Action.ADMIN_ACTIONS.contains(a)) {
				result.add(a);
			}

			if (!usr.isAdmin() && Action.USR_ACTIONS.contains(a)) {
				result.add(a);
			}
		}

		return result;
	}
}

package predicates;

import java.util.EnumSet;
import java.util.function.BiFunction;

import com.iliev.peter.achieve.Action;
import com.iliev.peter.user.User;


public class AvailActionsByUsrType implements BiFunction<User, EnumSet<Action>, EnumSet<Action>> {

	@Override
	public EnumSet<Action> apply(final User usr, final EnumSet<Action> u) {
		// TODO Auto-generated method stub
		return null;
	}
}

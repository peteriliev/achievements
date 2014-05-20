package predicates;

import java.util.EnumSet;
import java.util.function.BiFunction;

import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.achieve.Action;
import com.iliev.peter.achieve.Type;

public class AvailActions
		implements
		BiFunction<com.iliev.peter.achieve.Type, ARecordStatus, EnumSet<Action>> {

	@Override
	public EnumSet<Action> apply(final Type t, final ARecordStatus u) {
		
		if (!Type.REGULAR.equals(t))
		{
			// TODO:peteri - handle progress <code>Achievements</code>
			return EnumSet.noneOf(Action.class);
		}
		
		if (ARecordStatus.NULL.equals(u)) {
			return EnumSet.of(Action.USR_CLAIM);
		}

		if (ARecordStatus.REJECTED.equals(u)) {
			return EnumSet.of(Action.USR_CLAIM);
		}

		if (ARecordStatus.CLAIM.equals(u)) {
			return EnumSet.of(Action.ADMIN_APPROVE, Action.ADMIN_REJECT);
		}

		if (ARecordStatus.APPROVED.equals(u)) {
			return EnumSet.noneOf(Action.class);
		}

		// TODO:peteri - log warning
		return EnumSet.noneOf(Action.class);
	}
}

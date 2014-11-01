package predicates;

import java.util.EnumSet;
import java.util.function.BiFunction;

import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.achieve.Action;
import com.iliev.peter.achieve.Type;

public class AvailActions implements BiFunction<Type, ARecordStatus, EnumSet<Action>> {

	private AvailActions() {

	}

	public static final BiFunction<Type, ARecordStatus, EnumSet<Action>> INSTANCE = new AvailActions();

	@Override
	public EnumSet<Action> apply(final Type achiementType, final ARecordStatus status) {

		if (!Type.REGULAR.equals(achiementType)) {
			// TODO:peteri - handle progress <code>Achievements</code>
			return EnumSet.noneOf(Action.class);
		}

		if (ARecordStatus.NULL.equals(status)) {
			return EnumSet.of(Action.USR_CLAIM);
		}

		if (ARecordStatus.CLAIM.equals(status)) {
			return EnumSet.of(Action.ADMIN_APPROVE, Action.ADMIN_REJECT);
		}

		if (ARecordStatus.REJECTED.equals(status)) {
			return EnumSet.of(Action.USR_RECLAIM);
		}

		if (ARecordStatus.RECLAIM.equals(status)) {
			return EnumSet.of(Action.ADMIN_APPROVE, Action.ADMIN_REJECT);
		}

		if (ARecordStatus.APPROVED.equals(status)) {
			return EnumSet.noneOf(Action.class);
		}

		// TODO:peteri - log warning
		return EnumSet.of(Action.USR_CLAIM);
	}
}
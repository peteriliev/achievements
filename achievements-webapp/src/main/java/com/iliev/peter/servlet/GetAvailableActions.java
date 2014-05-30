package com.iliev.peter.servlet;

import static com.iliev.peter.servlet.Params.*;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import predicates.AvailActions;
import predicates.AvailActionsByUsrType;

import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.achieve.Action;
import com.iliev.peter.achieve.Type;
import com.iliev.peter.achieve.UserType;

public class GetAvailableActions extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		final String achievement_type = req.getParameter(ACHIEVEMENT_TYPE);
		final String record_status = req.getParameter(RECORD_STATUS);
		final String user_type = req.getParameter(USER_TYPE);

		final Type aType = Type.valueOf(achievement_type);
		final ARecordStatus rStatus = ARecordStatus.valueOf(record_status);
		final UserType uType = UserType.valueOf(user_type);

		final EnumSet<Action> actions1 = AvailActions.INSTANCE.apply(aType, rStatus);
		final EnumSet<Action> actions2 = AvailActionsByUsrType.INSTANCE.apply(uType, actions1);

		try {
			JSONArray arrayObj = new JSONArray();

			for (final Action action : actions2) {
				arrayObj.put(String.valueOf(action));
			}

			resp.setContentType("application/json");
			resp.getWriter().print(arrayObj);

		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
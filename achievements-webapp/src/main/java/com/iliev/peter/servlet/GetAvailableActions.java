package com.iliev.peter.servlet;

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

		final String achievement_type = req.getParameter("achievement_type");
		final String record_status = req.getParameter("record_status");
		final String current_user_type = req.getParameter("current_user_type");

		final Type aType = Type.valueOf(achievement_type);
		final ARecordStatus rStatus = ARecordStatus.valueOf(record_status);
		final UserType uType = UserType.valueOf(current_user_type);

		final EnumSet<Action> act1 = AvailActions.INSTANCE.apply(aType, rStatus);
		final EnumSet<Action> act2 = AvailActionsByUsrType.INSTANCE.apply(uType, act1);

		try {
			JSONArray arrayObj = new JSONArray();

			for (final Action act : act2) {
				arrayObj.put(String.valueOf(act));
			}

			resp.setContentType("application/json");
			resp.getWriter().print(arrayObj);

		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
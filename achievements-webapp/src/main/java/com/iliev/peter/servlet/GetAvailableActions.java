package com.iliev.peter.servlet;

import java.io.IOException;
import java.util.EnumSet;
import java.util.UUID;
import java.util.function.Predicate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.achieve.Action;
import com.iliev.peter.achieve.Type;
import com.iliev.peter.achieve.UserType;
import com.iliev.peter.db.Initializer;
import com.iliev.peter.db.Queries;

import predicates.AvailActions;
import predicates.AvailActionsByUsrType;


public class GetAvailableActions extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		final String target_user = req.getParameter("target_user");
		final String current_admin = req.getParameter("current_admin");
		final String achievement_uuid = req.getParameter("achievement_uuid");
		final String achievement_type = req.getParameter("achievement_type");
		final String record_status = req.getParameter("record_status");
		final String current_user_type = req.getParameter("current_user_type");
		
		final UUID usrUUID = UUID.fromString(target_user);
		final UUID admUUID = UUID.fromString(current_admin);
		final UUID achUUID = UUID.fromString(achievement_uuid);
		final Type aType = Type.valueOf(achievement_type);
		final ARecordStatus rStatus = ARecordStatus.valueOf(record_status);
		final UserType uType = UserType.valueOf(current_user_type);
		
		final EnumSet<Action> act1 = AvailActions.INSTANCE.apply(aType, rStatus);
		final EnumSet<Action > act2 = AvailActionsByUsrType.INSTANCE.apply(uType, act1);

		try {
			//Initializer.achievementMgr.approve(UUID.fromString(record_uuid), UUID.fromString(admin_uuid), note);
		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
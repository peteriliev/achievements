package com.iliev.peter.servlet;

import static com.iliev.peter.servlet.Params.*;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.db.TempDB;

public class ClaimAchievement extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final UUID achievementUUID = Util.getUUIDParam(req, ACHIEVEMENT_UUID);
		final UUID userUUID = Util.getUUIDParam(req, USER_UUID);
		final String note = req.getParameter(NOTE);

		try {
			TempDB.achievementMgr.claim(userUUID, achievementUUID, note);

			Util.setJSONStatus(resp, ARecordStatus.CLAIM);

		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
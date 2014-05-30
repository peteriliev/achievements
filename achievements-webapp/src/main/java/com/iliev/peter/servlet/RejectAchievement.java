package com.iliev.peter.servlet;

import static com.iliev.peter.servlet.Params.*;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.db.TempDB;

public class RejectAchievement extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		final UUID recordUUID = Util.getUUIDParam(req, RECORD_UUID);
		final UUID adminUUID = Util.getUUIDParam(req, ADMIN_UUID);
		final String note = req.getParameter(NOTE);

		try {
			TempDB.achievementMgr.reject(recordUUID, adminUUID, note);

			Util.setJSONStatus(resp, ARecordStatus.REJECTED);

		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
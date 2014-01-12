package com.iliev.peter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iliev.peter.achieve.ARecord;
import com.iliev.peter.achieve.contracts.IAchievement;
import com.iliev.peter.db.Initializer;
import com.iliev.peter.user.User;

public class CreateARecord extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(req, response);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		// out.print(jsonObject);
		out.flush();
	};

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String selectedTargetUser = req
				.getParameter("selectedTargetUser");
		final String currentAdminUser = req.getParameter("currentAdminUser");
		final String auuid = req.getParameter("auuid");

		try {
			final IAchievement achi = Initializer.achievementMgr
					.readSingle(UUID.fromString(auuid));
			final User usr = Initializer.userMgr.readSingle(UUID
					.fromString(selectedTargetUser));
			final User adm = Initializer.userMgr.readSingle(UUID
					.fromString(currentAdminUser));
			final ARecord ad = ARecord.newDTOInstance(achi, usr, new Date(),
					adm);
			Initializer.aRecordMgr.create(ad);
		} catch (Exception e) {
			// TODO: handle exception
		}
		final String st = "";
	}
}
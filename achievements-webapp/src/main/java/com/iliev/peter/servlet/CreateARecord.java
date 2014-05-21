package com.iliev.peter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iliev.peter.db.Initializer;

public class CreateARecord extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(req, response);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		// out.print(jsonObject);
		out.flush();
	};

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String selectedTargetUser = req.getParameter("selectedTargetUser");
		final String currentAdminUser = req.getParameter("currentAdminUser");
		final String auuid = req.getParameter("auuid");
		final String note = "Hard-coded note";

		try {
			// final ARecord ad = ARecord.newDTOInstance(UUID.fromString(auuid),
			// UUID.fromString(selectedTargetUser), new Date(),
			// UUID.fromString(currentAdminUser));
			Initializer.achievementMgr.claim(UUID.fromString(selectedTargetUser), UUID.fromString(auuid), note);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
package com.iliev.peter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetAvailableActions extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		final String target_user = req.getParameter("target_user");
		final String current_admin = req.getParameter("current_admin");
		final String achievement_uuid = req.getParameter("achievement_uuid");

		try {
			//Initializer.achievementMgr.approve(UUID.fromString(record_uuid), UUID.fromString(admin_uuid), note);
		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
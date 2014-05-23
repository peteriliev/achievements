package com.iliev.peter.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iliev.peter.db.Initializer;

public class RejectAchievement extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String achievement_uuid = req.getParameter("achievement_uuid");
		final String user_uuid = req.getParameter("user_uuid");
		final String note = req.getParameter("note");

		try {
			Initializer.achievementMgr.claim(UUID.fromString(user_uuid), UUID.fromString(achievement_uuid), note);
		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
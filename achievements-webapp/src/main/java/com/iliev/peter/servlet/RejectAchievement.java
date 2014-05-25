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
		final String record_uuid = req.getParameter("record_uuid");
		final String admin_uuid = req.getParameter("admin_uuid");
		final String note = req.getParameter("note");

		try {
			Initializer.achievementMgr.reject(UUID.fromString(record_uuid), UUID.fromString(admin_uuid), note);
		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
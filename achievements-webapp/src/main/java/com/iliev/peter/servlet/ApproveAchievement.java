package com.iliev.peter.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.iliev.peter.achieve.ARecordStatus;
import com.iliev.peter.db.Initializer;

public class ApproveAchievement extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String record_uuid = req.getParameter("record_uuid");
		final String admin_uuid = req.getParameter("admin_uuid");
		final String note = req.getParameter("note");

		try {
			Initializer.achievementMgr.approve(UUID.fromString(record_uuid), UUID.fromString(admin_uuid), note);

			JSONObject json = new JSONObject();
			json.put("record_status", String.valueOf(ARecordStatus.APPROVED));

			resp.setContentType("application/json");
			resp.getWriter().write(json.toString());

		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}
package com.iliev.peter.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.iliev.peter.achieve.Category;
import com.iliev.peter.db.Queries;
import com.iliev.peter.db.TempDB;
import com.iliev.peter.db.exception.NotFoundException;
import com.iliev.peter.user.User;

public class ShowUserCategory extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Subject currentUser = SecurityUtils.getSubject();
		final Predicate currentUserPrincipal = new Queries.UserByLogin(currentUser.getPrincipal().toString());
		User currentAdmin = null;
		try {
			currentAdmin = TempDB.userMgr.readSingle(currentUserPrincipal);
		} catch (NotFoundException e1) {
			e1.printStackTrace();
		}

		List<User> allTargetUsers = null;
		try {
			allTargetUsers = TempDB.userMgr.read(Queries.ALL_TARGET_USERS);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		UUID catUUID = null;
		try {
			catUUID = UUID.fromString(req.getParameter("catUUID"));

		} catch (Exception e) {
			resp.sendRedirect("/achievements-webapp/UserDashboard.jsp");
		}

		UUID targetUsrUUID = null;
		try {
			if (null == targetUsrUUID && null != req.getParameter("targetUsrUUID")) {
				targetUsrUUID = UUID.fromString(req.getParameter("targetUsrUUID"));
			}
			if (null == targetUsrUUID && currentAdmin.isAdmin() && allTargetUsers.size() > 0) {
				targetUsrUUID = allTargetUsers.get(0).getUUID();
			}
		} catch (Exception e) {
		}

		final PageMetadata pageMetadata = new PageMetadata();
		String headerText = "";
		try {
			final Category selectedCat = TempDB.cateogryMgr.readSingle(new Queries.ObjectByUUID(catUUID));
			headerText = selectedCat.getName();
			pageMetadata.setTitle(headerText);

		} catch (Exception exc) {

		}

		req.setAttribute("pageMetadata", pageMetadata);

		RequestDispatcher view = req.getRequestDispatcher("/UserCategory.jsp");
		view.forward(req, resp);
	}
}
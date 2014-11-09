package com.iliev.peter.servlet;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.iliev.peter.achieve.CategoryBuilder;
import com.iliev.peter.achieve.CategoryNode;
import com.iliev.peter.db.Queries;
import com.iliev.peter.db.TempDB;
import com.iliev.peter.db.exception.NotFoundException;
import com.iliev.peter.user.User;

public class ShowUserDashboard extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			final Subject currentUser = SecurityUtils.getSubject();
			final Predicate currentUserPrincipal = new Queries.UserByLogin(currentUser.getPrincipal().toString());
			User currentAdmin = TempDB.userMgr.readSingle(currentUserPrincipal);
			req.setAttribute("currentAdmin", currentAdmin);
			
		} catch (NotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			final List<CategoryNode> topLevel = CategoryBuilder.build();
			req.setAttribute("topLevel", topLevel);

		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher view = req.getRequestDispatcher("/UserDashboard.jsp");
		view.forward(req, resp);
	}
}
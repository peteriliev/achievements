<%@page import="com.iliev.peter.user.User"%>
<%@page import="java.util.List"%>
<%@page import="com.iliev.peter.achieve.contracts.IAchievement"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="java.util.UUID"%>
<%@page import="com.iliev.peter.db.*"%>
<%@page import="com.iliev.peter.achieve.*"%>
<%@page import="java.util.function.Predicate"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Category</title>
	<%
		Subject currentUser = SecurityUtils.getSubject();
	
		UUID catUUID = null;
			try {
				catUUID = UUID.fromString(request.getParameter("catUUID"));
			} catch (Exception e) {
				response.sendRedirect("/achievements-webapp/UserDashboard.jsp");
			}
	%></head>

<body>
<%
	String headerText = "";
	try {
		final Category selectedCat = Initializer.cateogryMgr.readSingle(new Queries.ObjectByUUID(catUUID));
		headerText = selectedCat.getName();
		
	} catch (Exception exc) {

	}
%>

<%=currentUser.getPrincipal()%><a href="/achievements-webapp/Logout.jsp">logout</a>

<h2><%=headerText%></h2>

<ul>
<%	final List<CategoryNode> topLevel = CategoryBuilder.build();
	
	for (final CategoryNode cn : topLevel)  { %> 
			<% if(cn.getUUID().equals(catUUID)) { %>
				<li><%=cn.getName()%></li>
			<% } else { %>
				<li><a href="/achievements-webapp/UserCategory.jsp?catUUID=<%=cn.getUUID()%>"><%=cn.getName()%></a></li>
			<% } %>
	
	<% final List<Category> children = cn.getChildren();
		for (final Category child : children) { %>
			<% if(child.getUUID().equals(catUUID)) { %>
				<li>+<%=child.getName()%></li>
			<% } else { %>
				<li>+<a href="/achievements-webapp/UserCategory.jsp?catUUID=<%=child.getUUID()%>"><%=child.getName()%></a></li>
			<% } %>
		<%}%>
			
	<% } %></ul>
	

	<%
		final Predicate<IAchievement> catPredi = new Queries.AchieveByCat(catUUID);
		final List<IAchievement> allAchievements = Initializer.achievementMgr.read(catPredi);
			
			final User usr = Initializer.userMgr.read(new Queries.UserByLogin(currentUser.getPrincipal().toString())).get(0);
			final Predicate<ARecord> userPredicate = new Queries.ARecordByUserAndCat(usr.getUUID());
			final List<IAchievement> userAchievements = Initializer.aRecordMgr.readByUser(userPredicate, allAchievements);
	%>

	<ul>
	<% for (final IAchievement achieve : userAchievements)  {%>
			<li>
				<%=achieve.getName()%>=<%=achieve.getPoints()%>
				<%if (achieve instanceof ARecord) { %>
					*
				<% } %>
			</li>
	<% } %></ul></body></html>
<%@page import="org.apache.shiro.authc.UsernamePasswordToken"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ page import="com.iliev.peter.db.*" %>
<%@ page import="com.iliev.peter.achieve.*" %>
<%@ page import="java.util.function.Predicate" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Dashboard</title>
</head>
<body>

<%
	final Subject currentUser = SecurityUtils.getSubject();
%>

<%=currentUser.getPrincipal()%><a href="/achievements-webapp/Logout.jsp">logout</a>

<ul>
	<% for (final Category cat : Initializer.cateogryMgr.read(Queries.SELECT_ALL))  {%> 
		<li><a href="/achievements-webapp/UserCategory.jsp?catUUID=<%=cat.getUUID()%>"><%=cat.getName()%></a></li>
	<%}%>
</ul>

</body>
</html>
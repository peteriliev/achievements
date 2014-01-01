<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.authc.UsernamePasswordToken"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 
	final String user = request.getParameter("user");
	final String pass = request.getParameter("password");
	final Subject currentUser = SecurityUtils.getSubject();

	String errorMsg = "";
	try {
	    UsernamePasswordToken token = new UsernamePasswordToken(user, pass);
	    currentUser.login(token);
	    response.sendRedirect("/achievements-webapp/UserDashboard.jsp");

	} catch(Exception e) {
		errorMsg = e.getMessage();
	}

%>

<h4><%=errorMsg %></h4>

<form action="/achievements-webapp/Login.jsp" method="post">
	User = <input type="text" name="user">
	Pass = <input type="text" name="password">
	<input type="submit">
</form>

</body>
</html>
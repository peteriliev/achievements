<%@ page import="com.iliev.peter.db.*" %>
<%@ page import="com.iliev.peter.achieve.*" %>
<%@ page import="java.util.function.Predicate" %>
<%@ page import="java.util.List" %>
<%@page import="org.apache.shiro.authc.UsernamePasswordToken"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>User Dashboard</title>
</head>
<body>

<jsp:useBean id="currentAdmin" class="com.iliev.peter.user.User" scope="request" />

<jsp:getProperty name="currentAdmin" property="login" /><a href="/achievements-webapp/Logout.jsp">logout</a>

<ul>

	<c:forEach var="cn" items="${topLevel}" >
		<li><a href="/achievements-webapp/ShowUserCategory?catUUID=${cn.UUID}">${cn.name}</a></li>
			<c:forEach var="child" items="${cn.children}" >
				<li>+<a href="/achievements-webapp/ShowUserCategory?catUUID=${child.UUID}">${child.name}</a></li>
			</c:forEach>
	</c:forEach>
	
	</ul>
</body>
</html>
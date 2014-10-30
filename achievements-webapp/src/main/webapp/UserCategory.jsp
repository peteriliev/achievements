<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">

<%@page import="com.iliev.peter.user.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.EnumSet"%>
<%@page import="com.iliev.peter.achieve.contracts.IAchievement"%>
<%@page import="predicates.AvailActionsByUsrType"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="java.util.UUID"%>
<%@page import="com.iliev.peter.db.*"%>
<%@page import="com.iliev.peter.contracts.UUIDObject"%>
<%@page import="com.iliev.peter.achieve.*"%>
<%@page import="java.util.function.Predicate"%>
<%@page import="com.iliev.peter.db.exception.NotFoundException"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	Subject currentUser = SecurityUtils.getSubject();
	final Predicate currentUserPrincipal = new Queries.UserByLogin(currentUser.getPrincipal().toString());
	final User currentAdmin = TempDB.userMgr.readSingle(currentUserPrincipal);

	List<User> allTargetUsers = null;
	try {
		allTargetUsers = TempDB.userMgr.read(Queries.ALL_TARGET_USERS);
	} catch (NotFoundException e) {
		e.printStackTrace();
	}

	UUID catUUID = null;
	try {
		catUUID = UUID.fromString(request.getParameter("catUUID"));

	} catch (Exception e) {
		response.sendRedirect("/achievements-webapp/UserDashboard.jsp");
	}

	UUID targetUsrUUID = null;
	try {
		if (null == targetUsrUUID && null != request.getParameter("targetUsrUUID")) {
			targetUsrUUID = UUID.fromString(request.getParameter("targetUsrUUID"));
		}
		if (null == targetUsrUUID && currentAdmin.isAdmin() && allTargetUsers.size() > 0) {
			targetUsrUUID = allTargetUsers.get(0).getUUID();
		}
	} catch (Exception e) {
	}

	String headerText = "";
	try {
		final Category selectedCat = TempDB.cateogryMgr.readSingle(new Queries.ObjectByUUID(catUUID));
		headerText = selectedCat.getName();

	} catch (Exception exc) {

	}
%>

<%@ include file="includes.jsp"%>

<body class="en-us " itemscope="itemscope" itemtype="http://schema.org/WebPage">
	<div class="wrapper">

		<%@ include file="meganav.jsp"%>

		<div class="body" itemscope="itemscope" itemtype="http://schema.org/WebPageElement">
			<div class="body-top">
				<div class="body-bot">

					<div id="profile-wrapper">
						<div id="profile-header">
							<%@ include file="profileheader.jsp"%>
						</div>

						<div id="profile-left">
							<%@ include file="profilemenu.jsp"%>
						</div>

						<div id="profile-right">

							<%@ include file="achievementfilter.jsp"%>

							<%@ include file="achievementgrid.jsp"%>

						</div>

						<span class="clear"></span>
					</div>

					<span class="clear"></span>
				</div>
			</div>
			<div class="body-trail">
				<span class="clear"></span>
			</div>
		</div>

		<%@ include file="footer.jsp"%>

		<%@ include file="servicebar.jsp"%>

	</div>

	<script type="text/javascript" src="/achievements-webapp/sc2/static/js/sc2.js?v=28"></script>
	<script type="text/javascript" src="/achievements-webapp/sc2/static/local-common/js/utility/dropdown.js?v=58"></script>

	<input type="hidden" id="current_admin" value="<%=currentAdmin.getUUID()%>" />
	<input type="hidden" id="current_cat" value="<%=catUUID%>" />
	<input type="hidden" id="current_user_type" value="<%=currentAdmin.isAdmin() ? UserType.ADMIN : UserType.REGULAR%>" />

</body>
</html>
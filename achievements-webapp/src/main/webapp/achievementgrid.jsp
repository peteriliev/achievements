
<div id="achievements-wrapper">

	<%
					final UUID myUserUUID = null == targetUsrUUID ? currentAdmin.getUUID() : targetUsrUUID;
						        	
						        	final List<AchieveWrapper> userAchievements = TempDB.achievementMgr.getMyAchievements(catUUID, myUserUUID);
						        	
						        	for (final AchieveWrapper achieve : userAchievements)  {
				%>
	<div id="achievement-<%=achieve.getAchievementUUID()%>" class="achievement-block achievement achievement-large unearned" achievement_uuid="<%=achieve.getAchievementUUID()%>" achievement_type="<%=achieve.getAchievementType()%>"
		record_status="<%=achieve.getStatus()%>">
		<div class="inner">
			<div class="meta png-fix">
				<span><%=achieve.getPoints()%></span>5/18/2014
			</div>
			<div id="achievement-icon-portrait-<%=achieve.getAchievementUUID()%>" class="icon portrait-c tile-locked">
				<span id="achievement-icon-frame-<%=achieve.getAchievementUUID()%>" class="icon-frame " style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -225px -90px no-repeat; width: 45px; height: 45px;"></span> <span
					class="clear">
					<!-- -->
				</span>
			</div>
			<div class="desc">
				<span><%=achieve.getName()%>&nbsp;<span id="achievement-status-<%=achieve.getAchievementUUID()%>">&nbsp;</span><br /><%=achieve.getDescription()%> <%final EnumSet<Action> myActions = AvailActionsByUsrType.INSTANCE.apply(currentAdmin.isAdmin() ? UserType.ADMIN : UserType.REGULAR, achieve.getActions()); %>
					<ul id="achievement-action-menu-<%=achieve.getAchievementUUID()%>" class="action_menu" achievement_uuid="<%=achieve.getAchievementUUID()%>" achievement_type="<%=achieve.getAchievementType()%>" record_status="<%=achieve.getStatus()%>">
						<%if (myActions.contains(Action.USR_CLAIM)) { %>
						<li><a style="display: none" class="btn_claim" achievement_uuid="<%=achieve.getAchievementUUID()%>" achievement_type="<%=achieve.getAchievementType()%>" user_uuid="<%=currentAdmin.getUUID()%>">Claim</a></li>
						<%}%>

						<%if (myActions.contains(Action.USR_RECLAIM)) { %>
						<li><a style="display: none" class="btn_reclaim" record_uuid="<%=achieve.getRecordUUID()%>" user_uuid="<%=currentAdmin.getUUID()%>">Claim</a></li>
						<%}%>

						<%if (myActions.contains(Action.ADMIN_REJECT)) { %>
						<li><a style="display: none" class="btn_reject" record_uuid="<%=achieve.getRecordUUID()%>" admin_uuid="<%=currentAdmin.getUUID()%>">Reject</a></li>
						<%}%>

						<%if (myActions.contains(Action.ADMIN_APPROVE)) { %>
						<li><a style="display: none" class="btn_approve" achievement_uuid="<%=achieve.getAchievementUUID()%>" achievement_type="<%=achieve.getAchievementType()%>" record_uuid="<%=achieve.getRecordUUID()%>"
							admin_uuid="<%=currentAdmin.getUUID()%>">Approve</a></li>
						<%}%>
					</ul>
			</div>
			<span class="clear">
				<!-- -->
			</span>
		</div>
	</div>
	<% } %>
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="achievements-wrapper">

<c:forEach var="achieve" items="${userAchievements}" >
	<div id="achievement-${achieve.achievementUUID}" class="achievement-block achievement achievement-large unearned" achievement_uuid="${achieve.achievementUUID}" achievement_type="${achieve.achievementType}"
		record_status="${achieve.status}">
		
		<!-- TODO:peteri - remove -->
		<ul style="color: orange">
			<li>status = ${achieve.status}</li>
		</ul>
		
		<div class="inner">
			<div class="meta png-fix">
				<span>${achieve.points}</span>5/18/2014
			</div>
			<div id="achievement-icon-portrait-${achieve.achievementUUID}" class="icon portrait-c tile-locked">
				<span id="achievement-icon-frame-${achieve.achievementUUID}" class="icon-frame " style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -225px -90px no-repeat; width: 45px; height: 45px;"></span> <span
					class="clear"> <!-- -->
				</span>
			</div>
			<div class="desc">
				<span>${achieve.name}&nbsp;<span style='color:white' id="achievement-status-${achieve.achievementUUID}">&nbsp;</span><br />${achieve.description}
	
				<h2>${achieve.actions}</h2>
				
				<ul id="achievement-action-menu-${achieve.achievementUUID}" class="action_menu" achievement_uuid="${achieve.achievementUUID}" achievement_type="${achieve.achievementType}" record_status="${achieve.status}">
					<c:forEach var="action" items="${achieve.actions}" >
						<h3>my act = ${action}</h3>
						<c:if test="${action eq 'USR_CLAIM' }" >
							<li><a style="display: none" class="btn_claim" achievement_uuid="${achieve.achievementUUID}" achievement_type="${achieve.achievementType}" user_uuid="<%=currentAdmin.getUUID()%>">Claim</a></li>
						</c:if>
					</c:forEach>
				
				</ul>
				
				
			</div>
			<span class="clear"> <!-- -->
			</span>
		</div>
	</div>
</c:forEach>
</div>
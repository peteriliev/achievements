<div id="service">
	<ul class="service-bar">
		<li class="service-cell service-home"><a href="http://us.battle.net/" tabindex="50" accessKey="1" title="Battle.net Home" data-action="Battle.net Home"> </a>
			<div id="bnet-app-tooltip" style="display: none">
				<div class="tooltip-arrow"></div>
				<div class="tooltip-wrapper">
					<a href="javascript:;" class="close" rel="close" data-label="Close"></a> <span class="clear">
						<!-- -->
					</span> <a href="http://us.battle.net/app/" class="button">Learn more</a> <a href="javascript:;" rel="close" data-label="Close">No thanks</a>
				</div>
			</div></li>
		<li class="service-cell service-welcome">Welcome, <%=currentUser.getPrincipal()%>&nbsp;<a href="/achievements-webapp/Logout.jsp" tabindex="50" accesskey="2">log out</a>
		</li>

		<%
			if (currentAdmin.isAdmin()) {
		%>
		<li><select id="target_user">
				<%
					for (final User tu : allTargetUsers) {
				%>
				<option value="<%=tu.getUUID()%>" <%if (tu.getUUID().equals(targetUsrUUID)) {%> selected="selected" <%}%>>
					<%=tu.getLogin()%>
					<%
						}
					%>
				
		</select></li>
		<%
			}
		%>
		<li class="service-cell service-shop"><a href='https://us.battle.net/shop/' class="service-link" data-action="Shop">Shop</a></li>
		<li class="service-cell service-account"><a href="https://us.battle.net/account/management/" class="service-link" tabindex="50" accesskey="3" data-action="Account">Account</a></li>
		<li class="service-cell service-support service-support-enhanced"><a href="#support" class="service-link service-link-dropdown" tabindex="50" accesskey="4" id="support-link" onclick="return false" style="cursor: progress"
			rel="javascript" data-action="Support - Support">Support<span class="no-support-tickets" id="support-ticket-count"></span></a></li>
		<li class="service-cell service-explore"><a href="#explore" tabindex="50" accesskey="5" class="dropdown" id="explore-link" onclick="return false" style="cursor: progress" rel="javascript" data-action="Explore - Explore">Explore</a></li>
	</ul>

	<div id="warnings-wrapper">
		<noscript>
			<div id="javascript-warning" class="warning warning-red">
				<div class="warning-inner2">JavaScript must be enabled to use this site.</div>
			</div>
		</noscript>
	</div>
</div>
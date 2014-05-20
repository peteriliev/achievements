<%@page import="com.iliev.peter.user.User"%>
<%@page import="java.util.List"%>
<%@page import="com.iliev.peter.achieve.contracts.IAchievement"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="java.util.UUID"%>
<%@page import="com.iliev.peter.db.*"%>
<%@page import="com.iliev.peter.achieve.*"%>
<%@page import="java.util.function.Predicate"%>
<%@page import="com.iliev.peter.db.exception.NotFoundException"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">
<head xmlns:og="http://ogp.me/ns#" xmlns:fb="http://ogp.me/ns/fb#">
<meta charset="UTF-8">
<meta http-equiv="imagetoolbar" content="false" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<%
	Subject currentUser = SecurityUtils.getSubject();
	final Predicate currentAdminP = new Queries.UserByLogin(currentUser.getPrincipal().toString());
	
	final User currentAdmin = Initializer.userMgr.readSingle(currentAdminP);

	UUID catUUID = null;
		try {
			catUUID = UUID.fromString(request.getParameter("catUUID"));
		} catch (Exception e) {
			response.sendRedirect("/achievements-webapp/UserDashboard.jsp");
		}
%>

<%
	String headerText = "";
	try {
		final Category selectedCat = Initializer.cateogryMgr.readSingle(new Queries.ObjectByUUID(catUUID));
		headerText = selectedCat.getName();
		
	} catch (Exception exc) {

	}
	
	List<User> allTargetUsers = null;
	try {
		allTargetUsers  = Initializer.userMgr.read(Queries.ALL_TARGET_USERS);
	} catch (NotFoundException e) {
		e.printStackTrace();
	}
%>

<title>Постижения > Бебълсчето > <%=headerText%></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="js/jquery-2.0.3.js"></script>
<script src="js/main.js"></script>

<link rel="icon" href="/achievements-webapp/sc2/static/images/icons/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/achievements-webapp/sc2/static/images/icons/favicon.ico" type="image/x-icon" />
<link title="StarCraft® II - News" href="/achievements-webapp/sc2/en/feed/news" type="application/atom+xml" rel="alternate" />
<link rel="search" type="application/opensearchdescription+xml" href="http://us.battle.net/en-us/data/opensearch" title="Battle.net Search" />
<link rel="stylesheet" type="text/css" media="all" href="/achievements-webapp/sc2/static/local-common/css/common-game-site.css?v=58" />
<link rel="stylesheet" type="text/css" media="all" href="/achievements-webapp/sc2/static/css/legal/ratings.css?v=58-28" />
<link rel="stylesheet" type="text/css" media="all" href="/achievements-webapp/sc2/static/css/sc2.css?v=28" />
<link rel="stylesheet" type="text/css" media="all" href="/achievements-webapp/sc2/static/css/profile/profile.css?v=28" />
<script type="text/javascript" src="/achievements-webapp/sc2/static/local-common/js/third-party.js?v=58"></script>
<script type="text/javascript" src="/achievements-webapp/sc2/static/local-common/js/common-game-site.js?v=58"></script>
</head>

<body class="en-us " itemscope="itemscope" itemtype="http://schema.org/WebPage">
<div class="wrapper">
<div class="head">
<h1 class="logo">
<a href="/achievements-webapp/sc2/en/">Постижения</a>
</h1>
<div class="search-bar">
<form action="/achievements-webapp/sc2/en/search" method="get" autocomplete="off">
<div>
<input type="text" class="search-field input" name="q" id="search-field" maxlength="200" tabindex="40" alt="Search StarCraft II" value="Search StarCraft II" />
<input type="submit" class="search-button" value="" tabindex="41" />
</div>
</form>
</div>
<ul class="menu" id="menu">
<li class="menu-home" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a itemprop="url" href="/achievements-webapp/sc2/en/" class="menu-active">
<span itemprop="name">Home</span>
</a>
</li>
<li class="menu-game" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a itemprop="url" href="/achievements-webapp/sc2/en/game/">
<span itemprop="name">Game Guide</span>
</a>
</li>
<li class="menu-esports" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a itemprop="url" href="http://wcs.battle.net/achievements-webapp/sc2/en/?utm_source=battle.net&amp;utm_medium=internal&amp;utm_campaign=wcs&amp;utm_content=sc2-nav">
<span itemprop="name">eSports</span>
</a>
</li>
<li class="menu-media" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a itemprop="url" href="/achievements-webapp/sc2/en/media/">
<span itemprop="name">Media</span>
</a>
</li>
<li class="menu-forums" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a itemprop="url" href="/achievements-webapp/sc2/en/forum/">
<span itemprop="name">Forums</span>
</a>
</li>
<li class="menu-buy-now" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a itemprop="url" href="/achievements-webapp/sc2/en/buy-now/">
<span itemprop="name">Buy Now</span>
</a>
</li>
</ul>
<div class="character-card card-game-char ajax-update">
<div class="message">
<span class="player-name">
Бебълсчето
</span>
<div class="character">
<a class="character-name context-link serif name-small" href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/" rel="np">Бебълсчето</a>
<span class="achievement-score">30</span>
<span class="avatar-frame">
<img src="/achievements-webapp/sc2/portraits/0-0.jpg" class="avatar avatar-sc2" />
<span class="border"></span>
<span class="icon icon-sc2"></span>
</span>
<div id="context-1" class="ui-context">
<div class="context">
<a href="javascript:;" class="close" onclick="return CharSelect.close(this);"></a>
<div class="context-user">
	<strong>Бебълсчето</strong>
	<span>#984</span>
</div>
<div class="context-links">
<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/" title="Profile" rel="np"
class="icon-profile link-first"
>
<span class="context-icon"></span>Profile
</a>
<a href="/achievements-webapp/sc2/en/search?f=post&amp;a=Sunstriderr%23984&amp;sort=time" title="View my posts" rel="np"
class="icon-posts link-last"
>
<span class="context-icon"></span>
</a>
</div>
</div>
</div>
</div>
</div>
</div>
<ol class="ui-breadcrumb">
<li itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a href="/achievements-webapp/sc2/en/" rel="np" class="breadcrumb-arrow" itemprop="url">
<span class="breadcrumb-text" itemprop="name">Постижения</span>
</a>
</li>
<li itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/" rel="np" class="breadcrumb-arrow" itemprop="url">
<span class="breadcrumb-text" itemprop="name">Бебълсчето</span>
</a>
</li>
<li class="last" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement">
<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/achievements/category/3211278" rel="np" itemprop="url">
<span class="breadcrumb-text" itemprop="name"><%=headerText%></span>
</a>
</li>
</ol>
</div>
<div class="body" itemscope="itemscope" itemtype="http://schema.org/WebPageElement">
<div class="body-top">
<div class="body-bot">
    <div id="profile-wrapper">
        <div id="profile-header">
            <div id="portrait" onclick="Core.goTo('/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/');"> 


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/portraits/0-90.jpg') 0px 0px no-repeat; width: 90px; height: 90px;">

	</span>

				<div id="portrait-frame">&#160;</div>
				<div id="profile-point">
					0
				</div>
            </div>

			<div id="current-decals">
					<div class="current-decal" data-tooltip="Zerg Decal">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/decals/0-45.jpg') -90px -270px no-repeat; width: 45px; height: 45px;">

			<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/rewards/zerg-decals#reward-2359737029" style="height: 45px"> </a>
	</span>
					</div>

					<div class="current-decal" data-tooltip="Protoss Decal">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/decals/0-45.jpg') -45px -135px no-repeat; width: 45px; height: 45px;">

			<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/rewards/protoss-decals#reward-2009110693" style="height: 45px"> </a>
	</span>
					</div>
				
					<div class="current-decal" data-tooltip="Terran Decal">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/decals/0-45.jpg') 0px 0px no-repeat; width: 45px; height: 45px;">

			<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/rewards/terran-decals#reward-18730036" style="height: 45px"> </a>
	</span>
					</div>
			</div>
            
            <h2>
				<span class="user-name">
					
					<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/">Бебълсчето</a>
				</span>
			</h2>	
            <h3>30</h3>
        </div>

        <div id="profile-left">
            <ul id="profile-menu">
					<li>
						<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/"><span class="back"></span>Обратно към <span>Профила</span></a></li>
            	<%	final List<CategoryNode> topLevel = CategoryBuilder.build();
					CategoryNode selectedCat = null;
					for (final CategoryNode cn : topLevel)  { %> 
					<% if(cn.getUUID().equals(catUUID)) { selectedCat = cn;%>
						<li class="active"><a href="#"><%=cn.getName()%></a></li>
					<% } else { %>
						<li class=""><a href="/achievements-webapp/UserCategory.jsp?catUUID=<%=cn.getUUID()%>"><%=cn.getName()%></a></li>
					<% } } %>
			</ul>
        </div>

        <div id="profile-right">

		<div id="achievement-filters" class="data-filters">


	<div class="profile-progress">
		<div class="progress-wrapper">
			<div class="progress-bar" style="width: 24%"></div>
		</div>
			<span>30 / 125</span>
	</div>

			<div class="ui-dropdown" id="type-filter">
				<select>
					<option value="all">All</option>
					<option value="earned">Earned</option>
					<option value="unearned">Unearned</option>
				</select>
			</div>

	<span class="clear"><!-- --></span>

			<script type="text/javascript">
				$(function() {
					Filter.initialize(function(params) {
						if (params.filter) {
							Sc2.filterContent('#achievements-wrapper', 'achievement', params.filter);
							$("#type-filter select").val(params.filter);
						}
					});

					$('.ui-dropdown').dropdown({
						callback: function(dropdown, value) {
							Filter.addParam('filter', value);
							Filter.applyQuery();

							Sc2.filterContent('#achievements-wrapper', 'achievement', value);
						}
					});
				});
			</script>
		</div>

		<div id="achievements-wrapper">
		
		<%
        	final Predicate<IAchievement> catPredi = new Queries.AchieveByCat(catUUID);
			final List<IAchievement> allAchievements = Initializer.achievementMgr.read(catPredi);

			final User usr = Initializer.userMgr.read(new Queries.UserByLogin(currentUser.getPrincipal().toString())).get(0);
        	final Predicate<ARecord> userPredicate = new Queries.ARecordByUserAndCat(usr.getUUID());
        	final List<IAchievement> userAchievements = Initializer.aRecordMgr.readByUser(userPredicate, allAchievements);
		%>

		<% for (final IAchievement achieve : userAchievements)  {%>
				<% boolean isEarned = (achieve instanceof ARecord);%>
				<div id="achievement-91475035553879" class="achievement achievement-large <%if (isEarned) { %>earned<% }else{ %>unearned<% }%>">

					<div class="inner">
						<div class="meta png-fix"><span><%=achieve.getPoints()%></span>5/18/2014</div>
						<div class="icon portrait-c <%if (!isEarned) { %>tile-locked<% }%>">
							<span class="icon-frame " style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') <%if (isEarned) { %>-180px -90px<% }else{ %>-225px -90px<% }%> no-repeat; width: 45px; height: 45px;"></span>
							<span class="clear"><!-- --></span>
						</div>
						<div class="desc">
							<span><%=achieve.getName()%></span><br /><%=achieve.getDescription()%>
						</div>
						<span class="clear"><!-- --></span>
					</div>
				</div>
				
		<% } %>
		</div>
		
        </div>

	<span class="clear"><!-- --></span>
    </div>
<span class="clear"><!-- --></span>
</div>
</div>
<div class="body-trail">
<span class="clear"><!-- --></span>
</div>
</div>
<div class="foot" id="footer" itemscope="itemscope" itemtype="http://schema.org/WPFooter">
<div class="lower-footer-wrapper">
<div class="lower-footer">
<div id="copyright">
<a href="javascript:;" tabindex="100" id="change-language">
<span>Americas - English (US)</span>
</a>
<span>©2014 ILIEV Entertainment, Inc. All rights reserved</span>
<a onclick="return Core.open(this);" href="http://us.blizzard.com/company/about/termsofuse.html" tabindex="100" data-action="Footer - Terms of Use">Terms of Use</a>
<a onclick="return Core.open(this);" href="http://us.blizzard.com/company/legal/" tabindex="100" data-action="Footer - Legal">Legal</a>
<a onclick="return Core.open(this);" href="http://us.blizzard.com/company/about/privacy.html" tabindex="100" data-action="Footer - Privacy Policy">Privacy Policy</a>
<a onclick="return Core.open(this);" href="http://us.blizzard.com/company/about/infringementnotice.html" tabindex="100" data-action="Footer - Copyright Infringement">Copyright Infringement</a>
</div>
<div id="international"></div>
</div>
<div id="marketing-trackers">
<div class="marketing-cover"></div>
</div>
</div>
</div>
<div id="service">
<ul class="service-bar">
<li class="service-cell service-home">
<a href="http://us.battle.net/" tabindex="50" accessKey="1" title="Battle.net Home" data-action="Battle.net Home"> </a>
<div id="bnet-app-tooltip" style="display: none">
<div class="tooltip-arrow"></div>
<div class="tooltip-wrapper">
<a href="javascript:;" class="close" rel="close" data-label="Close"></a>
<span class="clear"><!-- --></span>
<a href="http://us.battle.net/app/" class="button">Learn more</a>
<a href="javascript:;" rel="close" data-label="Close">No thanks</a>
</div>
</div>
</li>
<li class="service-cell service-welcome">
	Welcome, <%=currentUser.getPrincipal()%>&nbsp;<a href="/achievements-webapp/Logout.jsp" tabindex="50" accesskey="2">log out</a>
</li>

<li class="service-cell service-shop">
<a href='https://us.battle.net/shop/' class="service-link" data-action="Shop">Shop</a>
</li>
<li class="service-cell service-account">
<a href="https://us.battle.net/account/management/" class="service-link" tabindex="50" accesskey="3" data-action="Account">Account</a>
</li>
<li class="service-cell service-support service-support-enhanced">
<a href="#support" class="service-link service-link-dropdown" tabindex="50" accesskey="4" id="support-link" onclick="return false" style="cursor: progress" rel="javascript" data-action="Support - Support">Support<span class="no-support-tickets" id="support-ticket-count"></span></a>
</li>
<li class="service-cell service-explore">
<a href="#explore" tabindex="50" accesskey="5" class="dropdown" id="explore-link" onclick="return false" style="cursor: progress" rel="javascript" data-action="Explore - Explore">Explore</a>
</li>
</ul>

<div id="warnings-wrapper">
	<noscript>
		<div id="javascript-warning" class="warning warning-red">
		<div class="warning-inner2">
			JavaScript must be enabled to use this site.
		</div>
		</div>
	</noscript>
</div>
</div>
</div>
<script type="text/javascript" src="/achievements-webapp/sc2/static/js/sc2.js?v=28"></script>
<script type="text/javascript" src="/achievements-webapp/sc2/static/local-common/js/utility/dropdown.js?v=58"></script>
<input type="hidden" id="current_admin" value="<%=currentAdmin.getUUID()%>"/>
</body>
</html>
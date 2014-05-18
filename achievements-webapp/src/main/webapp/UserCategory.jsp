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

<title>Постижения > Бъбълсчето > <%=headerText%></title>

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
<script type="text/javascript">
//<![CDATA[
var Core = Core || {},
Login = Login || {};
Core.staticUrl = '/achievements-webapp/sc2/static';
Core.sharedStaticUrl = '/achievements-webapp/sc2/static/local-common';
Core.baseUrl = '/achievements-webapp/sc2/en';
Core.projectUrl = '/achievements-webapp/sc2';
Core.cdnUrl = 'http://media.blizzard.com';
Core.supportUrl = 'http://us.battle.net/support/';
Core.secureSupportUrl = 'https://us.battle.net/support/';
Core.project = 'sc2';
Core.locale = 'en-us';
Core.language = 'en';
Core.region = 'us';
Core.shortDateFormat = 'MM/dd/yyyy';
Core.dateTimeFormat = 'MM/dd/yyyy hh:mm a';
Core.loggedIn = true;
Core.userAgent = 'web';
Login.embeddedUrl = 'https://us.battle.net/login/login.frag';
var Flash = Flash || {};
Flash.videoPlayer = 'http://media.blizzard.com/global-video-player/themes/achievements-webapp/sc2/video-player.swf';
Flash.videoBase = 'http://media.blizzard.com/achievements-webapp/sc2/media/videos';
Flash.ratingImage = 'http://media.blizzard.com/global-video-player/ratings/achievements-webapp/sc2/en-us.jpg';
Flash.expressInstall = 'http://media.blizzard.com/global-video-player/expressInstall.swf';
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-544112-16']);
_gaq.push(['_setDomainName', '.battle.net']);
_gaq.push(['_setAllowLinker', true]);
_gaq.push(['_trackPageview']);
//]]>
</script>
<meta property="fb:app_id" content="157509169473" />
<meta property="og:site_name" content="StarCraft II" />
<meta property="og:locale" content="en_US" />
<meta property="og:type" content="website" />
<meta property="og:url" content="http://us.battle.net/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/achievements/category/3211278" />
<meta property="og:image" content="http://media.blizzard.com/battle.net/logos/og-sc2.png" />
<meta property="og:image" content="http://media.blizzard.com/battle.net/logos/og-blizzard.png" />
<meta property="og:title" content="StarCraft II" />
<meta property="og:description" content="Mar Sara Missions - Sunstriderr - StarCraft II" />

</head>

<body class="en-us " itemscope="itemscope" itemtype="http://schema.org/WebPage">
<div class="wrapper">
<div class="head">
<h1 class="logo">
<a href="/achievements-webapp/sc2/en/">StarCraft® II</a>
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
Peter
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




		<div id="achievement-91475035553879" class="achievement achievement-large earned">

			<div class="inner">
				<div class="meta png-fix"><span>15</span>5/18/2014</div>
				<div class="icon portrait-c ">
					<span class="icon-frame " style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -180px -90px no-repeat; width: 45px; height: 45px;"></span>
					<span class="clear"><!-- --></span>
				</div>
				<div class="desc">
					<span>Миене на зъбки</span><br />Малките бебълсчета трябва да си мият зъбките редовно.
				</div>
				<span class="clear"><!-- --></span>
			</div>
		</div>




		<div id="achievement-91475035553882"
			class="achievement achievement-large earned">

			<div class="inner">
				<div class="meta png-fix">
							<span>15</span>

						5/18/2014
				</div>

				<div class="icon portrait-c ">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -315px -90px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>Миене на ръчички</span><br />
					Малките бебълсчета трябва да си мият редовно ръчичките след игра.
				</div>

	<span class="clear"><!-- --></span>

			</div>
		</div>




		<div id="achievement-91475035553888" onclick="Sc2.openAchievement('series-91475035553888', this);"
			class="achievement achievement-large locked-large unearned expandable">

			<div class="inner">
				<div class="meta png-fix">
							<span>20</span>

				</div>

				<div class="icon portrait-c ">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/2-45.jpg') -90px -225px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>Mar Sara Mastery</span><br />
					Complete all the Mar Sara mission achievements.
				</div>

	<span class="clear"><!-- --></span>

					<div class="series">
						<div class="series-content" id="series-91475035553888" style="display: none;">

								<div class="series-criteria">
									<ul>


		<li class="list-badge earned">Liberation Day</li>


		<li class="list-badge">Raynor’s Back</li>


		<li class="list-badge">Down with Mengsk</li>


		<li class="list-badge earned">The Outlaws</li>


		<li class="list-badge">Cash Reward</li>


		<li class="list-badge">Be Quick or Be Dead</li>


		<li class="list-badge">Zero Hour</li>


		<li class="list-badge">Hold the Line</li>


		<li class="list-badge">The Best Defense…</li>
									</ul>
								</div>


								<div class="series-rewards">


			<div id="reward-414497095"
				class="series-tile tile-locked"
				data-tooltip="Portrait: Adjutant">

				<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/rewards/#reward-414497095">
					<span class="portrait-c">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/portraits/0-45.jpg') -90px -90px no-repeat; width: 45px; height: 45px;">

	</span>

	<span class="clear"><!-- --></span>
					</span>
					<span class="reward-star">&#160;</span>
				</a>
			</div>


			<div id="reward-414497095"
				class="series-tile tile-locked"
				data-tooltip="Portrait: Adjutant">

				<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/rewards/#reward-414497095">
					<span class="portrait-c">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/portraits/0-45.jpg') -90px -90px no-repeat; width: 45px; height: 45px;">

	</span>

	<span class="clear"><!-- --></span>
					</span>
					<span class="reward-star">&#160;</span>
				</a>
			</div>
								</div>

	<span class="clear"><!-- --></span>
						</div>

						<div class="series-bar">
								<div class="series-unlocked">2</div>

							<div class="series-expander plus">&#160;</div>


	<span class="clear"><!-- --></span>
						</div>
					</div>
			</div>
		</div>




		<div id="achievement-91475035553880"
			class="achievement achievement-large locked-large unearned">

			<div class="inner">
				<div class="meta png-fix">
							<span>10</span>

				</div>

				<div class="icon portrait-c tile-locked">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -225px -90px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>Raynor’s Back</span><br />
					Kill 5 enemy units in the “Liberation Day” mission with Raynor on Normal difficulty.
				</div>

	<span class="clear"><!-- --></span>

			</div>
		</div>




		<div id="achievement-91475035553881"
			class="achievement achievement-large locked-large unearned">

			<div class="inner">
				<div class="meta png-fix">
							<span>10</span>

				</div>

				<div class="icon portrait-c tile-locked">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -270px -90px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>Down with Mengsk</span><br />
					Kill every enemy unit in the “Liberation Day” mission on Hard difficulty.
				</div>

	<span class="clear"><!-- --></span>

			</div>
		</div>




		<div id="achievement-91475035553883"
			class="achievement achievement-large locked-large unearned">

			<div class="inner">
				<div class="meta png-fix">
							<span>10</span>

				</div>

				<div class="icon portrait-c tile-locked">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -0px -135px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>Cash Reward</span><br />
					Collect all Mineral and Gas Pallet pickups in “The Outlaws” mission on Normal difficulty.
				</div>

	<span class="clear"><!-- --></span>

			</div>
		</div>




		<div id="achievement-91475035553884"
			class="achievement achievement-large locked-large unearned">

			<div class="inner">
				<div class="meta png-fix">
							<span>10</span>

				</div>

				<div class="icon portrait-c tile-locked">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -360px -90px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>Be Quick or Be Dead</span><br />
					Complete “The Outlaws” mission on Hard difficulty in less than 10 minutes.
				</div>

	<span class="clear"><!-- --></span>

			</div>
		</div>




		<div id="achievement-91475035553885"
			class="achievement achievement-large locked-large unearned">

			<div class="inner">
				<div class="meta png-fix">
							<span>15</span>

				</div>

				<div class="icon portrait-c tile-locked">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -45px -135px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>Zero Hour</span><br />
					Complete all mission objectives in the “Zero Hour” mission.
				</div>

	<span class="clear"><!-- --></span>

			</div>
		</div>




		<div id="achievement-91475035553886"
			class="achievement achievement-large locked-large unearned">

			<div class="inner">
				<div class="meta png-fix">
							<span>10</span>

				</div>

				<div class="icon portrait-c tile-locked">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -90px -135px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>Hold the Line</span><br />
					Complete the “Zero Hour” mission on Normal difficulty without losing or salvaging a structure.
				</div>

	<span class="clear"><!-- --></span>

			</div>
		</div>




		<div id="achievement-91475035553887"
			class="achievement achievement-large locked-large unearned">

			<div class="inner">
				<div class="meta png-fix">
							<span>10</span>

				</div>

				<div class="icon portrait-c tile-locked">


	<span class="icon-frame "
		style="background: url('/achievements-webapp/sc2/achievements/8-45.jpg') -135px -135px no-repeat; width: 45px; height: 45px;">

	</span>
	<span class="clear"><!-- --></span>
				</div>

				<div class="desc">
					<span>The Best Defense…</span><br />
					Destroy 4 Zerg Hatcheries in the “Zero Hour” mission on Hard difficulty.
				</div>

	<span class="clear"><!-- --></span>

			</div>
		</div>

		</div>
		
        </div>

	<span class="clear"><!-- --></span>
    </div>
<span class="clear"><!-- --></span>
<a href="javascript:;" class="chat-gem" id="chat-gem"></a>
</div>
</div>
<div class="body-trail">
<span class="clear"><!-- --></span>
</div>
</div>
<div class="foot" id="footer" itemscope="itemscope" itemtype="http://schema.org/WPFooter">
<div id="sitemap">
<div class="column">
<h3 class="bnet">
<a href="http://us.battle.net/" tabindex="100" data-action="Battle.net Home - Battle.net Home">Battle.net Home</a>
</h3>
<ul>
<li><a href="http://us.battle.net/what-is/" data-action="Battle.net Home - What is Battle.net?">What is Battle.net?</a></li>
<li><a href="https://us.battle.net/shop/" data-action="Battle.net Home - Shop">Shop</a></li>
<li><a href="https://us.battle.net/account/management/" data-action="Battle.net Home - Account">Account</a></li>
<li><a href="http://us.battle.net/support/" data-action="Battle.net Home - Support">Support</a></li>
<li><a href="http://us.battle.net/realid/" data-action="Battle.net Home - Real ID">Real ID</a></li>
<li><a href="http://us.battle.net/battletag/" data-action="Battle.net Home - BattleTag">BattleTag</a></li>
</ul>
</div>
<div class="column">
<h3 class="games">
<a href="http://us.battle.net/" tabindex="100" data-action="Games - Games">Games</a>
</h3>
<ul>
<li><a href="http://us.battle.net/achievements-webapp/sc2/" data-action="Games - StarCraft® II">StarCraft® II</a></li>
<li><a href="http://us.battle.net/wow/" data-action="Games - World of Warcraft®">World of Warcraft®</a></li>
<li><a href="http://us.battle.net/d3/" data-action="Games - Diablo® III">Diablo® III</a></li>
<li><a href="http://us.battle.net/hearthstone/" data-action="Games - Hearthstone™">Hearthstone™</a></li>
<li><a href="http://us.battle.net/games/classic" data-action="Games - Classic Games">Classic Games</a></li>
<li><a href="https://us.battle.net/account/download/" data-action="Games - Game Client Downloads">Game Client Downloads</a></li>
</ul>
</div>
<div class="column">
<h3 class="account">
<a href="https://us.battle.net/account/management/" tabindex="100">Account</a>
</h3>
<ul>
<li><a href="https://us.battle.net/account/support/login-support.html" data-action="Account - Can’t log in?">Can’t log in?</a></li>
<li><a href="https://us.battle.net/account/creation/tos.html" data-action="Account - Create Account">Create Account</a></li>
<li><a href="https://us.battle.net/account/management/" data-action="Account - Account Summary">Account Summary</a></li>
<li><a href="http://us.battle.net/security" data-action="Account - Account Security">Account Security</a></li>
<li><a href="https://us.battle.net/account/management/claim-code.html" data-action="Account - Redeem Codes">Redeem Codes</a></li>
</ul>
</div>
<div class="column">
<h3 class="support">
<a href="http://us.battle.net/support/" tabindex="100" data-action="Support - Support">Support</a>
</h3>
<ul>
<li><a href="http://us.battle.net/support/" data-action="Support - Support Articles">Support Articles</a></li>
<li><a href="https://us.battle.net/account/parental-controls/index.html" data-action="Support - Parental Controls">Parental Controls</a></li>
<li><a href="http://us.battle.net/security/" data-action="Support - Protect Your Account">Protect Your Account</a></li>
<li><a href="http://us.battle.net/security/help" data-action="Support - Help! I got hacked!">Help! I got hacked!</a></li>
</ul>
</div>
<div class="column">
<h3 class="jobs">
<a href="http://jobs.blizzard.com/?utm_campaign=Blizzard_Jobs&amp;utm_source=Battle_net&amp;utm_medium=Footer">Work At Blizzard</a>
</h3>
<ul>
<li><a href="http://blizzard.com/company/careers/directory.html" data-action="Jobs - Search for Jobs">Search for Jobs</a></li>
<li><a href="https://blizzard.taleo.net/careersection/2/profile.ftl" data-action="Jobs - Candidate Profile">Candidate Profile</a></li>
<li><a href="http://blizzard.com/company/careers/university.html" data-action="Jobs - University Relations">University Relations</a></li>
<li><a href="http://blizzard.com/company/careers/form/support.html" data-action="Jobs - Job Site Support">Job Site Support</a></li>
<li><a href="http://blizzard.com/company/careers/faq.html" data-action="Jobs - Jobs FAQ">Jobs FAQ</a></li>
</ul>
</div>
<span class="clear"><!-- --></span>
</div>
<div class="lower-footer-wrapper">
<div class="lower-footer">
<div id="copyright">
<a href="javascript:;" tabindex="100" id="change-language">
<span>Americas - English (US)</span>
</a>
<span>©2014 Blizzard Entertainment, Inc. All rights reserved</span>
<a onclick="return Core.open(this);" href="http://us.blizzard.com/company/about/termsofuse.html" tabindex="100" data-action="Footer - Terms of Use">Terms of Use</a>
<a onclick="return Core.open(this);" href="http://us.blizzard.com/company/legal/" tabindex="100" data-action="Footer - Legal">Legal</a>
<a onclick="return Core.open(this);" href="http://us.blizzard.com/company/about/privacy.html" tabindex="100" data-action="Footer - Privacy Policy">Privacy Policy</a>
<a onclick="return Core.open(this);" href="http://us.blizzard.com/company/about/infringementnotice.html" tabindex="100" data-action="Footer - Copyright Infringement">Copyright Infringement</a>
</div>
<div id="international"></div>
<div id="legal">
<div id="legal-ratings" class="us png-fix">
<a rel="nofollow" class="truste-link" href="//privacy-policy.truste.com/click-with-confidence/ctv/en/us.battle.net/seal_m" target="_blank">
<img class="legal-image" src="/achievements-webapp/sc2/static/local-common/images/legal/us/truste.png?v=58-28" alt="Validate TRUSTe privacy certification"/>
</a>
<!-- legal:true us-en-us id:STARCRAFT_II_HEART_OF_THE_SWARM ratings:true -->
<div class="product-rating esrb-rating clearfix">
<a href="http://www.esrb.org/ratings/ratings_guide.jsp" tabindex="1" rel="external" target="_blank">
<img class="esrb-logo" src="/achievements-webapp/sc2/static/images/legal/ratings/esrb/en/t.png?v=58-28"
alt="ESRB Content Rating: T (Teen)" title="" width="65" height="97" />
</a>
<span class="esrb-descriptor"><span class="esrb-descriptor-label" title="Depictions of blood or the mutilation of body parts.">Blood and Gore</span></span>
<span class="esrb-descriptor"><span class="esrb-descriptor-label" title="Mild to moderate use of profanity.">Language</span></span>
<span class="esrb-descriptor"><span class="esrb-descriptor-label" title="Mild provocative references or materials.">Suggestive Themes</span></span>
<span class="esrb-descriptor"><span class="esrb-descriptor-label" title="The consumption of alcoholic beverages.">Use of Alcohol</span></span>
<span class="esrb-descriptor"><span class="esrb-descriptor-label" title="The consumption of tobacco products.">Use of Tobacco</span></span>
<span class="esrb-descriptor"><span class="esrb-descriptor-label" title="Scenes involving aggressive conflict. May contain bloodless dismemberment.">Violence</span></span>
<span class="esrb-disclaimer">Online Interactions Not Rated by the ESRB</span>
</div>
</div>
<div id="blizzard" class="png-fix">
<a href="http://us.blizzard.com/" tabindex="100"><img src="/achievements-webapp/sc2/static/images/logos/blizzard.png?v=58-28" alt="" /></a>
</div>
<script type="text/javascript">
//<![CDATA[
Feedback.showForm = false;
if (window.location.protocol === 'http:') {
Feedback.showForm = true;
}
//]]>
</script>
<div class="common-feedback-buttons" style="display: none;">
<a id="open-feedback-form" href="javascript:void(0);" class="feedback feedback-suggestion-open"><span class="feedback-icon"></span>Send Website Feedback</a>
<a id="open-error-form" href="javascript:void(0);" class="error feedback-error-open"><span class="feedback-icon"></span>Report Website Error</a>
</div>
<script type="text/javascript">
//<![CDATA[
if (Feedback.showForm) {
$('.common-feedback-buttons').css('display', 'block');
Feedback.initialize();
Feedback.titleWebsiteSuggestion = 'Send Website Feedback';
Feedback.titleWebsiteFeedback = 'Report Website Error';
Feedback.pageReferring = 'http://us.battle.net/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/achievements/category/3211278';
if (Feedback.pageReferring === '') {
Feedback.pageReferring = window.location.href;
}
Feedback.feedbackError = 'Error';
Feedback.introError = 'Found a problem with our sites? Let us know!';
Feedback.introFeedback = 'Send your website suggestions and ideas directly to Battle.net!';
}
//]]>
</script>
<span class="clear"><!-- --></span>
</div>
</div>
<div id="marketing-trackers">
<div class="marketing-cover"></div>
</div>
</div>
<script type="text/javascript">
//<![CDATA[
$(function() {
var category = Core.project + ' - Footer Navigation';
Core.bindTrackEvent('#sitemap a', category);
Core.bindTrackEvent('#copyright a[data-action]', category);
});
//]]>
</script>
</div>
<div id="service">
<ul class="service-bar">
<li class="service-cell service-home">
<a href="http://us.battle.net/" tabindex="50" accessKey="1" title="Battle.net Home" data-action="Battle.net Home"> </a>
<div id="bnet-app-tooltip" style="display: none">
<div class="tooltip-arrow"></div>
<div class="tooltip-wrapper">
<a href="javascript:;" class="close" rel="close" data-label="Close"></a>
<div class="content">
<h3 class="sub-title">Launcher Update</h3>
<h2 class="title">Get the Desktop App for Battle.net Now</h2>
<ul>
<li>All your games in 1 place</li>
<li>Log in once</li>
<li>Automatic game updates</li>
</ul>
</div>
<span class="clear"><!-- --></span>
<a href="http://us.battle.net/app/" class="button">Learn more</a>
<a href="javascript:;" rel="close" data-label="Close">No thanks</a>
</div>
</div>
<script type="text/javascript">
//<![CDATA[
Core.showUntilClosed('#bnet-app-tooltip', 'bnet.app.tooltip', {
startDate: '2013/09/07',
endDate: '2013/11/07',
cookieParams: { expires: 720 }
});
//]]>
</script>
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
<div class="support-menu" id="support-menu" style="display:none;">
<div class="support-primary">
<ul class="support-nav">
<li>
<a href="http://us.battle.net/support/" tabindex="55" class="support-category" id="support-nav-kb" data-action="Support - Knowledge Center">
<strong class="support-caption">Knowledge Center</strong>
Browse our support articles
</a>
</li>
<li>
<a href="https://us.battle.net/support/ticket/status" tabindex="55" class="support-category" id="support-nav-tickets" data-action="Support - Your Support Tickets">
<strong class="support-caption">Your Support Tickets</strong>
View your active support tickets.
</a>
<div class="ticket-summary" id="ticket-summary" data-action="Support - Support Ticket Status Changed"></div>
</li>
</ul>
<span class="clear"><!-- --></span>
</div>
<div class="support-secondary"></div>
</div>
</li>
<li class="service-cell service-explore">
<a href="#explore" tabindex="50" accesskey="5" class="dropdown" id="explore-link" onclick="return false" style="cursor: progress" rel="javascript" data-action="Explore - Explore">Explore</a>
<div class="explore-menu" id="explore-menu" style="display:none;">
<div class="explore-primary">
<ul class="explore-nav">
<li>
<a href="http://us.battle.net/" tabindex="55" data-action="Explore - Battle.net Home">
<strong class="explore-caption">Battle.net Home</strong>
</a>
</li>
<li>
<a href="https://us.battle.net/shop/" tabindex="55" data-action="Explore - Shop">
<strong class="explore-caption">Shop</strong>
</a>
</li>
<li>
<a href="https://us.battle.net/account/management/" tabindex="55" data-action="Explore - Account">
<strong class="explore-caption">Account</strong>
</a>
</li>
<li>
<a href="http://us.battle.net/support/" tabindex="55" data-action="Explore - Support">
<strong class="explore-caption">Support</strong>
</a>
</li>
</ul>
<div class="explore-links">
<h2 class="explore-caption">More</h2>
<ul>
<li><a href="http://us.battle.net/what-is/" tabindex="55" data-action="Explore - What is Battle.net?">What is Battle.net?</a></li>
<li><a href="https://us.battle.net/account/parental-controls/index.html" tabindex="55" data-action="Explore - Parental Controls">Parental Controls</a></li>
<li><a href="http://us.battle.net/security/" tabindex="55" data-action="Explore - Account Security">Account Security</a></li>
<li><a href="http://jobs.blizzard.com/?utm_campaign=Blizzard_Jobs&amp;utm_source=Battle_net&amp;utm_medium=Explore" tabindex="55" data-action="Explore - Work At Blizzard">Work At Blizzard</a></li>
<li><a href="https://us.battle.net/account/support/password-reset.html" tabindex="55" data-action="Explore - Can’t log in?">Can’t log in?</a></li>
<li><a href="https://us.battle.net/account/download/" tabindex="55" data-action="Explore - Game Client Downloads">Game Client Downloads</a></li>
<li><a href="https://us.battle.net/account/management/claim-code.html" tabindex="55" data-action="Explore - Redeem Codes">Redeem Codes</a></li>
<li><a href="http://us.battle.net/games/classic" tabindex="55" data-action="Explore - Classic Games">Classic Games</a></li>
</ul>
</div>
<span class="clear"><!-- --></span>
</div>
<ul class="explore-secondary">
<li class="explore-game explore-game-wow">
<a href="http://us.battle.net/wow/" tabindex="55" data-action="Explore - wow">
<span class="explore-game-inner">
<strong class="explore-caption">World of Warcraft®</strong>
</span>
</a>
</li>
<li class="explore-game explore-game-d3">
<a href="http://us.battle.net/d3/" tabindex="55" data-action="Explore - d3">
<span class="explore-game-inner">
<strong class="explore-caption">Diablo® III</strong>
</span>
</a>
</li>
<li class="explore-game explore-game-sc2">
<a href="http://us.battle.net/achievements-webapp/sc2/" tabindex="55" data-action="Explore - sc2">
<span class="explore-game-inner">
<strong class="explore-caption">StarCraft® II</strong>
</span>
</a>
</li>
<li class="explore-game explore-game-hs">
<a href="http://us.battle.net/hearthstone/" tabindex="55" data-action="Explore - hs">
<span class="explore-game-inner">
<strong class="explore-caption">Hearthstone™</strong>
</span>
</a>
</li>
<li class="explore-game explore-game-heroes">
<a href="http://www.heroesofthestorm.com/en-us" tabindex="55" data-action="Explore - heroes">
<span class="explore-game-inner">
<strong class="explore-caption">Heroes of the Storm™</strong>
</span>
</a>
</li>
</ul>
</div>
</li>
</ul>
<div id="warnings-wrapper">
<!--[if lt IE 8]> <div id="browser-warning" class="warning warning-red">
<div class="warning-inner2">
You are using an outdated web browser.<br />
<a href="http://us.blizzard.com/support/article/browserupdate">Upgrade</a> or <a href="http://www.google.com/chromeframe/?hl=en-US" id="chrome-frame-link">install Google Chrome Frame</a>.
<a href="#close" class="warning-close" onclick="App.closeWarning('#browser-warning', 'browserWarning'); return false;"></a>
</div>
</div>
<![endif]-->
<!--[if lt IE 8]> <script type="text/javascript" src="/achievements-webapp/sc2/static/local-common/js/third-party/CFInstall.min.js?v=58"></script>
<script type="text/javascript">
//<![CDATA[
$(function() {
var age = 365 * 24 * 60 * 60 * 1000;
var src = 'https://www.google.com/chromeframe/?hl=en-US';
if ('http:' == document.location.protocol) {
src = 'http://www.google.com/chromeframe/?hl=en-US';
}
document.cookie = "disableGCFCheck=0;path=/;max-age="+age;
$('#chrome-frame-link').bind({
'click': function() {
App.closeWarning('#browser-warning');
CFInstall.check({
mode: 'overlay',
url: src
});
return false;
}
});
});
//]]>
</script>
<![endif]-->
<noscript>
<div id="javascript-warning" class="warning warning-red">
<div class="warning-inner2">
JavaScript must be enabled to use this site.
</div>
</div>
</noscript>
</div>
<script type="text/javascript">
//<![CDATA[
$(function() {
var category = Core.project + ' - GNB';
Core.bindTrackEvent('#service .service-home a', category);
Core.bindTrackEvent('#service .service-account a', category);
Core.bindTrackEvent('#service .service-shop a', category);
Core.bindTrackEvent('#support-link', category);
Core.bindTrackEvent('#support-nav-kb', category);
Core.bindTrackEvent('#support-nav-tickets', category);
Core.bindTrackEvent('#ticket-summary', category);
Core.bindTrackEvent('#explore-link', category);
Core.bindTrackEvent('.explore-nav li a', category);
});
//]]>
</script>
</div>
</div>
<script>
//<![CDATA[
var xsToken = 'c5c193f1-435d-44e5-a3b4-29c2421e6301';
var supportToken = 'A063393B9D86287E007A5A92C00AC4F283565CE6';
var jsonSearchHandlerUrl = '//us.battle.net';
var Msg = Msg || {};
Msg.support = {
ticketNew: 'Ticket {0} was created.',
ticketStatus: 'Ticket {0}’s status changed to {1}.',
ticketOpen: 'Open',
ticketAnswered: 'Answered',
ticketResolved: 'Resolved',
ticketCanceled: 'Canceled',
ticketArchived: 'Archived',
ticketInfo: 'Need Info',
ticketAll: 'View All Tickets'
};
Msg.cms = {
requestError: 'Your request cannot be completed.',
ignoreNot: 'Not ignoring this user',
ignoreAlready: 'Already ignoring this user',
stickyRequested: 'Sticky requested',
stickyHasBeenRequested: 'You have already sent a sticky request for this topic.',
postAdded: 'Post added to tracker',
postRemoved: 'Post removed from tracker',
userAdded: 'User added to tracker',
userRemoved: 'User removed from tracker',
validationError: 'A required field is incomplete',
characterExceed: 'The post body exceeds XXXXXX characters.',
searchFor: "Search for",
searchTags: "Articles tagged:",
characterAjaxError: "You may have become logged out. Please refresh the page and try again.",
ilvl: "Level {0}",
shortQuery: "Search requests must be at least three characters long.",
editSuccess: "Success. Reload?",
postDelete: "Are you sure you want to delete this post?",
throttleError: "You must wait before you can post again."
};
Msg.bml= {
bold: 'Bold',
italics: 'Italics',
underline: 'Underline',
list: 'Unordered List',
listItem: 'List Item',
quote: 'Quote',
quoteBy: 'Posted by {0}',
unformat: 'Remove Formating',
cleanup: 'Fix Linebreaks',
code: 'Code Blocks',
item: 'WoW Item',
itemPrompt: 'Item ID:',
url: 'URL',
urlPrompt: 'URL Address:'
};
Msg.ui= {
submit: 'Submit',
cancel: 'Cancel',
reset: 'Reset',
viewInGallery: 'View in gallery',
loading: 'Loading…',
unexpectedError: 'An error has occurred',
fansiteFind: 'Find this on…',
fansiteFindType: 'Find {0} on…',
fansiteNone: 'No fansites available.',
flashErrorHeader: 'Adobe Flash Player must be installed to see this content.',
flashErrorText: 'Download Adobe Flash Player',
flashErrorUrl: 'http://get.adobe.com/flashplayer/',
save: 'Save'
};
Msg.grammar= {
colon: '{0}:',
first: 'First',
last: 'Last',
ellipsis: '…'
};
Msg.fansite= {
achievement: 'achievement',
character: 'character',
faction: 'faction',
'class': 'class',
object: 'object',
talentcalc: 'talents',
skill: 'profession',
quest: 'quest',
spell: 'spell',
event: 'event',
title: 'title',
arena: 'arena team',
guild: 'guild',
zone: 'zone',
item: 'item',
race: 'race',
npc: 'NPC',
pet: 'pet'
};
Msg.search= {
noResults: 'There are no results to display.',
kb: 'Support',
post: 'Forums',
article: 'Blog Articles',
static: 'General Content',
wowcharacter: 'Characters',
wowitem: 'Items',
wowguild: 'Guilds',
wowarenateam: 'Arena Teams',
url: 'Suggested Links',
friend: 'Friends',
product: 'Marketplace Products',
other: 'Other'
};
//]]>
</script>
<script type="text/javascript" src="/achievements-webapp/sc2/static/js/sc2.js?v=28"></script>
<script type="text/javascript">
//<![CDATA[
$(function(){
Menu.initialize('/data/menu.json?v58');
Search.initialize('/achievements-webapp/sc2/en/search/ta');
});
//]]>
</script>
<script type="text/javascript" src="/achievements-webapp/sc2/static/local-common/js/utility/dropdown.js?v=58"></script>
<script>
//<![CDATA[
(function() {
var ga = document.createElement('script');
var src = "https://ssl.google-analytics.com/ga.js";
if ('http:' == document.location.protocol) {
src = "http://www.google-analytics.com/ga.js";
}
ga.type = 'text/javascript';
ga.setAttribute('async', 'true');
ga.src = src;
var s = document.getElementsByTagName('script');
s = s[s.length-1];
s.parentNode.insertBefore(ga, s.nextSibling);
})();
//]]>
</script>
</body>
</html>

<!-- OLD CONTENT -->

<body>

<input type="hidden" id="current_admin" value="<%=currentAdmin.getUUID()%>"/>

<select id="target_user">
	<% for (final User tu: allTargetUsers)
       { %>
       
       <option value="<%=tu.getUUID()%>"> <%=tu.getLogin()%>
       
       <% }
	%>

</select>


	

	<%
        final Predicate<IAchievement> catPredi = new Queries.AchieveByCat(catUUID);
		final List<IAchievement> allAchievements = Initializer.achievementMgr.read(catPredi);
			
		final User usr = Initializer.userMgr.read(new Queries.UserByLogin(currentUser.getPrincipal().toString())).get(0);
        final Predicate<ARecord> userPredicate = new Queries.ARecordByUserAndCat(usr.getUUID());
        final List<IAchievement> userAchievements = Initializer.aRecordMgr.readByUser(userPredicate, allAchievements);
	%>
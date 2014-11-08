<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="head">
	<h1 class="logo">
		<a href="/achievements-webapp/sc2/en/">Постижения</a>
	</h1>
	<div class="search-bar">
		<form action="/achievements-webapp/sc2/en/search" method="get" autocomplete="off">
			<div>
				<input type="text" class="search-field input" name="q" id="search-field" maxlength="200" tabindex="40" alt="Search StarCraft II" value="Search StarCraft II" /> <input type="submit" class="search-button" value="" tabindex="41" />
			</div>
		</form>
	</div>
	<ul class="menu" id="menu">
		<li class="menu-home" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a itemprop="url" href="/achievements-webapp/sc2/en/" class="menu-active"> <span itemprop="name">Home</span>
		</a></li>
		<li class="menu-game" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a itemprop="url" href="/achievements-webapp/sc2/en/game/"> <span itemprop="name">Game Guide</span>
		</a></li>
		<li class="menu-esports" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a itemprop="url"
			href="http://wcs.battle.net/achievements-webapp/sc2/en/?utm_source=battle.net&amp;utm_medium=internal&amp;utm_campaign=wcs&amp;utm_content=sc2-nav"> <span itemprop="name">eSports</span>
		</a></li>
		<li class="menu-media" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a itemprop="url" href="/achievements-webapp/sc2/en/media/"> <span itemprop="name">Media</span>
		</a></li>
		<li class="menu-forums" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a itemprop="url" href="/achievements-webapp/sc2/en/forum/"> <span itemprop="name">Forums</span>
		</a></li>
		<li class="menu-buy-now" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a itemprop="url" href="/achievements-webapp/sc2/en/buy-now/"> <span itemprop="name">Buy Now</span>
		</a></li>
	</ul>

	<div class="character-card card-game-char ajax-update">

		<div class="message">

			<span class="player-name">Бебълсчето</span>

			<div class="character">
				<a class="character-name context-link serif name-small" href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/" rel="np">Бебълсчето</a> <span class="achievement-score">30</span> <span class="avatar-frame"> <img
					src="/achievements-webapp/sc2/portraits/0-0.jpg" class="avatar avatar-sc2" /> <span class="border"></span> <span class="icon icon-sc2"></span>
				</span>
				<div id="context-1" class="ui-context">
					<div class="context">
						<a href="javascript:;" class="close" onclick="return CharSelect.close(this);"></a>
						<div class="context-user">
							<strong>Бебълсчето</strong><span>#984</span>
						</div>
						<div class="context-links">
							<a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/" title="Profile" rel="np" class="icon-profile link-first"> <span class="context-icon"></span>Profile
							</a> <a href="/achievements-webapp/sc2/en/search?f=post&amp;a=Sunstriderr%23984&amp;sort=time" title="View my posts" rel="np" class="icon-posts link-last"> <span class="context-icon"></span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<ol class="ui-breadcrumb">
		<li itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a href="/achievements-webapp/sc2/en/" rel="np" class="breadcrumb-arrow" itemprop="url"> <span class="breadcrumb-text" itemprop="name">Постижения</span>
		</a></li>
		<li itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/" rel="np" class="breadcrumb-arrow" itemprop="url"> <span class="breadcrumb-text"
				itemprop="name">Бебълсчето</span>
		</a></li>
		<li class="last" itemscope="itemscope" itemtype="http://schema.org/SiteNavigationElement"><a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/achievements/category/3211278" rel="np" itemprop="url">
				<span class="breadcrumb-text" itemprop="name">
					<jsp:getProperty name="pageMetadata" property="title" />
				</span>
		</a></li>
	</ol>
</div>

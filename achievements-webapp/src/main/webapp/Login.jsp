<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.authc.UsernamePasswordToken"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-gb"	class="en-gb">
<head xmlns:og="http://ogp.me/ns#" xmlns:fb="http://ogp.me/ns/fb#">
<meta http-equiv="imagetoolbar" content="false" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Battle.net Account Login</title>

<link rel="shortcut icon" href="//bneteu-a.akamaihd.net/login/static/images/meta/favicon.2dy4z.ico" />

<link rel="stylesheet" type="text/css" media="all" href="//bneteu-a.akamaihd.net/login/static/css/toolkit/bnet-web.min.387hK.css" />
<link rel="stylesheet" type="text/css" media="all" href="//bneteu-a.akamaihd.net/login/static/css/login/global.min.46f9o.css?v=1" />
<meta name="viewport" content="width=device-width" />

</head>
<body class="en-gb login-template web bnet"	data-embedded-state="STATE_LOGIN">

	<%
		final String user = request.getParameter("user");
		final String pass = request.getParameter("password");
		final Subject currentUser = SecurityUtils.getSubject();

		String errorMsg = "";
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(user, pass);
			currentUser.login(token);
			response.sendRedirect("/achievements-webapp/UserDashboard.jsp");

		} catch (Exception e) {
			// TODO:peteri
			//errorMsg = e.getMessage();
		}
	%>


	<div class="grid-container wrapper">
		<h1 class="logo">Battle.net Account Login</h1>

		<div class="hide mobile-grid-35 mobile-prefix-10" id="info-wrapper">
			<h2>
				<strong class="info-title"></strong>
			</h2>
			<p class="info-body"></p>
			<button class="btn btn-block hide visible-phone" id="info-phone-close">Close</button>
		</div>
		<div class="input-container" id="login-wrapper">
			<form action="/achievements-webapp/Login.jsp" method="post" id="password-form" class="username-required input-focus">
				<div class="control-group">
					<label id="accountName-label" class="control-label" for="accountName">E-mail</label>
					<div class="controls">
					
						<input aria-labelledby="accountName-label" type="text" name="user" id="accountName" value="" name="accountName" title="E-mail" maxlength="320"
							type="email" tabindex="1" class="input-block input-large"
							placeholder="E-mail" autofocus="autofocus" autocorrect="off" />
					</div>
				</div>
				
				
				<div class="control-group">
					<label id="password-label" class="control-label" for="password">Password</label>
					<div class="controls">
					
						<input ria-labelledby="password-label" id="password" value=""
							name="password" title="Password" maxlength="16" type="password"
							tabindex="1" class="input-block input-large" autocomplete="off"
							placeholder="Password" autocorrect="off" />
					</div>
				</div>
				<div class="persistWrapper">
					<label id="persistLogin-label" class="checkbox-label css-label "
						for="persistLogin"> <input
						aria-labelledby="persistLogin-label" id="persistLogin"
						name="persistLogin" type="checkbox" checked="checked" tabindex="1" />
						<span class="input-checkbox"></span> Keep me logged in
					</label>
				</div>
				<div class="control-group submit ">
					<button type="submit" id="submit" class="btn btn-primary btn-large btn-block " data-loading-text=""	tabindex="1">
						Log In <i class="spinner-battlenet"></i>
					</button>
				</div>
				<ul id="help-links">
					<li><a class="btn btn-block btn-large" rel="external" href="#" tabindex="1"> Create Free Account <i
							class="icon-external-link"></i>
					</a></li>
					<li><a class="" rel="external" href="#" tabindex="1"> Can’t log in? <i class="icon-external-link"></i>
					</a></li>
				</ul>
			</form>
		</div>
		<footer class="footer footer-eu">
		<div class="lower-footer-wrapper">
			<div class="lower-footer">
				<div id="copyright">
					<span class="legal-links"> <a
						onclick="return Core.open(this);"
						href="#"
						tabindex="100" data-action="Footer - Terms of Use">Terms of
							Use</a> <a onclick="return Core.open(this);"
						href="#" tabindex="100"
						data-action="Footer - Legal">Legal</a> <a
						onclick="return Core.open(this);"
						href="#"
						tabindex="100" data-action="Footer - Privacy Policy">Privacy
							Policy</a> <a onclick="return Core.open(this);"
						href="#"
						tabindex="100" data-action="Footer - Copyright Infringement">Copyright
							Infringement</a> <a target="_blank"
						href="#"
						tabindex="100">Contact Us</a>
					</span> ©2014 Blizzard Entertainment, Inc. All rights reserved
				</div>
				<div id="legal">
					<div id="legal-ratings" class="png-fix"></div>
					<span class="clear"> <!-- -->
					</span>
				</div>
			</div>
			<div id="marketing-trackers">
				<div class="marketing-cover"></div>
			</div>
		</div>
		</footer>
	</div>

</body>
</html>
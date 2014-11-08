<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<ul id="profile-menu">
	<li><a href="/achievements-webapp/sc2/en/profile/5716947/1/Sunstriderr/"><span class="back"></span>Обратно към <span>Профила</span></a></li>
	<%
		final List<CategoryNode> topLevel = CategoryBuilder.build();
		CategoryNode selectedCat = null;
		for (final CategoryNode cn : topLevel) {
	%>
	<%
		if (cn.getUUID().equals(catUUID)) {
				selectedCat = cn;
	%>
	<li class="active"><a href="#"><%=cn.getName()%></a></li>
	<%
		} else {
	%>
	<li class=""><a href="/achievements-webapp/ShowUserCategory?catUUID=<%=cn.getUUID()%>&targetUsrUUID=<%=targetUsrUUID%>"><%=cn.getName()%></a></li>
	<%
		}
		}
	%>
</ul>
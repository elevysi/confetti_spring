<%@ include file="../../layout/taglib.jsp"%>

<h1>Friends</h1>
<ul class="list">
<c:forEach items="${friends}" var="friend">
	<li><c:out value="${friend.user.first_name}"/> <c:out value="${friend.user.last_name}"/></li>
</c:forEach>
</ul>


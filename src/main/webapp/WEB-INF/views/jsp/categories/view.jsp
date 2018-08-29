<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>
<c:url value='/categories/delete/${category.id}' var="deleteCategoryUrl" />
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />

<fmt:parseNumber var="count" value="0" />
<fmt:parseNumber var="noColumns" value="4" />
<fmt:parseNumber var="starter" value="0" />
<fmt:parseNumber var="unit" value="1" />
<fmt:parseNumber var="even" value="2" />
<fmt:parseNumber var="itemsSize" value="${fn:length(category.publications)}" />


<!-- News v3 -->
<div class="news-v3-in">
	<h2>
		<a href="<c:url value='/public/tag/${category.name}/'/>"><c:out
				value="${category.name}" /></a>
	</h2>
	
	<div class="headline"><h2>Articles</h2></div>
	<ul class="list">
	
		<c:forEach items="${category.publications}" var="publication">
			<li><a href="<c:url value='/public/publication/view/${publication.id}/${publication.friendlyUrl}' />" ><c:out value="${publication.friendlyUrl}" /></a></li>
		</c:forEach>
		
	</ul>
	
	
	<c:if test="${isAdmin}">
		<div class="clearfix margin-bottom-20"><hr></div>
		<ul class="list-inline up-ul">
			<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-yellow btn-u-sm"  href="<spring:url value='/categories/edit/${category.id}' />"><i class="fa fa-edit"></i> Edit Category</a></li>
			<li>
				<form:form method="post" action="${deleteCategoryUrl}" onsubmit="return confirmDelete(this, '${deleteCategoryUrl}')" class="form-inline">
					<button class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-red btn-u-sm"><i class="fa fa-magic"></i> Delete Tag</button>
				</form:form>
			</li>
		</ul>
		<div class="clearfix margin-bottom-20"><hr></div>
	</c:if>
	
</div>
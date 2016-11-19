<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp" %>

<h1><c:out value="${user.first_name}"/> <c:out value="${user.last_name}"/> <a href="<spring:url value='/bucket/${user.username}' />" class="btn-u btn-u-green">Bucket This Confetti</a></h1>
<c:forEach items="${posts}" var="post">
<!--Blog Post-->
<div class="row blog blog-medium margin-bottom-40">
	<div class="col-md-5">
		<img class="img-responsive" src="<c:url value='/resources/img/main/11.jpg'/>" alt="">
	</div>
	<div class="col-md-7">
		<h2><c:out value="${post.title}" /></h2>
		<ul class="list-unstyled list-inline blog-info">
			<li><i class="fa fa-calendar"></i> November 02, 2013</li>
			<li><i class="fa fa-comments"></i> <a href="#">24 Comments</a></li>
			<li><i class="fa fa-tags"></i> Technology, Internet</li>
		</ul>
		<p><c:out value="${post.content}" /></p>
		<p>
			<a class="btn-u btn-u-small" href="blog_item.html"><i
				class="fa fa-location-arrow"></i> Read More</a>
		</p>
	</div>
</div>
<!--End Blog Post-->
</c:forEach>

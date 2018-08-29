<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="taglib.jsp"%>



<!-- Topbar -->
<div class="topbar">
	<ul class="loginbar pull-right">
		<li class="hoverSelector"><i class="fa fa-globe"></i> <a>Languages</a>
			<ul class="languages hoverSelectorBlock">
				<li class="active"><a href="?locale=en">English <i
							class="fa fa-check"></i></a></li>
					<li><a href="?locale=fr">French</a></li>
			</ul></li>
		<li class="topbar-devider"></li>
		<li><a href="page_faq.html">Help</a></li>
		<li class="topbar-devider"></li>
		<security:authorize access="! isAuthenticated()">
				<li><a href="<spring:url value="/auth/rqstd/login"/>">Login</a></li>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<li><a href="<spring:url value="/logout"/>">Logout (<security:authentication property="principal.username" />)</a></li>
			</security:authorize>
	</ul>
</div>
<!-- End Topbar -->
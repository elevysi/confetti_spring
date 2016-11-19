<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%>
<img class="img-responsive profile-img margin-bottom-20" src="<c:url value='/uploads/download?key=${actingProfile.profilePicture.keyIdentification}'/>" alt="${userProfile.profilePicture.altText}">
<ul class="list-group sidebar-nav-v1 margin-bottom-40"
	id="sidebar-nav-1">
	
	<security:authorize access="isAuthenticated()">
		<c:if test="${not empty actingProfile}">
			<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/' />"><i class="fa fa-user"></i> Home</a></li>
			<li class="list-group-item"><a href="<spring:url value='/uploads/profile/' />"><i class="fa fa-user"></i> Profile Pic</a></li>
			<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/posts/' />"><i class="fa fa-user"></i> Posts</a></li>
			<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/plays/' />"><i class="fa fa-user"></i> Plays</a></li>
			<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/albums/' />"><i class="fa fa-user"></i>  Albums</a></li>
			<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/settings/' />"><i class="fa fa-cog"></i> Settings</a></li>
			<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/messages/' />"><i class="fa fa-cog"></i> Messages</a></li>
			<li class="list-group-item"><a href="<spring:url value='/profile/friends' />"><i class="fa fa-group"></i> Friends</a></li>
				
		</c:if>
	</security:authorize>
	
	<li class="list-group-item active"><a href="profile.html"><i class="fa fa-bar-chart-o"></i> Profile</a></li>
	
	
</ul>


<!--Notification-->
<div class="panel-heading-v2 overflow-h">
	<h2 class="heading-xs pull-left">
		<i class="fa fa-bell-o"></i> Notification
	</h2>
	<a href="#"><i class="fa fa-cog pull-right"></i></a>
</div>
<ul id="scrollbar5" class="list-unstyled contentHolder margin-bottom-20">
	<li class="notification"><i
		class="icon-custom icon-sm rounded-x icon-bg-red icon-line icon-envelope"></i>
		<div class="overflow-h">
			<span><strong>Albert Heller</strong> has sent you email.</span> <small>Two
				minutes ago</small>
		</div></li>
	<li class="notification"><img class="rounded-x"
		src="<c:url value='/resources/img/testimonials/img6.jpg" alt="'/>">
		<div class="overflow-h">
			<span><strong>Taylor Lee</strong> started following you.</span> <small>Today
				18:25 pm</small>
		</div></li>
	<li class="notification"><i
		class="icon-custom icon-sm rounded-x icon-bg-yellow icon-line fa fa-bolt"></i>
		<div class="overflow-h">
			<span><strong>Natasha Kolnikova</strong> accepted your
				invitation.</span> <small>Yesterday 1:07 pm</small>
		</div></li>
	<li class="notification"><img class="rounded-x"
		src="<c:url value='/resources/img/testimonials/img1.jpg" alt="'/>">
		<div class="overflow-h">
			<span><strong>Mikel Andrews</strong> commented on your
				Timeline.</span> <small>23/12 11:01 am</small>
		</div></li>
	<li class="notification"><i
		class="icon-custom icon-sm rounded-x icon-bg-blue icon-line fa fa-comments"></i>
		<div class="overflow-h">
			<span><strong>Bruno Js.</strong> added you to group chating.</span> <small>Yesterday
				1:07 pm</small>
		</div></li>
	<li class="notification"><img class="rounded-x"
		src="<c:url value='/resources/img/testimonials/img6.jpg" alt="'/>">
		<div class="overflow-h">
			<span><strong>Taylor Lee</strong> changed profile picture.</span> <small>23/12
				15:15 pm</small>
		</div></li>
</ul>
<button type="button" class="btn-u btn-u-default btn-u-sm btn-block">Load
	More</button>
<!--End Notification-->

<div class="margin-bottom-50"></div>

<!--Datepicker-->
<form action="" id="sky-form2" class="sky-form">
	<div id="inline-start"></div>
</form>
<!--End Datepicker-->
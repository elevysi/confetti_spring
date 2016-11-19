<%@ include file="../../layout/taglib.jsp"%>
<div class="tab-v1" ng-app="confettiApp">
	<ul class="nav nav-justified nav-tabs">
		<li><a href="#/profileSettings">Edit Profile</a></li>
		<li><a data-toggle="tab" href="#password">Change Password</a></li>
		<li><a data-toggle="tab" href="#payment">Payment Options</a></li>
		<li><a data-toggle="tab" href="#notSettings">Notification
				Settings</a></li>
	</ul>
	
	
	<div ng-view></div>
	
</div>
<!-- End Tab v1 -->

<%-- <script src="<c:url value='/ng/lib/angular/angular.js' />"></script> --%>

<script src="<c:url value='/ng/app.js' />"></script>
<script src="<c:url value='/ng/services.js' />"></script>
<script src="<c:url value='/ng/controllers/profileController.js' />"></script>
<script src="<c:url value='/ng/filters.js' />"></script>
<script src="<c:url value='/ng/directives.js' />"></script>
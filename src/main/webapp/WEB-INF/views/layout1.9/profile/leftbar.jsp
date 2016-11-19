<%@ include file="../../layout/taglib.jsp"%>
<c:if test="${not empty actingProfile.profilePicture && not empty actingProfile.profilePicture.iterator().hasNext()}">
<img class="img-responsive profile-img margin-bottom-20" src="<c:url value='/uploads/download?key=${actingProfile.profilePicture.iterator().next().keyIdentification}'/>" alt="${actingProfile.profilePicture.iterator().next().altText}">
</c:if>
<ul class="list-group sidebar-nav-v1 margin-bottom-40"
	id="sidebar-nav-1">
	<li class="list-group-item active"><a href="<spring:url value='/profile/${actingProfile.name}'/>"><i class="fa fa-bar-chart-o"></i> Home</a></li>
	<li class="list-group-item"><a href="<spring:url value='/uploads/profile/'/>"><i class="fa fa-user"></i> Profile Picture</a></li>
	<li class="list-group-item"><a href="<spring:url value='/profile/friends'/>"><i class="fa fa-group"></i> Friends Bucket</a></li>
	<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/posts/'/>"><i class="fa fa-cubes"></i> Posts</a></li>
	<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/plays/'/>"><i class="fa fa-cubes"></i> Plays</a></li>
	<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/albums/'/>"><i class="fa fa-cubes"></i> Albums</a></li>
	<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/messages/'/>"><i class="fa fa-comments"></i> Messages</a></li>
	<li class="list-group-item"><a href="<spring:url value='/profile/${actingProfile.name}/settings/'/>"><i class="fa fa-cog"></i> Settings</a></li>
</ul>

<!--Notification-->

<!--End Notification-->

<div class="margin-bottom-50"></div>

<!--Datepicker-->
<form action="#" id="sky-form2" class="sky-form">
	<div id="inline-start"></div>
</form>
<!--End Datepicker-->
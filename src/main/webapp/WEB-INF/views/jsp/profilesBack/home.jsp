<%@ include file="../../layout/taglib.jsp"%>
<c:url var="homeTabUrl" value="/profile/homeTab" />
<c:url var="profileTabUrl" value="/profile/profileTab" />
<c:url var="messagesTabUrl" value="/profile/messagesTab" />
<c:url var="settingsTabUrl" value="/profile/settingsTab" />
<!--Profile Body-->
<div class="profile-body">
Path to pic is ${userProfile.profilePicture.id}
	<div class="row">

		<!-- Tab v1 -->
		<div class="tab-v1">
			<ul class="nav nav-tabs" id="rowTab">
				<li class="active"><a href="#home" class="initialactive" data-toggle="tab" data-url="${homeTabUrl}"><i class="fa fa-home"></i> Home</a></li>
				<li><a href="#profile" data-toggle="tab" data-url="${profileTabUrl}"><i class="fa fa-cloud"></i> Profile</a></li>
				<li><a href="#messages" data-toggle="tab" data-url="${messagesTabUrl}"><i class="fa fa-comments"></i> Messages</a></li>
				<li><a href="#settings" data-toggle="tab" data-url="${settingsTabUrl}"><i class="fa fa-gear"></i> Settings</a></li>
			</ul>
			<div class="tab-content">
				<div class="profile-edit tab-pane fade in active" id="home">
					<div class="row">
						
					</div>
				</div>
				<div class="profile-edit tab-pane fade in" id="profile">
					
				</div>
				<div class="profile-edit tab-pane fade in" id="messages">
					
				</div>
				<div class="profile-edit tab-pane fade in" id="settings">
					
				</div>
			</div>
		</div>
		<!-- End Tab v1 -->

	</div>


</div>
<!--End Profile Body-->

<script>
$(document).ready(function(){
	
	var initialTab = $( "a[class='initialactive']" );
	var initialurl = initialTab.attr('data-url');
	var initialhref =  $( "a[class='initialactive']" ).attr('href');
	
	$(initialhref).load(initialurl, function(result){
		  initialTab.tab('show');
	});
	
	  
});



$('#rowTab a').click(function (e) {
  	e.preventDefault();

  	$('.tab-content').addClass('loader');

 	var url = $(this).attr("data-url");
  	var href = this.hash;
  	var pane = $(this);

  	$(href).load(url,function(result){
  		   
	    pane.tab('show');
	});
});
</script>

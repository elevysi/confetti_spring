<%@ include file="../../layout/taglib.jsp"%>

<c:url var="allBucketUrl" value="/profile/${profile.name}/profileBucketNetworkAjax/1/" />
<c:url var="myBucketUrl" value="/profile/${profile.name}/profileBucketNetworkAjax/2/" />
<c:url var="includeBucketUrl" value="/profile/${profile.name}/profileBucketNetworkAjax/3/" />
<c:url var="mutualBucketUrl" value="/profile/${profile.name}/profileBucketNetworkAjax/4/" />

<c:url var="postsUrl" value="/profile/${profile.name}/posts/" />
<c:url var="playsUrl" value="/profile/${profile.name}/plays/" />
<c:url var="dossiersUrl" value="/profile/${profile.name}/dossiers/" />
<c:url var="albumsUrl" value="/profile/${profile.name}/albums/" />

<c:url var="bucketPostUrl" value="/bucket/${profile.name}" />

<div class="" style="margin-left: 20px;">
	<h2><c:out value="${profile.name}"/></h2>
	<ul class="list-inline up-ul">
		<!-- Button trigger modal -->
		<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-purple btn-u-sm"  href="<spring:url value='/uploads/profile' />">Profile Picture</a></li>
		<%-- <li><a class="btn-u btn-brd btn-u-blue btn-u-xs modalOpen"  href="<spring:url value='/posts/addModal' />">New Post Modal</a></li> --%>
		<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-sea btn-u-sm"  href="<spring:url value='/posts/add' />">New Post</a></li>
		<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-red btn-u-sm"  href="<spring:url value='/plays/add' />">New Play</a></li>
		<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-dark btn-u-sm"  href="<spring:url value='/albums/add' />">New Album</a></li>
		<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-brown btn-u-sm"  href="<spring:url value='/products/add' />">New Product</a></li>
		<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-blue btn-u-sm"  href="<spring:url value='/conversations/add/' />">New Message</a></li>
		<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-yellow btn-u-sm"  href="<spring:url value='/dossiers/add' />">New Dossier</a></li>
	</ul>
	<hr>
	
	<ul class="list-inline up-ul">
		<li>
			<form:form action="${bucketPostUrl}" method="post" onSubmit="return confirm('Please confirm before the action is processed.');" class="form-inline">
				<input type="hidden" value="${profile.name}" name="bucketID" >
				<button class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-purple btn-u-sm" type="submit">Add to my Bucket</button>
			</form:form>
		</li>
	</ul>
	
	<hr>
	
	<div class="tab-v2">
		<ul class="nav nav-tabs" id="rowTab">
			<li class="active"><a href="#posts" data-toggle="tab" aria-expanded="true" class="initialactive" data-url="${postsUrl}">Posts</a></li>
			<li><a href="#plays" data-toggle="tab" aria-expanded="true" data-url="${playsUrl}">Plays</a></li>
			<li><a href="#dossiers" data-toggle="tab" aria-expanded="true" data-url="${dossiersUrl}">Dossiers</a></li>
			<li><a href="#albums" data-toggle="tab" aria-expanded="true" data-url="${albumsUrl}">Albums</a></li>
			
			<li><a href="#allBucket" data-toggle="tab" aria-expanded="true" data-url="${allBucketUrl}">All Network</a></li>
			<li><a href="#myBucket" data-toggle="tab" aria-expanded="true" data-url="${myBucketUrl}">Bucket</a></li>
			<li class=""><a href="#includeMeBucket" data-toggle="tab" aria-expanded="true" data-url="${includeBucketUrl}">Included in Bucket</a></li>
			<li class=""><a href="#mutualBucket" data-toggle="tab" aria-expanded="false" data-url="${mutualBucketUrl}">Mutual Bucket</a></li>
		</ul>
		<div class="tab-content">
			
			<div class="tab-pane fade active in" id="posts">
			</div>
			
			<div class="tab-pane fade" id="plays">
			</div>
			
			<div class="tab-pane fade" id="dossiers">
			</div>
			
			<div class="tab-pane fade" id="albums">
			</div>
			
			<div class="tab-pane fade" id="allBucket">
			</div>
			<div class="tab-pane fade" id="myBucket">
			</div>
			
			<div class="tab-pane fade" id="includeMeBucket">
			</div>
			
			<div class="tab-pane fade" id="mutualBucket">
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
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
 	  	$('.tab-content').removeClass('loader'); 
 		pane.tab('show');
	});
  	
});


</script>
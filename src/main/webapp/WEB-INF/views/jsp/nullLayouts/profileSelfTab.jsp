<%@ include file="../../layout/taglib.jsp"%>

<div class="" style="margin-left: 20px;">
<h2><c:out value="${actingProfile.name}"/></h2>

<!-- Button trigger modal -->
<a class="btn-u btn-u-green modalOpen"  href="<spring:url value='/posts/addModal' />">New Post Modal</a>

<a class="btn-u btn-u-green"  href="<spring:url value='/posts/add' />">New Post</a>
<a class="btn-u btn-u-green"  href="<spring:url value='/uploads/profile' />">Profile Picture</a>
<a class="btn-u btn-u-green"  href="<spring:url value='/products/add' />">New Product</a>
<a class="btn-u btn-u-green"  href="<spring:url value='/plays/add' />">New Play</a>
<a class="btn-u btn-u-green"  href="<spring:url value='/albums/add' />">New Album</a>


<hr>


<c:forEach items="${profilePosts}" var="post">
	


<!--Blog Post-->
<div class="row blog blog-medium margin-bottom-40">

	<c:choose>
		<c:when test="${not empty post.postImage}">
		
			<div class="col-md-5">
				<img class="img-responsive" src="<c:url value='/uploads/download?key=${post.postImage.keyIdentification}'/>" alt="${post.postImage.altText}">
			</div>
			
			<div class="col-md-7">
					<h2><c:out value="${post.title}" /></h2>
					<ul class="list-unstyled list-inline blog-info">
						<li><i class="fa fa-calendar"></i> <fmt:formatDate value="${post.created}" type="both" dateStyle="medium" timeStyle="medium" /></li>
						<li><i class="fa fa-comments"></i> <a href='<c:url value="/posts/view/${post.id}#commentingSection"/>'>${fn:length(post.comments)} Comments</a></li>
						<li><i class="fa fa-tags"></i> Technology, Internet</li>
					</ul>
					<div class="crop"><c:out value="${post.content}" escapeXml="false"/></div>
					<p>
						<a class="btn-u btn-u-small" href="<c:url value='/posts/view/${post.id}'/>"><i class="fa fa-location-arrow"></i> Read More</a>
					</p>
			</div>
			
		
		</c:when>
		
		<c:otherwise>
		
			<div class="">
					<h2><c:out value="${post.title}" /></h2>
					<ul class="list-unstyled list-inline blog-info">
						<li><i class="fa fa-calendar"></i> <fmt:formatDate value="${post.created}" type="both" dateStyle="medium" timeStyle="medium" /></li>
						<li><i class="fa fa-comments"></i> <a href='<c:url value="/posts/view/${post.id}#commentingSection"/>'>${fn:length(post.comments)} Comments</a></li>
						<li><i class="fa fa-tags"></i> Technology, Internet</li>
					</ul>
					<div class="crop"><c:out value="${post.content}" /></div>
					<p>
						<a class="btn-u btn-u-small" href="<c:url value='/posts/view/${post.id}'/>"><i class="fa fa-location-arrow"></i> Read More</a>
					</p>
			</div>
		
		
		</c:otherwise>
	</c:choose>

	
</div>
<div class="clearfix margin-bottom-20"><hr></div>
<!--End Blog Post-->
</c:forEach>
<c:out value="${profilePublications}"/>
<c:forEach items="${profilePublications}" var="publication">
Ola amigo
${publication.id}

</c:forEach>

</div>
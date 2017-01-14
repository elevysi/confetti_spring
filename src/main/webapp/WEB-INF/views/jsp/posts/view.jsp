<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/taglib.jsp"%>
<link rel="stylesheet" href="<c:url value='/js/jquery/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui.min.css'/>">
<c:url var="getCommentsUrl" value="/comments/public/section/posts/${post.id}" />
<c:url value="/admin/featureItem" var="featureUrl" />
<c:url value="/admin/unfeatureItem" var="unfeatureUrl" />
<c:url value='/posts/delete/${post.id}' var="deletePostUrl" />
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />

<!-- News v3 -->
<div class="news-v3 bg-color-white margin-bottom-30" id="blogRead">
	<c:choose>
		<c:when test="${not empty post.uploads && fn:length(post.uploads) > 1}">
			<div class="carousel slide carousel-v2" id="portfolio-carousel">
				<ol class="carousel-indicators">
					<fmt:parseNumber var="licount" value="1" />
					<fmt:parseNumber var="activeLiItemIndex" value="1" />
					<c:forEach items="${post.uploads}" var="upload">

						<c:set var="itemClass" value="rounded-x active" />
						<c:if test="${licount > activeLiItemIndex}">
							<c:set var="itemClass" value="rounded-x" />
						</c:if>
						<li class="${itemClass}" data-target="#portfolio-carousel"
							data-slide-to="${licount}"></li>
						<fmt:parseNumber var="licount" value="${licount + 1}" />
					</c:forEach>
				</ol>
				<div class="carousel-inner">
					<fmt:parseNumber var="count" value="1" />
					<fmt:parseNumber var="activeItemIndex" value="1" />
					<c:forEach items="${post.uploads}" var="upload">

						<c:set var="itemClass" value="item active" />
						<c:if test="${count > activeItemIndex}">
							<c:set var="itemClass" value="item" />
						</c:if>

						<div class="<c:out value='${itemClass}' />">
							<img class="img-responsive full-width"
								src="<c:url value='/uploads/download?key=${upload.keyIdentification}'/>" alt="${upload.altText}">
						</div>
						<fmt:parseNumber var="count" value="${count + 1}" />
					</c:forEach>
				</div>
				<a class="left carousel-control rounded-x"
					href="#portfolio-carousel" role="button" data-slide="prev"> <i
					class="fa fa-angle-left arrow-prev"></i>
				</a> <a class="right carousel-control rounded-x"
					href="#portfolio-carousel" role="button" data-slide="next"> <i
					class="fa fa-angle-right arrow-next"></i>
				</a>
			</div>




		</c:when>


		<c:when test="${not empty post.uploads && fn:length(post.uploads) == 1}">
			<!-- img-responsive full-width -->
			<img class="img-responsive full-width" src="<c:url value='/uploads/download?key=${post.uploads.iterator().next().keyIdentification}'/>" alt="${post.uploads.iterator().next().altText}">
		</c:when>
	</c:choose>

	<div class="news-v3-in">
		<ul class="list-inline posted-info">
			<li>By <a href="<c:url value='/profile/${post.profile.id}'/>"><c:out
						value="${post.profile.name}" /></a></li>

			<c:if test="${not empty post.categories}">
				<li>In <c:forEach items="${post.categories}" var="postCategory"
						varStatus="stat">


						<c:url var="itemUrl" value="/categories/view/${postCategory.id}" />
						<c:set var="itemText" value="${postCategory.name}" />

						<c:set var="itemDisplay"
							value="<a href='${itemUrl}'>${itemText}</a>" />

						<c:set var="categoryDisplay"
							value="${categoryDisplay}${stat.first ? '' : ', '} ${itemDisplay}" />

					</c:forEach> <c:out value="${categoryDisplay}" escapeXml="false" />
				</li>
			</c:if>

			<li>Posted <fmt:formatDate pattern="dd MMMM yyyy"
					value="${post.created}" />
			</li>
		</ul>
		<h2>
			<a href="<c:url value='/posts/view/${post.id}/'/>"><c:out
					value="${post.title}" /></a>
		</h2>
		<p>
			<c:out value="${post.content}" escapeXml="false" />
		</p>
		
		
		<c:if test="${canEditPost || isAdmin}">
			<div class="clearfix margin-bottom-20"><hr></div>
			<div class="btn-group">
				<a class="btn-u btn-brd btn-u-blue"  href="<spring:url value='/posts/edit/${post.id}' />"><i class="fa fa-edit"></i> Edit Post</a>
				
				<form:form method="post" action="${deletePostUrl}" onsubmit="return confirmDelete(this, '${deletePostUrl}')">
						<button class="btn-u btn-brd btn-u-blue"><i class="fa fa-trash"></i> Delete Post</button>
				</form:form>
				
				<c:if test="${isAdmin}">
				
					<c:choose>
						<c:when test="${post.publication.featured}">
							<a class="btn-u btn-brd btn-u-blue" id="unfeatureBtn"  href="<spring:url value='/admin/unfeatureItem' />"><i class="fa fa-magic"></i> Unfeature publication</a>
						</c:when>
						<c:otherwise>
							<a class="btn-u btn-brd btn-u-blue" id="unfeatureBtn"  href="<spring:url value='/admin/featureItem' />"><i class="fa fa-magic"></i> Feature publication</a>
						</c:otherwise>
					
					</c:choose>
				
				</c:if>
					
			</div>
			<div class="clearfix margin-bottom-20"><hr></div>
		</c:if>
			
		
		<div id="postViewSection">
			<ul class="post-shares post-shares-lg">
				<li><a href="#commentingSection"> <i class="rounded-x icon-speech"></i> <span id="noCmtsDiv">0</span></a></li>
				<li><a class="modalOpen noSave" href="<spring:url value='/likes/itemShares/posts/${post.id}' />"> <i class="rounded-x icon-share"></i> <span id="noSharesDiv">0</span></a></li>
				<li><a class="modalOpen noSave" href="<spring:url value='/likes/itemLikes/posts/${post.id}' />"><i class="rounded-x icon-heart"></i> <span id="noLikesDiv">0</span></a></li>
			</ul>	
		</div>
		
		
	</div>
</div>
<!-- End News v3 -->

<!-- Blog Post Author -->
<div class="blog-author margin-bottom-30">

	<c:choose>
		<c:when test="${not empty post.profile.profilePicture}">
			<img src="<c:url value='/uploads/download?key=${post.profile.profilePicture.iterator().next().keyIdentification}'/>" alt="${post.profile.profilePicture.iterator().next().altText}">

		</c:when>
		<c:otherwise>

			<img src="<c:url value='/resources_1_8//img/team/img1-sm.jpg'/>" alt="">
		</c:otherwise>
	</c:choose>


	<div class="blog-author-desc">
		<div class="overflow-h">
			<h4>
				<c:out value="${post.profile.name}" />
			</h4>
			<ul class="list-inline">
				<li><a href="#"><i class="fa fa-facebook"></i></a></li>
				<li><a href="#"><i class="fa fa-twitter"></i></a></li>
				<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
			</ul>
		</div>
		<p>
			<c:out value="${post.profile.description}" escapeXml="false"/>
		</p>
	</div>
</div>
<!-- End Blog Post Author -->


<c:if test="${not empty profileLatestPosts}">

<!-- News v2 -->
<div class="row news-v2 margin-bottom-50">

	
    
    <c:forEach items="${profileLatestPosts}" var="latestProfilePost">
    	<div class="col-sm-6 sm-margin-bottom-30">
	        <div class="news-v2-badge">
	            
	            	<c:if test="${not empty latestProfilePost.uploads}">
	            		<img class="img-responsive" src="<c:url value='/uploads/download?key=${latestProfilePost.uploads.iterator().next().keyIdentification}'/>" alt="${latestProfilePost.uploads.iterator().next().altText}">
	            		
	            	</c:if>
	            	
	            <p>
	                <span><fmt:formatDate pattern="dd" value="${latestProfilePost.created}" /></span>
	                <small><fmt:formatDate pattern="MMM" value="${latestProfilePost.created}" /></small>
	                <small><fmt:formatDate pattern="yyyy" value="${latestProfilePost.created}" /></small>
	            </p>
	        </div>
	        <div class="news-v2-desc">
	            <h3><a href="<c:url value='/posts/view/${latestProfilePost.id}/${latestProfilePost.publication.friendlyUrl}'/> "><c:out value="${latestProfilePost.title}" /></a></h3>
	            <small>By <c:out value="${latestProfilePost.profile.name}"></c:out> | California, US 
	            
	            <c:if test="${not empty latestProfilePost.categories}">| In
				<c:forEach items="${latestProfilePost.categories}" var="postCategory"
						varStatus="stat">


						<c:url var="itemUrl" value="/categories/view/${postCategory.id}" />
						<c:set var="itemText" value="${postCategory.name}" />

						<c:set var="itemDisplay"
							value="<a href='${itemUrl}'>${itemText}</a>" />

						<c:set var="categoryDisplay"
							value="${categoryDisplay}${stat.first ? '' : ', '} ${itemDisplay}" />

					</c:forEach> <c:out value="${categoryDisplay}" escapeXml="false" />
				
			</c:if>
	            
	             
	             
	             </small>
	            <c:out value="${latestProfilePost.description}" escapeXml="false" />
	        </div>
	    </div>
    </c:forEach>
    
</div>
<!-- End News v2 -->


</c:if>



<div id="commentingSection" class="min-height-256"></div>

<!-- Code Highlighter -->
<link type="text/css" rel="stylesheet" href="<c:url value='/js/syntaxhighlighter_3.0.83/styles/shCore.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/js/syntaxhighlighter_3.0.83/styles/shThemeDefault.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/js/syntaxhighlighter_3.0.83/styles/shCoreEclipse.css'/>" />

<script type="text/javascript"  src="<c:url value='/js/syntaxhighlighter_3.0.83/scripts/shCore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/syntaxhighlighter_3.0.83/scripts/shBrushJava.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/syntaxhighlighter_3.0.83/scripts/shBrushJScript.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/syntaxhighlighter_3.0.83/scripts/shBrushXml.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/syntaxhighlighter_3.0.83/scripts/shBrushSql.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/syntaxhighlighter_3.0.83/scripts/shBrushPlain.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/syntaxhighlighter_3.0.83/scripts/shBrushCss.js'/>"></script>

<script type="text/javascript">SyntaxHighlighter.all();</script>
<script>

$(document).ready(function(){
	var getCommentsUrl = '${getCommentsUrl}';
	$("#commentingSection").html("");
	$("#commentingSection").addClass("loader-256-gray");
	$.ajax({
          type: "GET",
          url: getCommentsUrl,
          dataType : "html",
          success: function(html){
        	  $("#commentingSection").html(html);
        	  $("#commentingSection").removeClass("loader-256-gray");
          },
          error: function(){
              alert("Failed to load Comments.");
          }
	 });
	
	$("#unfeatureBtn").on("click", function(e){
		e.preventDefault();
		var _requestedUrl = $(this).attr("href");
		var _dialogHtml = "<h6> Please confirm.</h6>";
		
	  	   $('<div id="dialogCom"></div>').appendTo('body')
			  .html(_dialogHtml)
			  .dialog({
			      modal: true, title: 'Confirm to continue', zIndex: 10000, autoOpen: true,
			      width: 'auto', resizable: false,
			      buttons: {
			          Continue: function () {

			        	  $.ajax({
			    	          type: "POST",
			    	          data: {id : "${post.publication.id}"},
			    	          url: _requestedUrl,
			    	          dataType : "json",
			    	          beforeSend:function(xhr){
					  	 	         xhr.setRequestHeader(header, token)
				  	 	      },
			    	          success: function(returnData){
			    	        	  if(returnData.code == 1){
			    	        		  $("#unfeatureBtn").attr("href", "${featureUrl}");
			    	        		  $("#unfeatureBtn").text("Feature Item");
			    	        		  alert(returnData.message);
			    	        	  }else if(returnData.code == 2){
			    	        		  $("#unfeatureBtn").attr("href", "${unfeatureUrl}");
			    	        		  $("#unfeatureBtn").text("Unfeature Item");
			    	        		  alert(returnData.message);
			    	        	  }
			    	          },
			    	          error: function(){
			    	              alert("Failed to unfeature item.");
			    	          }
			    		 });
			        	 $(this).dialog("close");
			          },
			          Cancel: function (file) {		        	  
			        	 	$(this).dialog("close");
			          }
			      },
			      close: function (event, ui) {
			          $(this).remove();
			      }
			});
	});
	
});
	
	
	</script>

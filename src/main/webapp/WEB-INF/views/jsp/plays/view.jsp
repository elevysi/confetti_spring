<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/taglib.jsp"%>
<c:url var="getCommentsUrl" value="/comments/public/section/plays/${play.id}" />
<c:url value="/admin/featureItem" var="featureUrl" />
<c:url value="/admin/unfeatureItem" var="unfeatureUrl" />
<c:url value='/plays/delete/${play.id}' var="deletePlayUrl" />
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />

<!-- Blog Posts -->
<div class="news-v3 bg-color-white margin-bottom-60">
	<div class="responsive-video">
		<iframe src="<c:out value='${play.url}'/>" frameborder="0" allowfullscreen></iframe>
	</div>
	<div class="news-v3-in">
		<ul class="list-inline posted-info">
			<li>By <a href="<c:url value='/profile/${play.playProfile.name}'/>"><c:out value="${play.playProfile.name}"/></a></li>
			<li>Posted <fmt:formatDate pattern="dd MMMM yyyy" value="${play.created}" />
			</li>
		</ul>
		<h2><c:out value="${play.title}"/></h2>
		<p>
			<c:out value="${play.description}" escapeXml="false" />
		</p>
		
		<div class="row">
			<div class="col-md-4">
			
				<c:if test="${canEditPlay || isAdmin}">
					<a class="btn-u rounded btn-u-dark-blue btn-u-xs"  href="<spring:url value='/plays/edit/${play.id}' />"><i class="fa fa-magic"></i> Edit play</a>
				</c:if>
			</div>
			
			<div class="col-md-4">
			
				<c:if test="${canEditPlay || isAdmin}">
					<form:form method="post" action="${deletePlayUrl}" onsubmit="return confirmDelete(this, '${deletePlayUrl}')">
						<button class="btn-u rounded btn-u-dark-blue btn-u-xs"><i class="fa fa-magic"></i> Delete play</button>
					</form:form>
				
				</c:if>
			</div>
			
			<div class="col-md-4">
				<c:if test="${isAdmin}">
					<c:choose>
						<c:when test="${play.publication.featured}">
							<a class="btn-u rounded btn-u-dark-blue btn-u-xs" id="unfeatureBtn"  href="<spring:url value='/admin/unfeatureItem' />"><i class="fa fa-magic"></i> Unfeature publication</a>
						</c:when>
						<c:otherwise>
							<a class="btn-u rounded btn-u-dark-blue btn-u-xs" id="unfeatureBtn"  href="<spring:url value='/admin/featureItem' />"><i class="fa fa-magic"></i> Feature publication</a>
						</c:otherwise>
					
					</c:choose>
				</c:if>
			</div>			
		</div>
		
		
		<div id="postViewSection">
			<ul class="post-shares post-shares-lg">
				<li><a href="#commentingSection"> <i class="rounded-x icon-speech"></i> <span id="noCmtsDiv">0</span></a></li>
				<li><a class="modalOpen noSave" href="<spring:url value='/likes/itemShares/plays/${play.id}' />"> <i class="rounded-x icon-share"></i> <span id="noSharesDiv">0</span></a></li>
				<li><a class="modalOpen noSave" href="<spring:url value='/likes/itemLikes/plays/${play.id}' />"><i class="rounded-x icon-heart"></i> <span id="noLikesDiv">0</span></a></li>
			</ul>	
		</div>
	</div>
</div>
<!-- End Blog Posts -->




<!-- Blog Post Author -->
<div class="blog-author margin-bottom-30">
	
	<c:choose>
		<c:when test="${not empty play.playProfile.profilePicture}">
			<img src="<c:url value='/uploads/download?key=${play.playProfile.profilePicture.iterator().next().keyIdentification}'/>" alt="${play.playProfile.profilePicture.iterator().next().altText}">

		</c:when>
		<c:otherwise>

			<img src="<c:url value='/resources_1_8//img/team/img1-sm.jpg'/>" alt="">
		</c:otherwise>
	</c:choose>
	
	<div class="blog-author-desc">
			<div class="overflow-h">
				<h4><c:out value="${play.playProfile.name}" /></h4>
				<ul class="list-inline">
					<li><a href="#"><i class="fa fa-facebook"></i></a></li>
					<li><a href="#"><i class="fa fa-twitter"></i></a></li>
					<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
				</ul>
			</div>
			
			<c:if test="${not empty play.playProfile.description}">
				<c:out value="${play.playProfile.description}" escapeXml="false" />
			</c:if>
			
			
	</div>
</div>
<!-- End Blog Post Author -->

<!-- Authored Blog -->
<div class="row news-v2 margin-bottom-50">

<c:if test="${not empty latestProfilePlays}">

	<c:forEach items="${latestProfilePlays}" var="latestProfilePlay">
	
		<div class="col-sm-4 sm-margin-bottom-30">
			<div class="news-v2-badge">
			
					<div class="responsive-video">
						<iframe src="<c:out value='${latestProfilePlay.url}'/>" frameborder="0" allowfullscreen></iframe>
					</div>
				
				<p>
					<fmt:formatDate pattern="dd MMMM yyyy" value="${latestProfilePlay.created}" />
				</p>
			</div>
			
			<div class="news-v2-desc">
				<h3>
					<a href='<c:url value='/plays/view/${latestProfilePlay.id}/'/>'><c:out value="${latestProfilePlay.title}" /></a>
				</h3>
				<small>By <a href="<c:url value='/profile/${play.playProfile.name}'/>"><c:out value="${play.playProfile.name}" /></a> | In <a href="<c:url value='/playTypes/${play.playType.name}'/>"><c:out value="${play.playType.name}" /></a></small>
				
			</div>
		
		</div>
	
	</c:forEach>

</c:if>

</div>
<!-- End Authored Blog -->
<div id="commentingSection" class="min-height-256"></div>

<script type="text/javascript">


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
			    	          data: {id : "${play.publication.id}"},
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


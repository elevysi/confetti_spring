<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>
<c:url value="/admin/featureItem" var="featureUrl" />
<c:url value="/admin/unfeatureItem" var="unfeatureUrl" />
<c:url value='/albums/delete/${album.id}' var="deleteAlbumUrl" />
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
<%-- <a class="fancybox img-hover-v1" title="Image 1" rel="gallery" href="<c:url value='/resources_1_9_5/img/main/img12.jpg' />">
<span><img alt="" src="<c:url value='/resources_1_9_5/img/main/img12.jpg' />" class="img-responsive"></span>
</a>

<a class="fancybox" href="http://www.example/data.php">This takes content using ajax</a> --%>

<!-- Three Columns -->
		<div class="container content">
			<div class="text-center margin-bottom-50">
				<h2 class="title-v2 title-center"><c:out value="${album.name}" /></h2>
				<p class="space-lg-hor"><c:out value="${album.description}" /></p>
				<p><span class="color-green"><c:out value="${album.place}" /></span></p>
				
				<div class="row">
					<div class="col-md-4">
					
						<c:if test="${canEditAlbum || isAdmin}">
							<a class="btn-u rounded btn-u-dark-blue btn-u-xs"  href="<spring:url value='/albums/edit/${album.id}' />"><i class="fa fa-magic"></i> Edit Album</a>
						</c:if>
					</div>
					
					<div class="col-md-4">
					
						<c:if test="${canEditAlbum || isAdmin}">
							<form:form method="post" action="${deleteAlbumUrl}" onsubmit="return confirmDelete(this, '${deleteAlbumUrl}')">
								<button class="btn-u rounded btn-u-dark-blue btn-u-xs"><i class="fa fa-magic"></i> Delete Album</button>
							</form:form>
						
						</c:if>
					</div>
					
					<div class="col-md-4">
						<c:if test="${isAdmin}">
							<c:choose>
								<c:when test="${album.publication.featured}">
									<a class="btn-u rounded btn-u-dark-blue btn-u-xs" id="unfeatureBtn"  href="<spring:url value='/admin/unfeatureItem' />"><i class="fa fa-magic"></i> Unfeature publication</a>
								</c:when>
								<c:otherwise>
									<a class="btn-u rounded btn-u-dark-blue btn-u-xs" id="unfeatureBtn"  href="<spring:url value='/admin/featureItem' />"><i class="fa fa-magic"></i> Feature publication</a>
								</c:otherwise>
							
							</c:choose>
						</c:if>
					</div>			
				</div>
				
			</div>
			
			
			<fmt:parseNumber var="count" value="0"/>
			<fmt:parseNumber var="noColumns" value="3"/>
			<fmt:parseNumber var="starter" value="0"/>
			<fmt:parseNumber var="unit" value="1"/>
			<fmt:parseNumber var="uploadsSize" value="${fn:length(albumUploads)}"/>
			
			<c:forEach items="${albumUploads}" var="upload">
			
				
				
					<c:if test="${count == starter || (count%noColumns)==starter}"><div class="row  margin-bottom-30"></c:if>
					
					
				
				
				<div class="col-sm-4 sm-margin-bottom-30">
					<%--<a  class="fancybox img-hover-v1" href="<c:url value='/uploads/download?key=${upload.keyIdentification}'/>" rel="gallery1" title="${upload.filename}"> 
					 <a  class="ajaxFancyBox img-hover-v1" rel="gallery1" title="${upload.filename}" href="<c:url value='/uploads/download?key=${upload.keyIdentification}'/>">--%>
					
					<a  class="fancybox img-hover-v1" rel="gallery1" title="${upload.filename}" href="">
						<span><img class="img-responsive" src="<c:url value='/uploads/download?key=${upload.keyIdentification}'/>" alt="${upload.altText}"></span>
					</a>
				</div>
				
				
				
					<c:if test="${(count+unit) == uploadsSize || (count+unit)%noColumns==starter}"></div></c:if>
					
					
				
			
				<fmt:parseNumber var="count" value="${count + 1}" />
			</c:forEach>
</div>

<script type="text/javascript">



$(document).ready(function(){

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
			    	          data: {id : "${album.publication.id}"},
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
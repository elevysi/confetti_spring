<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../layout/taglib.jsp" %>

<spring:eval expression="@environment.getProperty('socialService.url')" var="socialServiceUrl" />

<c:url var="baseUrl" value="/?page=" />
<tiles:putAttribute name="basePaginationUrl" value="${baseUrl}" cascade="true"/>

<div class="g-pr-20--lg">
  <!-- Blog Minimal Blocks -->
  
  <c:forEach items="${publications}" var="publication">
  
	  <article class="g-mb-100">
	    <div class="g-mb-30">
	      <div class="media g-mb-25">
	      
	      	<c:choose>
				<c:when test="${not empty publication.profile.profilePicture}">
					<img class="d-flex g-width-40 g-height-40 rounded-circle mr-2" src="<c:url value='${socialServiceUrl}/ui/uploads/download?key=${publication.profile.profilePicture.iterator().next().keyIdentification}'/>" alt="${publication.profile.profilePicture.iterator().next().altText}">
				</c:when>
				<c:otherwise>
					 <img class="d-flex g-width-40 g-height-40 rounded-circle mr-2" src="<c:url value='/resources_2_5/img-temp/100x100/img7.jpg'/>" alt="Image Description">
				</c:otherwise>
			</c:choose>
	      
	        <div class="media-body">
	          <h4 class="h6 g-color-primary mb-0"><a href="<c:url value='${socialServiceUrl}/ui/public/profile/${publication.profileName}'/>"><c:out value="${publication.profileName}"/></a></h4>
	          <c:if test="${not empty publication.created}">
					 <span class="d-block g-color-gray-dark-v4 g-font-size-12">
					<fmt:formatDate pattern="dd MMMM yy" value="${publication.created}" />
					</span>
				</c:if>
	        </div>
	      </div>
	      
	      <c:choose>
	      	<c:when test="${not empty publication.uploads}">
				<!-- img-responsive full-width -->
				<img class="img-fluid w-100 g-mb-25" src="<c:url value='/uploads/download?key=${post.publication.uploads.iterator().next().keyIdentification}'/>" alt="${post.publication.uploads.iterator().next().altText}">
			</c:when>
			<c:when test="${not empty publication.play}">
				<iframe class="img-fluid w-100 g-mb-25" src="<c:out value='${publication.play.url}'/>" frameborder="0" allowfullscreen></iframe>
			</c:when>
			
	      	<c:otherwise>
				 <img class="img-fluid w-100 g-mb-25" src="<c:url value='/resources_2_5/img-temp/740x380/img1.jpg'/>" alt="Image Description">
			</c:otherwise>
	      </c:choose>
	      
	      <c:choose>
	      	<c:when test="${not empty publication.post}">
	      		<c:set var="publicationTitle" value="${publication.post.title}" />
	      		<c:set var="publicationDescription" value="${publication.post.description}" />
			</c:when>
			<c:when test="${not empty publication.play}">
				<c:set var="publicationTitle" value="${publication.play.title}" />
				<c:set var="publicationDescription" value="${publication.play.description}" />
			</c:when>
	      	<c:when test="${not empty publication.dossier}">
	      		<c:set var="publicationTitle" value="${publication.dossier.title}" />
	      		<c:set var="publicationDescription" value="${publication.dossier.description}"/>
			</c:when>
	      </c:choose>
	
	      <h2 class="h4 g-color-black g-font-weight-600 mb-3"><a class="u-link-v5 g-color-black g-color-primary--hover" href="<c:url value='/public/publication/view/${publication.id}/${publication.friendlyUrl}'/>"><c:out value="${publicationTitle}"/></a></h2>
	      
	      
	      <p class="g-color-gray-dark-v4 g-line-height-1_8"><c:out value="${publicationDescription}" escapeXml="false"/></p>
	      <a class="g-font-size-13" href="<c:url value='/public/publication/view/${publication.id}/${publication.friendlyUrl}'/>">Read more...</a>
	    </div>
	
	    <ul class="d-flex justify-content-end list-inline g-brd-y g-brd-gray-light-v3 g-font-size-13 g-py-13 mb-0">
	      <li class="list-inline-item mr-auto">
	        <i class="align-middle g-color-primary g-font-size-default mr-1 icon-medical-022 u-line-icon-pro"></i>
	        <a class="g-color-gray-dark-v4 g-color-primary--hover g-transition-0_3 g-text-underline--none--hover" href="#!"></a>
	      </li>
	      <li class="list-inline-item mr-4">
	        <i class="align-middle g-color-primary g-font-size-default mr-1 icon-finance-206 u-line-icon-pro"></i>
	        <a class="d-inline-block g-color-gray-dark-v4 g-text-underline--none--hover" href="#!">Comments</a>
	      </li>
	      <li class="list-inline-item">
	        <i class="align-middle g-color-primary g-font-size-default mr-1 fa fa-bookmark-o"></i>
	        <a class="d-inline-block g-color-gray-dark-v4 g-text-underline--none--hover" href="#!">Share</a>
	      </li>
	    </ul>
	  </article>
	  <!-- End Blog Minimal Blocks -->
  
  </c:forEach>
<tiles:insertAttribute name="pagination" ignore="true"/>
</div>

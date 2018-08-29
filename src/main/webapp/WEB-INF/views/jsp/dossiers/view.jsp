<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>
<c:url value='/dossiers/delete/${dossier.id}' var="deleteDossierUrl" />
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />

<fmt:parseNumber var="count" value="0" />
<fmt:parseNumber var="noColumns" value="4" />
<fmt:parseNumber var="starter" value="0" />
<fmt:parseNumber var="unit" value="1" />
<fmt:parseNumber var="even" value="2" />
<fmt:parseNumber var="itemsSize" value="${fn:length(dossier.publications)}" />
<spring:eval expression="@environment.getProperty('socialService.url')" var="socialServiceUrl" />


<!-- News v3 -->
<div class="news-v3 bg-color-white margin-bottom-30">
	<c:choose>
		<c:when test="${not empty dossier.publication.uploads && fn:length(dossier.publication.uploads) > 1}">
			<div class="carousel slide carousel-v2" id="portfolio-carousel">
				<ol class="carousel-indicators">
					<fmt:parseNumber var="licount" value="1" />
					<fmt:parseNumber var="activeLiItemIndex" value="1" />
					<c:forEach items="${dossier.publication.uploads}" var="upload">

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
					<c:forEach items="${dossier.publication.uploads}" var="upload">

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


		<c:when test="${not empty dossier.publication.uploads && fn:length(dossier.publication.uploads) == 1}">
			<!-- img-responsive full-width -->
			<img class="img-responsive full-width" src="<c:url value='/uploads/download?key=${dossier.publication.uploads.iterator().next().keyIdentification}'/>" alt="${dossier.publication.uploads.iterator().next().altText}">
		</c:when>
	</c:choose>
	
</div>


<div class="news-v3-in">
	<ul class="list-inline posted-info">
		<li>By <a href="<c:url value='${socialServiceUrl}/ui/public/profile/${dossier.publication.profile.name}'/>"><c:out
					value="${dossier.publication.profile.name}" /></a></li>

		<c:if test="${not empty dossier.publication.categories}">
			<li>Tags: <c:forEach items="${dossier.publication.categories}" var="postCategory"
					varStatus="stat">


					<c:url var="itemUrl" value="/public/tag/${postCategory.name}" />
					<c:set var="itemText" value="${postCategory.name}" />

					<c:set var="itemDisplay"
						value="<a href='${itemUrl}'>${itemText}</a>" />

					<c:set var="categoryDisplay"
						value="${categoryDisplay}${stat.first ? '' : ', '} ${itemDisplay}" />

				</c:forEach>
				<c:out value="${categoryDisplay}" escapeXml="false" />
			</li>
		</c:if>

		<li>Posted <fmt:formatDate pattern="dd MMMM yyyy"
				value="${dossier.created}" />
		</li>
	</ul>
	<h2>
		<a href="<c:url value='/dossiers/view/${dossier.id}/'/>"><c:out
				value="${dossier.name}" /></a>
	</h2>
	<p>
		<c:out value="${dossier.description}" escapeXml="false" />
	</p>
	<div class="headline"><h2>Articles</h2></div>
	<ul class="list">
	
		<c:forEach items="${dossier.publications}" var="publication">
			<li><a href="<c:url value='/public/publication/view/${publication.id}/${publication.friendlyUrl}' />" ><c:out value="${publication.friendlyUrl}" /></a></li>
		</c:forEach>
		
	</ul>
	
	
	<c:if test="${canEditDossier || isAdmin}">
		<div class="clearfix margin-bottom-20"><hr></div>
		<ul class="list-inline up-ul">
			<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-yellow btn-u-sm"  href="<spring:url value='/dossiers/edit/${dossier.id}' />"><i class="fa fa-edit"></i> Edit Dossier</a></li>
			<li>
				<form:form method="post" action="${deleteDossierUrl}" onsubmit="return confirmDelete(this, '${deleteDossierUrl}')" class="form-inline">
					<button class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-red btn-u-sm"><i class="fa fa-magic"></i> Delete Dossier</button>
				</form:form>
			</li>
		</ul>
		<div class="clearfix margin-bottom-20"><hr></div>
	</c:if>
	
</div>

<!-- End News v3 -->

<!-- Blog Post Author -->
<div class="blog-author margin-bottom-30">

	<c:choose>
		<c:when test="${not empty dossier.publication.profile.profilePicture && fn:length(dossier.publication.profile.profilePicture) >= 1}">
			<img src="<c:url value='${socialServiceUrl}/ui/uploads/download?key=${dossier.publication.profile.profilePicture.iterator().next().keyIdentification}'/>" alt="${dossier.publication.profile.profilePicture.iterator().next().altText}">

		</c:when>
		<c:otherwise>

			<img src="<c:url value='/resources_1_8/img/team/img1-sm.jpg'/>" alt="">
		</c:otherwise>
	</c:choose>


	<div class="blog-author-desc">
		<div class="overflow-h">
			<h4>
				<c:out value="${dossier.publication.profile.name}" />
			</h4>
			<ul class="list-inline">
				<li><a href="#"><i class="fa fa-facebook"></i></a></li>
				<li><a href="#"><i class="fa fa-twitter"></i></a></li>
				<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
			</ul>
		</div>
		<p>
			<c:out value="${dossier.profile.description}" />
		</p>
	</div>
</div>
<!-- End Blog Post Author -->

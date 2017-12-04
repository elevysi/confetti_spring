<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../layout/taglib.jsp" %>
<%@ page session="false" %>



<fmt:parseNumber var="count" value="0"/>
<fmt:parseNumber var="countFeat" value=
"0"/>
<fmt:parseNumber var="noColumns" value="4"/>
<fmt:parseNumber var="noColumnsFeat" value="2"/>
<fmt:parseNumber var="starter" value="0"/>
<fmt:parseNumber var="unit" value="1"/>
<fmt:parseNumber var="videosSize" value="${fn:length(videoPlays)}"/>
<fmt:parseNumber var="albumsSize" value="${fn:length(albums)}"/>
<fmt:parseNumber var="featuredSize" value="${fn:length(featuredPublications)}"/>

				<!-- Blog Grid -->
				<div class="margin-bottom-50">
					<h2 class="title-v4">Featured Items </h2>
					
						
						<c:forEach items="${featuredPublications}" var="featuredItem">
						<c:choose>
				
							<c:when test="${countFeat == starter}"><div class="row margin-bottom-50"></c:when>
							<c:otherwise>
								<c:if test="${(countFeat%noColumnsFeat)==starter}"><div class="row margin-bottom-50"></c:if>
							</c:otherwise>
							
						</c:choose>
						
						<c:choose>
							<c:when test="${not empty featuredItem.post}">
							
								
								<div class="col-sm-6 sm-margin-bottom-50">
									<!-- Blog Grid -->
											<div class="blog-grid margin-bottom-40">
											
												<c:choose>
													<c:when test="${not empty featuredItem.post.uploads && fn:length(featuredItem.post.uploads) >= 1}">
														<img class="img-responsive" src="<c:url value='/uploads/download?key=${featuredItem.post.uploads.iterator().next().keyIdentification}'/>" alt="">
													</c:when>
												<c:otherwise>
												</c:otherwise>
												</c:choose>
												
												<h3><a href="<spring:url value='/posts/view/${featuredItem.post.id}/${featuredItem.friendlyUrl}'/>"><c:out value="${featuredItem.post.title}" /></a></h3>
												<ul class="blog-grid-info">
													<li><a href="<c:url value='/public/profile/${featuredItem.profile.name}'/>"><c:out value="${featuredItem.profile.name}"/></a></li>
													<c:if test="${not empty featuredItem.post.created}">
														<li>
														<fmt:formatDate pattern="dd MMMM yy" value="${featuredItem.post.created}" />
														</li>
													</c:if>
													<li><a href="#"><i class="fa fa-comments"></i> 0</a></li>
												</ul>
												<p><c:out value="${featuredItem.post.description}" escapeXml="false"/></p>
												<a class="r-more" href="<spring:url value='/posts/view/${featuredItem.post.id}/${featuredItem.friendlyUrl}'/>">Read More</a>
											</div>
								
									
									<div class="blog-grid margin-bottom-40">
										
									</div>
									<!-- End Blog Grid -->

								</div>
							</c:when>
							<c:when test="${not empty featuredItem.play}">
								<div class="col-sm-6 sm-margin-bottom-50">
									<div class="news-v2-badge">
							
										<div class="responsive-video">
											<iframe src="<c:out value='${featuredItem.play.url}'/>" frameborder="0" allowfullscreen></iframe>
										</div>
									</div>
									
									<div class="news-v2-desc">
										<h3>
											<a href='<c:url value='/plays/view/${featuredItem.play.id}/${featuredItem.friendlyUrl}'/>'><c:out value="${featuredItem.play.title}" /></a>
										</h3>
										<small>By <a href="<c:url value='/public/profile/${featuredItem.profile.name}'/>"><c:out value="${featuredItem.profile.name}" /></a> | In <a href="#"><c:out value="${featuredItem.play.playType.name}" /></a>
										| 
										<c:if test="${not empty featuredItem.play.created}">
											<fmt:formatDate pattern="dd MMMM yy" value="${featuredItem.play.created}" />
										</c:if>
										</small>
									</div>
								</div>
							
							</c:when>
							<c:when test="${not empty featuredItem.album}">
							
								<div class="col-sm-6 sm-margin-bottom-50">
								
										<div class="blog-grid">
						
										<c:choose>
											<c:when test="${not empty featuredItem.album.uploads && fn:length(featuredItem.album.uploads) >= 1}">
												<img alt="" src="<c:url value='/uploads/download?key=${featuredItem.album.uploads.iterator().next().keyIdentification}'/>" class="img-responsive">
											</c:when>
											<c:otherwise>
												<img alt="" src="<c:url value='/resources_1_9_5/img/team/img10-md.jpg'/>" class="img-responsive">
											</c:otherwise>
										</c:choose>
									
										
										<h3><a href="<c:url value='/albums/view/${featuredItem.album.id}/' />"><c:out value="${featuredItem.album.name}" /></a></h3>
										<ul class="blog-grid-info">
											<li><c:out value="${fn:length(featuredItem.album.uploads)} photos by ${featuredItem.profile.name}" /></li>
											<li><fmt:formatDate pattern="dd MMMM yy" value="${featuredItem.album.created}" /></li>
											<li><a href="#"><i class="fa fa-comments"></i> 0</a></li>
										</ul>
									</div>
								</div>
							</c:when>						
						</c:choose>
						
							<c:choose>
				
								<c:when test="${(countFeat+unit) == featuredSize}"></div></c:when>
								<c:otherwise>
									<c:if test="${(countFeat+unit)%noColumnsFeat==starter}"></div></c:if>
								</c:otherwise>
								
							</c:choose>
							<fmt:parseNumber var="countFeat" value="${countFeat + 1}" />
						
						</c:forEach>

						
					
				</div>
				<!-- End Blog Grid -->
				
				
				<a class="r-more" href="<spring:url value='/public/posts'/>"><span class="glyphicon glyphicon-hand-right"></span> Discover All Posts</a>
				<hr />
				
				<!-- Blog Carousel Heading -->
				<div class="blog-cars-heading">
					<h2>Dossiers</h2>
					<div class="owl-navigation">
						<div class="customNavigation">
							<a class="owl-btn prev-v3"><i class="fa fa-angle-left"></i></a>
							<a class="owl-btn next-v3"><i class="fa fa-angle-right"></i></a>
						</div>
					</div><!--/navigation-->
				</div>
				<!-- End Blog Carousel Heading -->

				<!-- Blog Carousel -->
				<div class="blog-carousel margin-bottom-50">
					

					<c:forEach items="${dossiers}" var="dossier" >
						<!-- Blog Grid -->
						<div class="item">
							<div class="row">
								<div class="col-sm-5 sm-margin-bottom-20">
									<c:choose>
										<c:when test="${not empty dossier.uploads && fn:length(dossier.uploads) >= 1}">
											<img class="img-responsive" src="<c:url value='/uploads/download?key=${dossier.uploads.iterator().next().keyIdentification}'/>" alt="">
										</c:when>
										<c:otherwise>
											<img class="img-responsive" src="<c:url value='/resources_1_9_5/img/main/img22.jpg' />" alt="">
										</c:otherwise>
										
									</c:choose>
								
									
									</div>
								<div class="col-sm-7">
									<div class="blog-grid">
										<h3><a href="<c:url value='/dossiers/view/${dossier.id}/' />"><c:out value="${dossier.name}" /></a></h3>
										<ul class="blog-grid-info">
											<li></li>
											<li><fmt:formatDate pattern="dd MMMM yy" value="${dossier.created}" /></li>
											<li><a href="#"><i class="fa fa-comments"></i> 0</a></li>
										</ul>
										<p><c:out value='${dossier.description}' escapeXml="false"/></p>
										<a class="r-more" href="<c:url value='/dossiers/view/${dossier.id}/' />">Discover</a>
									</div>
								</div>
							</div>
						</div>
						<!-- End Blog Grid -->
					</c:forEach>

					
				</div>
				<!-- End Blog Carousel -->
				
				<a class="r-more" href="<spring:url value='/public/dossiers'/>"><span class="glyphicon glyphicon-hand-right"></span> Discover All Dossiers</a>
				<hr />

				<!-- Blog Thumb v4 -->
				<div class="margin-bottom-50">
					<h2 class="title-v4">Weekly Plays</h2>
					
					<c:forEach items="${videoPlays}" var="featuredVideo">
					
					<c:choose>
				
						<c:when test="${count == starter}"><div class="row news-v2 margin-bottom-50"></c:when>
						<c:otherwise>
							<c:if test="${(count%noColumns)==starter}"><div class="row news-v2 margin-bottom-50"></c:if>
						</c:otherwise>
						
					</c:choose>
	
						<div class="col-sm-3 sm-margin-bottom-30">
							<div class="news-v2-badge">
							
								<div class="responsive-video">
									<iframe src="<c:out value='${featuredVideo.url}'/>" frameborder="0" allowfullscreen></iframe>
								</div>
								<c:if test="${not empty featuredVideo.created}">
									<p>
										<fmt:formatDate pattern="dd MMMM yyyy" value="${featuredVideo.created}" />
									</p>
								</c:if>
							</div>
							
							<div class="news-v2-desc">
								<h3>
									<a href='<c:url value='/plays/view/${featuredVideo.id}/${featuredVideo.publication.friendlyUrl}'/>'><c:out value="${featuredVideo.title}" /></a>
								</h3>
								<small>By <a href="<c:url value='/profile/${featuredVideo.playProfile.name}' />" ><c:out value="${featuredVideo.playProfile.name}" /></a> | In <a href="#"><c:out value="${featuredVideo.playType.name}" /></a></small>
							</div>
						
						</div>
						
						<c:choose>
				
							<c:when test="${(count+unit) == videosSize}"></div></c:when>
							<c:otherwise>
								<c:if test="${(count+unit)%noColumns==starter}"></div><!--/end row--></c:if>
							</c:otherwise>
							
						</c:choose>
			
					<fmt:parseNumber var="count" value="${count + 1}" />
					
					</c:forEach>
						
					
				</div>
				
				<a class="r-more" href="<spring:url value='/public/plays'/>"><span class="glyphicon glyphicon-hand-right"></span> Discover All Plays</a>
				<hr />
				
				<!-- End Blog Thumb v4 -->

				

				<!-- Pager v4
				<ul class="pager pager-v4 md-margin-bottom-50">
					<li class="previous"><a class="rounded-3x" href="#">&larr; Older</a></li>
					<li class="page-amount">1 of 7</li>
					<li class="next"><a class="rounded-3x" href="#">Newer &rarr;</a></li>
				</ul>
				End Pager v4 -->
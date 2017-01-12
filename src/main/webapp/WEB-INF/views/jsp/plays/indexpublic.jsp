<%@ include file="../../layout/taglib.jsp"%>

<c:url var="baseUrl" value="/public/plays?page=" />
<tiles:putAttribute name="basePaginationUrl" value="${baseUrl}" cascade="true"/>


<fmt:parseNumber var="count" value="0" />
<fmt:parseNumber var="noColumns" value="4" />
<fmt:parseNumber var="starter" value="0" />
<fmt:parseNumber var="unit" value="1" />
<fmt:parseNumber var="even" value="2" />
<fmt:parseNumber var="playsSize" value="${fn:length(plays)}" />

<!-- Blog Thumb v4 -->
				<div class="margin-bottom-50">
					<h2 class="title-v4">Plays Confettis</h2>
					
					<c:if test="${count == starter || (count%noColumns)==starter}">
						<div class="row news-v2 margin-bottom-50">
					</c:if>
					
					<c:forEach items="${plays}" var="play">
					
					
	
						<div class="col-sm-3 sm-margin-bottom-30">
							<div class="news-v2-badge">
							
								<div class="responsive-video">
									<iframe src="<c:out value='${play.url}'/>" frameborder="0" allowfullscreen></iframe>
								</div>
								
								<p>
									<fmt:formatDate pattern="dd MM yy" value="${play.created}" />
								</p>
							</div>
							
							<div class="news-v2-desc">
								<h3>
									<a href='<c:url value='/plays/view/${play.id}/${play.publication.friendlyUrl}'/>'><c:out value="${play.title}" /></a>
								</h3>
								<small>By <a href="<c:url value='/profile/${play.playProfile.name}' />" ><c:out value="${play.playProfile.name}" /></a> | In <a href="#"><c:out value="${play.playType.name}" /></a></small>
							</div>
						
						</div>
						
						<c:if test="${((count+unit) == playsSize) || ((count+unit)%noColumns==starter)}">
							</div>
						</c:if>
						
			
						<fmt:parseNumber var="count" value="${count + 1}" />
					
					</c:forEach>
						
					
				</div>
				<!-- End Blog Thumb v4 -->

<tiles:insertAttribute name="pagination" ignore="true"/>

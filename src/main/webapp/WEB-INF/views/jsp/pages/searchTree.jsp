<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/taglib.jsp"%>

<!--=== Search Block Version 2 ===-->
    <div class="search-block-v2">
                              
        <div class="container">
            <div class="col-md-6 col-md-offset-3">
                <h2>Search again</h2>
                <form action="<spring:url value="/public/search/tree"/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="input-group">
                
                    <input type="text" class="form-control" placeholder="Search users, posts ..." name="globalSearch">
                    <span class="input-group-btn">
                        <button class="btn-u" type="submit"><i class="fa fa-search"></i></button>
                    </span>
                </div>
                </form>
            </div>
        </div>
    </div><!--/container-->
    <!--=== End Search Block Version 2 ===-->


<span class="results-number"><c:out value="${fn:length(searchResults)}"/> results for <c:out
		value="${searchTerm}"></c:out></span>


<div class="tab-v2">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#postsTab" data-toggle="tab" aria-expanded="true">Posts</a></li>
		<li class=""><a href="#playsTab" data-toggle="tab" aria-expanded="true">Plays</a></li>
		<li class=""><a href="#profilesTab" data-toggle="tab" aria-expanded="false">Profiles</a></li>
		<li class=""><a href="#usersTab" data-toggle="tab" aria-expanded="false">Users</a></li>
		<li class=""><a href="#messagesTab" data-toggle="tab" aria-expanded="false">Messages</a></li>
		<li class=""><a href="#shopTab" data-toggle="tab" aria-expanded="false">Shop</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane fade active in" id="postsTab">
			<h4>Posts</h4>
			<c:if test="${not empty postRoot.children}">
				
				<c:forEach items="${postRoot.children}" var="node">
				
					<!-- Begin Inner Results -->
					<div class="inner-results">
						<h3>
							<a class="color-green" href="<spring:url value='/posts/view/${node.domainObject.id}/'/>"><c:out value="${node.domainObject.title}" /></a>
						</h3>
						<ul class="list-inline up-ul">
							
								<li><a class="btn-u btn-brd btn-brd-hover rounded btn-u-dark-blue btn-u-sm" href="<c:url value='/posts/view/${node.domainObject.id}'/>">Share</a></li>
								<li><a class="btn-u btn-brd btn-brd-hover rounded btn-u-dark btn-u-sm" href="<c:url value='/posts/view/${node.domainObject.id}'/>">Comment</a></li>
								<li><a class="btn-u btn-brd btn-brd-hover rounded btn-u-green btn-u-sm" href='<c:url value="/posts/view/${node.domainObject.id}"/>' class="btn-u btn-u-purple">Read</a></li>
							
						</ul>
						<div class="overflow-h">
						
							<img src="<c:url value='/uploads/download?key=${node.avatar.keyIdentification}'/>"
								alt="${node.avatar.altText}">
							<div class="overflow-a">
								<p><c:out value="${fn:substring(node.domainObject.content, 0, 300)}" escapeXml="false"/></p>
								<ul class="list-inline down-ul">
									<li>
										<ul class="list-inline star-vote">
											<li><i class="color-green fa fa-star"></i></li>
											<li><i class="color-green fa fa-star"></i></li>
											<li><i class="color-green fa fa-star"></i></li>
											<li><i class="color-green fa fa-star"></i></li>
											<li><i class="color-green fa fa-star-half-o"></i></li>
										</ul>
									</li>
									
									<li><c:out value="${node.domainObject.profile.name}"/></li>
									
								</ul>
							</div>
						</div>
					</div>
					<!-- Begin Inner Results -->
				
				
					
				</c:forEach>
			</c:if>
		</div>
		
		<div class="tab-pane fade" id="playsTab">
		</div>
		
		
		<div class="tab-pane fade" id="profilesTab">
		</div>
		
		<div class="tab-pane fade" id="usersTab">
			<h4>Users</h4>
			<c:if test="${not empty userRoot.children}">
				
				<c:forEach items="${userRoot.children}" var="node">
				
					<!-- Begin Inner Results -->
					<div class="inner-results">
						<h3>
							<a class="color-green" href="<spring:url value='/users/viewProfile/${node.domainObject.id}/'/>"><c:out value="${node.domainObject.first_name}" /></a>
						</h3>
						<ul class="list-inline up-ul">
							
								<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-dark-blue btn-u-sm" href="<c:url value='/profile/${node.domainObject.first_name}' />">View</a></li>
								<li><button href="#" class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-purple btn-u-sm">Add to confetti bucket</button></li>
								<li><button href="<c:url value='/profile/follow/'/>" class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-green btn-u-sm">Follow</button></li>
						</ul>
						<div class="overflow-h">
						
							<img src="<c:url value='/uploads/download?key=${node.avatar.keyIdentification}'/>"
								alt="${node.avatar.altText}">
							<div class="overflow-a">
								<%-- <p><c:out value="${fn:substring(node.domainObject.content, 0, 300)}" escapeXml="false"/></p> --%>
								<ul class="list-inline down-ul">
									<li>
										<ul class="list-inline star-vote">
											<li><i class="color-green fa fa-star"></i></li>
											<li><i class="color-green fa fa-star"></i></li>
											<li><i class="color-green fa fa-star"></i></li>
											<li><i class="color-green fa fa-star"></i></li>
											<li><i class="color-green fa fa-star-half-o"></i></li>
										</ul>
									</li>
									
									
								</ul>
							</div>
						</div>
					</div>
					<!-- Begin Inner Results -->
				</c:forEach>
			</c:if>
		</div>
		
		<div class="tab-pane fade" id="messagesTab">
		</div>
		<div class="tab-pane fade" id="shopTab">
		</div>
	</div>
</div>
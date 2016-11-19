<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/taglib.jsp"%>

<!--=== Search Block Version 2 ===-->
    <div class="search-block-v2">
                              
        <div class="container">
            <div class="col-md-6 col-md-offset-3">
                <h2>Search again</h2>
                <form action="<spring:url value="/public/search"/>" method="post">
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
<c:choose>
	<c:when test="${not empty searchResults}">
		<c:forEach items="${searchResults}" var="searchResult">
			<c:choose>
				<c:when test="${not empty searchResult.upload}">

					<!-- Begin Inner Results -->
					<div class="inner-results">
						<h3>
							<c:choose>
								<c:when test="${searchResult.type == 'user'}">
									<a class="color-green" href="<spring:url value='/users/viewProfile/${searchResult.index}'/>"><c:out
											value="${searchResult.heading}" /> </a>
								</c:when>
								<c:when test="${searchResult.type == 'post'}">
									<a class="color-green" href="<spring:url value='/posts/view/${searchResult.index}'/>"><c:out
											value="${searchResult.heading}" /></a>
								</c:when>
							</c:choose>
						</h3>
						<ul class="list-inline up-ul">
							
							<c:choose>
								<c:when test="${searchResult.type == 'user'}">
									<li><button class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-dark-blue btn-u-sm" href="#">View</button></li>
									<li><button href="#" class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-purple btn-u-sm">Add to confetti bucket</button></li>
									<li><button href="<c:url value='/profile/follow/'/>" class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-green btn-u-sm">Follow</button></li>
								</c:when>
								<c:when test="${searchResult.type == 'post'}">
									<li><button class="btn-u btn-brd btn-brd-hover rounded btn-u-dark-blue btn-u-sm" href="<c:url value='/posts/view/${searchResult.index}'/>">Share</button></li>
									<li><button class="btn-u btn-brd btn-brd-hover rounded btn-u-dark btn-u-sm" href="<c:url value='/posts/view/${searchResult.index}'/>">Comment</button></li>
									<li><button class="btn-u btn-brd btn-brd-hover rounded btn-u-green btn-u-sm" href='<c:url value="/posts/view/${searchResult.index}"/>' class="btn-u btn-u-purple">Read</button></li>
								</c:when>
							</c:choose>
							
						</ul>
						<div class="overflow-h">
						
							<img src="<c:url value='/uploads/download?key=${searchResult.upload.keyIdentification}'/>"
								alt="${searchResult.upload.altText}">
							<div class="overflow-a">
								<p><c:out value="${fn:substring(searchResult.details, 0, 300)}" escapeXml="false"/></p>
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
									<c:choose>
										<c:when test="${searchResult.type == 'user'}">
											<li>11 months ago - By WrapBootstrap</li>
										</c:when>
										<c:when test="${searchResult.type == 'post'}">
											<li><c:out value="${searchResult.object.profile.name}"/></li>
										</c:when>
									</c:choose>
									
									<li>2,092,675 views</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- Begin Inner Results -->


				</c:when>
				<c:otherwise>

					<!-- Begin Inner Results -->
					<div class="inner-results">
						<h3>
							<c:choose>
								<c:when test="${searchResult.type == 'user'}">
									<a href="<spring:url value='/profile/${searchResult.index}'/>"><c:out
											value="${searchResult.heading}" /> </a>
								</c:when>
								<c:when test="${searchResult.type == 'post'}">
									<a href="<spring:url value='/posts/view/${searchResult.index}'/>"><c:out
											value="${searchResult.heading}" /></a>
								</c:when>
							</c:choose>
						</h3>
						<ul class="list-inline up-ul">
							<li class="btn-group">
								<button data-toggle="dropdown"
									class="btn btn-default dropdown-toggle" type="button">
									More<i class="fa fa-caret-down margin-left-5"></i> <span
										class="sr-only">Toggle Dropdown</span>
								</button>
								<ul role="menu" class="dropdown-menu">
									<li><a href="#">Share</a></li>
									<li><a href="#">Similar</a></li>
									<li><a href="#">Advanced search</a></li>
								</ul>
							</li>
							<li><a href="#">Wrapbootstrap</a></li>
							<li><a href="#">Dribbble</a></li>
						</ul>
						<p>
							<c:out value="${fn:substring(searchResult.details, 0, 300)}" escapeXml="false"/>
						</p>
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
							<li>3 years ago - By Anthon Brandley</li>
							<li>234,034 views</li>
							<li><a href="#">Web designer</a></li>
						</ul>
					</div>
					<!-- Begin Inner Results -->


				</c:otherwise>

			</c:choose>


			<hr>

		</c:forEach>

		<div class="margin-bottom-30"></div>

		<div class="text-left">
			<ul class="pagination">
				<li><a href="#">«</a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">...</a></li>
				<li><a href="#">157</a></li>
				<li><a href="#">158</a></li>
				<li><a href="#">»</a></li>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<p class="alert alert-danger">
			No results found for term "
			<c:out value="${searchTerm}" />
			"
		</p>
	</c:otherwise>
</c:choose>
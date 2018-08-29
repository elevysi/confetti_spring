<%@ include file="../../layout/taglib.jsp"%>


<c:set var="baseUrl"><tiles:getAsString name='basePaginationUrl' /></c:set>
<c:if test="${not empty page}" >
<fmt:parseNumber var="noLinksPerSide" value="2"/>
<fmt:parseNumber var="nullNo" value="0"/>
<fmt:parseNumber var="firstDisplay" value="1"/>


 <!-- Pagination -->
  <nav id="stickyblock-end" class="text-center" aria-label="Page Navigation">
    <ul class="list-inline">
      <c:if test="${page.current > firstDisplay}" >
	      <li class="list-inline-item float-left g-hidden-xs-down">
	        <a class="u-pagination-v1__item u-pagination-v1-4 g-brd-gray-light-v3 g-brd-primary--hover g-rounded-50 g-pa-7-16" href="<c:out value='${baseUrl}${page.current - 1}'/>" aria-label="Previous">
	          <span aria-hidden="true">
	            <i class="fa fa-angle-left g-mr-5"></i> Previous
	          </span>
	          <span class="sr-only">Previous</span>
	        </a>
	      </li>
      </c:if>
      
      <c:if test="${not empty page.current && not empty totalPages}">
		
		
		<c:if test="${ page.current >=  firstDisplay}" >
			<c:choose>
					<c:when test="${page.current - noLinksPerSide >= firstDisplay}">
						<c:forEach begin="${page.current -  noLinksPerSide}" end="${page.current - 1}" var="val">
						    <li class="list-inline-item"><a class="u-pagination-v1__item u-pagination-v1-4 g-rounded-50 g-pa-7-14" href="<c:out value="${baseUrl}${val}" />"><c:out value="${val}"/></a></li>
						</c:forEach>
					</c:when>
					
					<c:otherwise>
					
						<c:forEach begin="${firstDisplay}" end="${page.current - 1}" var="val">
				   			<li class="list-inline-item"><a class="u-pagination-v1__item u-pagination-v1-4 g-rounded-50 g-pa-7-14" href="<c:out value="${baseUrl}${val}" />"><c:out value="${val}"/></a></li>
						</c:forEach>
					
					</c:otherwise>
				
				</c:choose>
		</c:if>
		
		
		<li class="list-inline-item"><a class="u-pagination-v1__item u-pagination-v1-4 u-pagination-v1-4--active g-rounded-50 g-pa-7-14" href="<c:out value="${baseUrl}${page.current}" />"><c:out value="${page.current}" /></a></li>
		
		<c:if test="${ page.current <=  totalPages}" >
		
			<c:choose>
					<c:when test="${page.current + noLinksPerSide <= totalPages}">
						<c:forEach begin="${page.current + 1}" end="${page.current +  noLinksPerSide}" var="val">
						    <li class="list-inline-item"><a class="u-pagination-v1__item u-pagination-v1-4 g-rounded-50 g-pa-7-14" href="<c:out value="${baseUrl}${val}" />"><c:out value="${val}"/></a></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
					
						<c:forEach begin="${page.current + 1}" end="${totalPages}" var="val">
						    <li class="list-inline-item"><a class="u-pagination-v1__item u-pagination-v1-4 g-rounded-50 g-pa-7-14" href="<c:out value="${baseUrl}${val}" />"><c:out value="${val}"/></a></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		
		</c:if>
		
		
	</c:if>
    <c:if test="${page.current < totalPages}" >
	      <li class="list-inline-item float-right g-hidden-xs-down">
	        <a class="u-pagination-v1__item u-pagination-v1-4 g-brd-gray-light-v3 g-brd-primary--hover g-rounded-50 g-pa-7-16" href="<c:out value='${baseUrl}${page.current + 1}'/>" aria-label="Next">
	          <span aria-hidden="true">
	            Next <i class="fa fa-angle-right g-ml-5"></i>
	          </span>
	          <span class="sr-only">Next</span>
	        </a>
	      </li>
	</c:if>
	   
    </ul>
  </nav>
  <!-- End Pagination -->
</c:if>
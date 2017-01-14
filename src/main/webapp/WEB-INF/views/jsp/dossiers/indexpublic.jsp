<%@ include file="../../layout/taglib.jsp"%>

<c:url var="baseUrl" value="/public/dossiers?page=" />
<tiles:putAttribute name="basePaginationUrl" value="${baseUrl}" cascade="true"/>


<fmt:parseNumber var="count" value="0" />
<fmt:parseNumber var="noColumns" value="4" />
<fmt:parseNumber var="starter" value="0" />
<fmt:parseNumber var="unit" value="1" />
<fmt:parseNumber var="even" value="2" />
<fmt:parseNumber var="dossiersSize" value="${fn:length(dossiers)}" />

<div class="headline">
<h2>All Dossiers - <c:out value="${page.totalRecords}" /></h2>
</div>


<c:forEach items="${dossiers}" var="dossier">

	

		<c:if test="${count == starter || (count%noColumns)==starter}">
					<div class="row news-v2">
		</c:if>
		

	

		<div class="col-md-3">
		
		<h2 class="title-v4"><c:out value="${dossier.name}"></c:out></h2>
			<p><c:out value="${dossier.description}" escapeXml="false"/></p>
			
			<p>
				<a class="btn-u btn-brd btn-u-blue" href="<c:url value='/dossiers/view/${dossier.id}/'/>"><i class="fa fa-hand-o-up"></i> Explore</a>
			</p>
			
		</div>
	

	

		<c:if test="${((count+unit) == dossiersSize) || ((count+unit)%noColumns==starter)}">
			</div>
		</c:if>
		

	

	<fmt:parseNumber var="count" value="${count + 1}" />

</c:forEach>

<tiles:insertAttribute name="pagination" ignore="true"/>

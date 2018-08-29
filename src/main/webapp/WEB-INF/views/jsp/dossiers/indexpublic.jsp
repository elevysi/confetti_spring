<%@ include file="../../layout/taglib.jsp"%>

<c:url var="baseUrl" value="/public/dossiers?page=" />
<tiles:putAttribute name="basePaginationUrl" value="${baseUrl}" cascade="true"/>


<div class="headline">
<h2>All Dossiers - <c:out value="${page.totalRecords}" /></h2>
</div>

<%@ include file="../nullLayouts/indexdossiersajax.jsp"%>
<tiles:insertAttribute name="pagination" ignore="true"/>
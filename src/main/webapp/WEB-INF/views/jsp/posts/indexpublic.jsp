<%@ include file="../../layout/taglib.jsp"%>

<c:url var="baseUrl" value="/public/posts?page=" />
<tiles:putAttribute name="basePaginationUrl" value="${baseUrl}" cascade="true"/>


<div class="headline">
<h2>All Posts - <c:out value="${page.totalRecords}" /></h2>
</div>
<%@ include file="../nullLayouts/indexpostsajax.jsp"%>

<tiles:insertAttribute name="pagination" ignore="true"/>
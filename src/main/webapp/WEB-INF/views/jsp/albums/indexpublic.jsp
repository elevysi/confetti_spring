<%@ include file="../../layout/taglib.jsp"%>

<c:url var="baseUrl" value="/public/albums?page=" />
<tiles:putAttribute name="basePaginationUrl" value="${baseUrl}" cascade="true"/>
<link rel="stylesheet" href="<c:url value='/resources_1_9_5/plugins/cube-portfolio/cubeportfolio/css/cubeportfolio.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources_1_9_5/plugins/cube-portfolio/cubeportfolio/custom/custom-cubeportfolio.css'/>">



<div class="headline">
<h2>All Albums - <c:out value="${page.totalRecords}" /></h2>
</div>

<%@ include file="../nullLayouts/indexalbumsajax.jsp"%>

<tiles:insertAttribute name="pagination" ignore="true"/>

<script type="text/javascript" src="<c:url value='/resources_1_9_5/plugins/cube-portfolio/cubeportfolio/js/jquery.cubeportfolio.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources_1_9_5/js/plugins/cube-portfolio/cube-portfolio-lightbox.js'/>"></script>

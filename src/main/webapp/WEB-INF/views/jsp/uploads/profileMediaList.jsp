<%@ include file="../../layout/taglib.jsp"%>
<div class="content">
<div class="row">
	<c:forEach items="${profileUploads}" var="savedUpload" varStatus="status">

		<div class="col-sm-3 sm-margin-bottom-30 image_block">
			
			<c:set var="downloadLink" value="/uploads/download?key=${savedUpload.keyIdentification}"></c:set>
		
			<a class="fancybox img-hover-v1" href="#${savedUpload.keyIdentification}" rel="gallery1" title="${savedUpload.filename}" >
				<span><img class="img-responsive" src="<c:url value='/uploads/download?key=${savedUpload.keyIdentification}' />" alt="${savedUpload.altText}"></span>
			</a>
			
<%-- 			<a class="fancybox img-hover-v1" data-fancybox-type="iframe" href="<c:url value='/uploads/download?key=${savedUpload.keyIdentification}' />" rel="gallery1" title="${savedUpload.filename}" > --%>
			
			
			<div style="display:none;" id="${savedUpload.keyIdentification}" class="modal-inline">
				<img src="<c:url value='/uploads/download?key=${savedUpload.keyIdentification}' />" alt="${savedUpload.altText}">
			</div>


		</div>
		<c:if test="${status.count % 4 == 0}">
			</div>
			<div class="row">
		</c:if>
</c:forEach>
</div>
</div>

<script type="text/javascript">
    
    
    $(document).ready(function() {
    	$(".fancybox").fancybox({
    		openEffect	: 'none',
    		closeEffect	: 'none'
    	});
    });
</script>
<%@ include file="../../layout/taglib.jsp"%>

<a href="<spring:url value='/message/add' />" class="btn-u btn-u-green modalOpen">New Message</a>


<script>

$(document).ready(function(){
	$(".triggerRemove").click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
	});
	
	$(".modalOpen").click(function(e){
		e.preventDefault();
		var link = $(this).attr("href");
		
		$("#modalAdd .modal-body").html("");
		$("#modalAdd .modal-body").addClass("loader");
		
		
		$.get(link, function(html){
			$("#modalAdd .saveBtn").attr("href", link);
			$("#modalAdd .modal-body").removeClass("loader");
			$("#modalAdd .modal-body").html(html);
			$("#modalAdd").modal();
		});
		
		
		
});
});
</script>
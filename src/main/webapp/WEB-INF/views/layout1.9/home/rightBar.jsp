<%@ include file="../../layout/taglib.jsp"%>
<c:url var="getLastCommentsUrl" value="/public/latestComments" />
<c:url var="getPhotoStreamUrl" value="/public/photoStream" />

				<!-- Blog Thumb v3 -->
				<div class="row margin-bottom-50">
					<div id="latestComments">
					</div>
				</div>
				<!-- End Blog Thumb v3 -->
				
				
				<div class="row margin-bottom-50" id="photoStreamDiv">
					

					<!-- Photo Stream -->
					<div class="headline"><h2 class="heading-sm">Photo Stream</h2></div>
					<ul class="list-unstyled blog-photos margin-bottom-35">
						<li><a href="#"><img src="<c:url value='resources_1_9_5/img/sliders/elastislide/5.jpg' />" alt="" class="hover-effect"></a></li>
						<li><a href="#"><img src="<c:url value='resources_1_9_5/img/sliders/elastislide/6.jpg' />" alt="" class="hover-effect"></a></li>
						<li><a href="#"><img src="<c:url value='resources_1_9_5/img/sliders/elastislide/8.jpg' />" alt="" class="hover-effect"></a></li>
						<li><a href="#"><img src="<c:url value='resources_1_9_5/img/sliders/elastislide/10.jpg' />" alt="" class="hover-effect"></a></li>
						<li><a href="#"><img src="<c:url value='resources_1_9_5/img/sliders/elastislide/11.jpg' />" alt="" class="hover-effect"></a></li>
						<li><a href="#"><img src="<c:url value='resources_1_9_5/img/sliders/elastislide/1.jpg' />" alt="" class="hover-effect"></a></li>
						<li><a href="#"><img src="<c:url value='resources_1_9_5/img/sliders/elastislide/2.jpg' />" alt="" class="hover-effect"></a></li>
						<li><a href="#"><img src="<c:url value='resources_1_9_5/img/sliders/elastislide/7.jpg' />" alt="" class="hover-effect"></a></li>
					</ul>
					<!-- End Photo Stream -->

					
				</div>
				

<script type="text/javascript">


$(document).ready(function(){
	var getCommentsUrl = '${getLastCommentsUrl}';
	$("#latestComments").html("");
	$("#latestComments").addClass("loader-256-gray");
	$.ajax({
          type: "GET",
          url: getCommentsUrl,
          dataType : "html",
          success: function(html){
        	  $("#latestComments").html(html);
        	  $("#latestComments").removeClass("loader-256-gray");
          },
          error: function(){
              alert("Failed to load Comments.");
          }
	 });
});

$(document).ready(function(){
	var getPhotoStreamUrl = '${getPhotoStreamUrl}';
	$("#photoStreamDiv").html("");
	$("#photoStreamDiv").addClass("loader-256-gray");
	$.ajax({
          type: "GET",
          url: getPhotoStreamUrl,
          dataType : "html",
          success: function(html){
        	  $("#photoStreamDiv").html(html);
        	  $("#photoStreamDiv").removeClass("loader-256-gray");
          },
          error: function(){
              alert("Failed to load the photo stream.");
          }
	 });
});


</script>				


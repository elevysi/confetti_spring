<%@ include file="../../layout/taglib.jsp"%>

<h1>Friends</h1>


<div class="container s-results margin-bottom-50">
	<c:forEach items="${friends}" var="friend">
	<!-- News v3 -->
                <div class="row margin-bottom-20">
                	<div class="col-sm-5 sm-margin-bottom-20">
                        <img class="img-responsive profile-img margin-bottom-20" src="<c:url value='/uploads/download?key=${friend.profilePicture.keyIdentification}'/>" alt="${friend.profilePicture.altText}">
                    </div>
                    
                    <div class="col-sm-5 news-v3">
                    <h3><a href="<spring:url value='/profile/${friend.name}'/>"><c:out value="${friend.name}" /> </a></h3>
                        Bio
                    </div>
                </div><!--/end row-->
                <!-- End News v3 -->
                <div class="clearfix margin-bottom-20"><hr></div>

	</c:forEach>
</div>







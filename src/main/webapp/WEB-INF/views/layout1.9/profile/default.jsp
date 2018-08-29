<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>
	<%@ include file="../../layout/taglib.jsp"%>
	<tiles:insertAttribute name="headTag" />
	<!-- CSS Page Style -->
	<link rel="stylesheet" href="<c:url value='/resources_1_9_5/css/pages/profile.css' />">
	<c:choose>
		<c:when test="${not empty  pageTitle}">
			<title><c:out value="${pageTitle}"/></title>
		</c:when>
		<c:otherwise>
			<title><tiles:getAsString name="title" ignore="true"/></title>
		</c:otherwise>
	</c:choose>
</head>

<body>
	<div class="wrapper">
		<!--=== Header ===-->
		<div class="header">
		
			<tiles:insertAttribute name="topbar" />
			<tiles:insertAttribute name="navbar" />
			
		</div>
		<!--=== End Header ===-->

		<!--=== Profile ===-->
		<div class="container content profile">
			<div class="row">
				<!--Left Sidebar-->
				<div class="col-md-3 md-margin-bottom-40">
					<tiles:insertAttribute name="profileLeftbar" />
				</div>
				<!--End Left Sidebar-->

				<!-- Profile Content -->
				<div class="col-md-9">
					<c:if test="${sessionMessage.msgText != null}">
						<div class="${sessionMessage.msgClass}">
							<button class="close" aria-hidden="true" data-dismiss="alert" type="button">�</button>
							<c:out value="${sessionMessage.msgText}"></c:out>
						</div>
					</c:if>
					<tiles:insertAttribute name="body" />	
				</div>
				<!-- End Profile Content -->
			</div>
		</div><!--/container-->
		<!--=== End Profile ===-->

		<!--=== Footer Version 1 ===-->
		<div class="footer-v1">
			<tiles:insertAttribute name="footer" />
		</div>
		<!--=== End Footer Version 1 ===-->
	</div><!--/wrapper-->

	
</body>
</html>

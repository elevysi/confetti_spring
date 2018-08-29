<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>
	<%@ include file="taglib.jsp"%>
   <tiles:insertAttribute name="headTag" />
   <title><tiles:getAsString name="title" /></title>
</head>

<body>
<div class="wrapper">
    <!--=== Header ===-->
    <div class="header">
        <div class="container">
            <!-- Logo -->
            <a href="<c:url value='/'/>" class="logo">
                <img src="<c:url value='/resources_1_8/img/logo1-default.png'/>" alt="Logo">
            </a>
            <!-- End Logo -->

            <!-- Topbar -->
            <tiles:insertAttribute name="topbar" />
            <!-- End Topbar -->

            <!-- Toggle get grouped for better mobile display -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="fa fa-bars"></span>
            </button>
            <!-- End Toggle -->
        </div><!--/end container-->
		
		<!-- Navbar -->
		<tiles:insertAttribute name="navbar" />
		<!-- End Navbar -->
        
    </div>
    <!--=== End Header ===-->

    <!--=== Conetnt ===-->
    <div class="container content profile">
    	<div class="row">
				<div class="col-md-9 md-margin-bottom-40">
				<c:if test="${sessionMessage.msgText != null}">
					<div class="${sessionMessage.msgClass}">
						<button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
						<c:out value="${sessionMessage.msgText}"></c:out>
					</div>
				</c:if>
				<tiles:insertAttribute name="body" />
				</div>
				<div class="col-md-3">
					<tiles:insertAttribute name="rightVBar" />
				</div>


			</div>
			<!--/row-->
    </div><!--/container-->
    <!--=== End Profile ===-->

    <!--=== Footer Version 1 ===-->
    <tiles:insertAttribute name="footer" />     
    <!--=== End Footer Version 1 ===-->
</div><!--/wrapper-->

<tiles:insertAttribute name="bodyjs" />

</body>
</html>
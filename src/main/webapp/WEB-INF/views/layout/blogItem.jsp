<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>
	<%@ include file="taglib.jsp"%>
   <tiles:insertAttribute name="headTag" />
   
   <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
   
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

    
    
   <%--  <tiles:insertAttribute name="profileCover" /> --%>

    <!--=== Blog Posts ===-->
    <div class="bg-color-light">
        <div class="container content-sm">
            <div class="row">
                <!-- Blog All Posts -->
                <div class="col-md-9">
	                <c:if test="${sessionMessage.msgText != null}">
						<div class="${sessionMessage.msgClass}">
							<button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
							<c:out value="${sessionMessage.msgText}"></c:out>
						</div>
					</c:if>
					<tiles:insertAttribute name="body" />
                </div>
                <!-- End Blog All Posts -->
				<tiles:insertAttribute name="rightBlogSideBar" />
                
            </div>
        </div><!--/end container-->
    </div>
    <!--=== End Blog Posts ===-->

    <!--=== Footer Version 1 ===-->
    <tiles:insertAttribute name="footer" />
    
    
</div><!--/wrapper-->
<tiles:insertAttribute name="bodyjs" />
<script type="text/javascript">
  
</script>
<!--[if lt IE 9]>
    <script src="<c:url value='/resources_1_8/plugins/respond.js'/>"></script>
    <script src="<c:url value='/resources_1_8/plugins/html5shiv.js'/>"></script>
    <script src="<c:url value='/resources_1_8/plugins/placeholder-IE-fixes.js'/>"></script>
<![endif]-->

</body>
</html>
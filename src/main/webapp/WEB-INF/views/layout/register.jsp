<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>
    <%@ include file="taglib.jsp"%>
	<title><tiles:getAsString name="title" /></title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Favicon -->
    <link rel="shortcut icon" href="favicon.ico">

    <!-- Web Fonts -->
    <link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

    <!-- CSS Global Compulsory -->
    <link rel="stylesheet" href="<c:url value='/resources_1_8/plugins/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources_1_8/css/style.css'/>">

    <!-- CSS Header and Footer -->
    <link rel="stylesheet" href="<c:url value='/resources_1_8/css/headers/header-default.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources_1_8/css/footers/footer-v1.css'/>">

    <!-- CSS Implementing Plugins -->
    <link rel="stylesheet" href="<c:url value='/resources_1_8/plugins/animate.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources_1_8/plugins/line-icons/line-icons.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources_1_8/plugins/font-awesome/css/font-awesome.min.css'/>">

    <!-- CSS Page Style -->
    <link rel="stylesheet" href="<c:url value='/resources_1_8/css/pages/page_log_reg_v1.css'/>">

    <!-- CSS Customization -->
    <link rel="stylesheet" href="<c:url value='/resources_1_8/css/custom.css'/>">
    
    <!-- JS Global Compulsory -->
	<script type="text/javascript" src="<c:url value='/resources_1_8/plugins/jquery/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources_1_8/plugins/jquery/jquery-migrate.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources_1_8/plugins/bootstrap/js/bootstrap.min.js'/>"></script>
	<!-- JS Implementing Plugins -->
	<script type="text/javascript" src="<c:url value='/resources_1_8/plugins/back-to-top.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources_1_8/plugins/smoothScroll.js'/>"></script>
	<!-- JS Customization -->
	<script type="text/javascript" src="<c:url value='/resources_1_8/js/custom.js'/>"></script>
	<!-- JS Page Level -->
	<script type="text/javascript" src="<c:url value='/resources_1_8/js/app.js'/>"></script>
	
	<!-- Jquery Validator -->
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
</head>

<body>
<div class="wrapper">
    <!--=== Header ===-->
    <div class="header">
        <div class="container">
            <!-- Logo -->
            <a class="logo" href="index.html">
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

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">Registration</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">Pages</a></li>
                <li class="active">Registration</li>
            </ul>
        </div><!--/container-->
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

    <!--=== Content Part ===-->
    <div class="container content">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <tiles:insertAttribute name="body" />
            </div>
        </div>
    </div><!--/container-->
    <!--=== End Content Part ===-->

     <!--=== Footer Version 1 ===-->
    <tiles:insertAttribute name="footer" />
    
    <!-- Add Model -->
		<div class="modal fade bs-example-modal-lg" id="modalAdd" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel-add">Confetti Bucket</h4>
					</div>
					<div class="modal-body"></div>
					<div class="modal-footer">
						<button type="button" class="btn-u" data-dismiss="modal">Cancel</button>
						<a href="" class="btn-u btn-u-green saveBtn">Save</a>
					</div>
				</div>
			</div>
		</div>
		<!-- End of Add Model -->
    
    <!--=== End Footer Version 1 ===-->
</div>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
    });
</script>
<!--[if lt IE 9]>
    <script src="<c:url value='/resources_1_8/plugins/respond.js'/>"></script>
    <script src="<c:url value='/resources_1_8/plugins/html5shiv.js'/>"></script>
    <script src="<c:url value='/resources_1_8/plugins/placeholder-IE-fixes.js'/>"></script>
<![endif]-->

</body>
</html>
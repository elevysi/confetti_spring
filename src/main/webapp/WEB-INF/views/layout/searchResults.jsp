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
    <link rel="stylesheet" href="<c:url value='/resources_1_8/css/pages/page_search_inner.css'/>">

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
	
	<!-- Jquery UI -->
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
		
	<!-- Jquery Validator -->
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
	
	<!-- Jquery File Upload -->
	<script type="text/javascript" src="<c:url value='/js/dropzone-4.0.0/dist/dropzone.js'/>"></script>
	<!-- TinyMCE -->
	<script type="text/javascript" src="<c:url value='/js/tinymce_4.1.7/tinymce.min.js'/>"></script>
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
    <tiles:insertAttribute name="breadcrumb" />
    <!--=== End Breadcrumbs ===-->

    <!--=== Search Block Version 2 ===-->
    <div class="search-block-v2">
                              
        <div class="container">
            <div class="col-md-6 col-md-offset-3">
                <h2>Search again</h2>
                <form action="<spring:url value="/public/search"/>" method="post">
                <div class="input-group">
                
                    <input type="text" class="form-control" placeholder="Search users, posts ..." name="globalSearch">
                    <span class="input-group-btn">
                        <button class="btn-u" type="submit"><i class="fa fa-search"></i></button>
                    </span>
                </div>
                </form>
            </div>
        </div>
    </div><!--/container-->
    <!--=== End Search Block Version 2 ===-->

    <!--=== Search Results ===-->
    <div class="container s-results margin-bottom-50">
        <div class="row">
            <div class="col-md-2 hidden-xs related-search">
                <div class="row">
                    <div class="col-md-12 col-sm-4">
                        <h3>Related searches</h3>
                        <ul class="list-unstyled">
                            <li><a href="#">Web design company</a></li>
                            <li><a href="#">Web design tutorials</a></li>
                            <li><a href="#">Self designing</a></li>
                        </ul>
                        <hr>
                    </div>

                    <div class="col-md-12 col-sm-4">
                        <h3>Tutorials</h3>
                        <ul class="list-unstyled">
                            <li><a href="#">Basic Concepts</a></li>
                            <li><a href="#">‎Building your first web page</a></li>
                            <li><a href="#">‎Advanced HTML</a></li>
                        </ul>
                        <hr>
                    </div>

                    <div class="col-md-12 col-sm-4">
                        <h3>Trending topics</h3>
                        <ul class="list-unstyled">
                            <li><a href="#">Bootstrap</a></li>
                            <li><a href="#">Wrapbootstrap</a></li>
                            <li><a href="#">Codrops</a></li>
                        </ul>
                        <hr>
                    </div>

                    <div class="col-md-12 col-sm-4">
                        <h3>Search history</h3>
                        <ul class="list-unstyled">
                            <li><a href="#">Web design articles</a></li>
                            <li><a href="#">Tutorials for web design</a></li>
                        </ul>
                        <a class="see-all" href="#">See all</a>
                        <hr>
                    </div>

                    <div class="col-md-12 col-sm-4">
                        <h3>Related pictures</h3>
                        <ul class="list-unstyled blog-photos margin-bottom-30">
                            <li><a href="#"><img src="<c:url value='/resources_1_8/img/sliders/elastislide/5.jpg'/>" alt="" class="hover-effect"></a></li>
                            <li><a href="#"><img src="<c:url value='/resources_1_8/img/sliders/elastislide/6.jpg'/>" alt="" class="hover-effect"></a></li>
                            <li><a href="#"><img src="<c:url value='/resources_1_8/img/sliders/elastislide/8.jpg'/>" alt="" class="hover-effect"></a></li>
                            <li><a href="#"><img src="<c:url value='/resources_1_8/img/sliders/elastislide/10.jpg'/>" alt="" class="hover-effect"></a></li>
                            <li><a href="#"><img src="<c:url value='/resources_1_8/img/sliders/elastislide/11.jpg'/>" alt="" class="hover-effect"></a></li>
                            <li><a href="#"><img src="<c:url value='/resources_1_8/img/sliders/elastislide/1.jpg'/>" alt="" class="hover-effect"></a></li>
                            <li><a href="#"><img src="<c:url value='/resources_1_8/img/sliders/elastislide/2.jpg'/>" alt="" class="hover-effect"></a></li>
                            <li><a href="#"><img src="<c:url value='/resources_1_8/img/sliders/elastislide/7.jpg'/>" alt="" class="hover-effect"></a></li>
                        </ul>
                    </div>
                </div>
            </div><!--/col-md-2-->

            <div class="col-md-10">
                <tiles:insertAttribute name="body" />
            </div><!--/col-md-10-->
        </div>
    </div><!--/container-->
    <!--=== End Search Results ===-->

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
</div><!--/End Wrapepr-->


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

<script>

$(document).ready(function(){
	$(".triggerRemove").click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
	});
	
	$(".modalOpen").click(function(e){
		e.preventDefault();
		$("#modalAdd .saveBtn").attr("href", $(this).attr("href"));
		$("#modalAdd").modal();
});
});
</script>


</body>
</html>
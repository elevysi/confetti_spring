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

    <!--=== Breadcrumbs v1 ===-->
    <div class="breadcrumbs-v1">
        <div class="container">
            <span>Blog Page</span>
            <h1><c:out value="${user.first_name}"/> <c:out value="${user.last_name}"/></h1>
        </div>
    </div>
    <!--=== End Breadcrumbs v1 ===-->

    <!--=== Blog Posts ===-->
    <div class="container content-md">
        <div class="row">
            <!-- Blog Sidebar -->
            <div class="col-md-3">
                <div class="headline-v2 bg-color-light"><h2>Trending</h2></div>
                <!-- Trending -->
                <ul class="list-unstyled blog-trending margin-bottom-50">
                    <li>
                        <h3><a href="#">Proin dapibus ornare magna.</a></h3>
                        <small>19 Jan, 2015 / <a href="#">Hi-Tech,</a> <a href="#">Technology</a></small>
                    </li>
                    <li>
                        <h3><a href="#">Fusce at diam ante.</a></h3>
                        <small>17 Jan, 2015 / <a href="#">Artificial Intelligence</a></small>
                    </li>
                    <li>
                        <h3><a href="#">Donec quis consequat magna...</a></h3>
                        <small>5 Jan, 2015 / <a href="#">Web,</a> <a href="#">Webdesign</a></small>
                    </li>
                </ul>
                <!-- End Trending -->

                <div class="headline-v2 bg-color-light"><h2>Latest Posts</h2></div>
                <!-- Latest Links -->
                <ul class="list-unstyled blog-latest-posts margin-bottom-50">
                    <li>
                        <h3><a href="#">The point of using Lorem Ipsum</a></h3>
                        <small>19 Jan, 2015 / <a href="#">Hi-Tech,</a> <a href="#">Technology</a></small>
                        <p>Phasellus ullamcorper pellentesque ex. Cras venenatis elit orci, vitae dictum elit egestas a. Nunc nec auctor mauris, semper scelerisque nibh.</p>
                    </li>
                    <li>
                        <h3><a href="#">Many desktop publishing packages...</a></h3>
                        <small>23 Jan, 2015 / <a href="#">Art,</a> <a href="#">Lifestyles</a></small>
                        <p>Integer vehicula sed justo ac dapibus. In sodales nunc non varius accumsan.</p>
                    </li>
                </ul>
                <!-- End Latest Links -->

                <div class="headline-v2 bg-color-light"><h2>Tags</h2></div>
                <!-- Tags v2 -->
                <ul class="list-inline tags-v2 margin-bottom-50">
                    <li><a href="#">Web Design</a></li>
                    <li><a href="#">Economy</a></li>
                    <li><a href="#">Sport</a></li>
                    <li><a href="#">Marketing</a></li>
                    <li><a href="#">Books</a></li>
                    <li><a href="#">Elections</a></li>
                    <li><a href="#">Flickr</a></li>
                    <li><a href="#">Politics</a></li>
                </ul>
                <!-- End Tags v2 -->

                <div class="headline-v2 bg-color-light"><h2>Photostream</h2></div>
                <!-- Photostream -->
                <ul class="list-inline blog-photostream margin-bottom-50">
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img22.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 1">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img22.jpg'/>" alt=""></span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img23.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 2">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img23.jpg'/>" alt=""></span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img4.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 3">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img4.jpg'/>" alt=""></span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img9.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 4">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img9.jpg'/>" alt=""></span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img25.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 5">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img25.jpg'/>" alt=""></span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img6.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 6">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img6.jpg'/>" alt=""></span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img20.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 7">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img20.jpg'/>" alt=""></span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img3.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 8">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img3.jpg'/>" alt=""></span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/resources_1_8/img/main/img7.jpg'/>" rel="gallery" class="fancybox img-hover-v2" title="Image 9">
                            <span><img class="img-responsive" src="<c:url value='/resources_1_8/img/main/img7.jpg'/>" alt=""></span>
                        </a>
                    </li>
                </ul>
                <!-- End Photostream -->
            </div>
            <!-- End Blog Sidebar -->

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
        </div>
    </div>
    <!--=== End Blog Posts ===-->

     <!--=== Footer Version 1 ===-->
    <tiles:insertAttribute name="footer" />
    
    <!--=== End Footer Version 1 ===-->
</div><!--/wrapper-->

<tiles:insertAttribute name="bodyjs" />



</body>
</html>
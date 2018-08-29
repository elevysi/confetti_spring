<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>
<spring:eval expression="@environment.getProperty('socialService.url')" var="socialServiceUrl" />
<spring:eval expression="@environment.getProperty('authService.url')" var="authServiceUrl" />
	
<!-- Header -->
    <header id="js-header" class="u-header u-header--static">
      <div class="u-header__section u-header__section--light g-bg-white g-transition-0_3 g-py-10">
        <nav class="js-mega-menu navbar navbar-expand-lg hs-menu-initialized hs-menu-horizontal">
          <div class="container">
            <!-- Responsive Toggle Button -->
            <button class="navbar-toggler navbar-toggler-right btn g-line-height-1 g-brd-none g-pa-0 g-pos-abs g-top-3 g-right-0" type="button" aria-label="Toggle navigation" aria-expanded="false" aria-controls="navBar" data-toggle="collapse" data-target="#navBar">
              <span class="hamburger hamburger--slider">
            <span class="hamburger-box">
              <span class="hamburger-inner"></span>
              </span>
              </span>
            </button>
            <!-- End Responsive Toggle Button -->

            <!-- Logo -->
            <a href="<spring:url value="/"/>" class="navbar-brand">
              <img src="<c:url value='/resources_1_9_5/img/logo1-default.png'/>" alt="Confetti Bucket Logo'/>">
            </a>
            <!-- End Logo -->

            <!-- Navigation -->
            <div class="collapse navbar-collapse align-items-center flex-sm-row g-pt-10 g-pt-5--lg g-mr-40--lg" id="navBar">
              <ul class="navbar-nav text-uppercase g-pos-rel g-font-weight-600 ml-auto">
                <!-- Home -->
                <li class="nav-item  g-mx-10--lg g-mx-15--xl">
                  <a href="<spring:url value="/"/>" class="nav-link g-py-7 g-px-0">Home</a>
                </li>
                <!-- End Home -->

                <!-- Blogs -->
                <li class="hs-has-sub-menu nav-item  g-mx-10--lg g-mx-15--xl" data-animation-in="fadeIn" data-animation-out="fadeOut">
                  <a id="nav-link-blogs" class="nav-link g-py-7 g-px-0" href="#!" aria-haspopup="true" aria-expanded="false" aria-controls="nav-submenu-blogs">Blogs</a>

                  <ul class="hs-sub-menu list-unstyled u-shadow-v11 g-brd-top g-brd-primary g-brd-top-2 g-min-width-220 g-mt-18 g-mt-8--lg--scrolling" id="nav-submenu-blogs" aria-labelledby="nav-link-blogs">
                    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="/"/>">Spring</a></li>
                    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="http://elevysi.com/posts/timeline"/>">Cake PHP</a></li>
                  </ul>
                </li>
                <!-- End Blogs -->
                
                <!-- Confettis -->
                <li class="hs-has-sub-menu nav-item  g-mx-10--lg g-mx-15--xl" data-animation-in="fadeIn" data-animation-out="fadeOut">
                  <a id="nav-link-confettis" class="nav-link g-py-7 g-px-0" href="#!" aria-haspopup="true" aria-expanded="false" aria-controls="nav-submenu-confettis">Confettis</a>
                  <ul class="hs-sub-menu list-unstyled u-shadow-v11 g-brd-top g-brd-primary g-brd-top-2 g-min-width-220 g-mt-18 g-mt-8--lg--scrolling" id="nav-submenu-confettis" aria-labelledby="nav-link-confettis">
                    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="/public/posts"/>">Posts</a></li>
					<li class="dropdown-item "><a class="nav-link" href="<spring:url value="/public/plays"/>">Plays</a></li>
					<li class="dropdown-item "><a class="nav-link" href="<spring:url value="/public/dossiers"/>">Dossiers</a></li>
					<li class="dropdown-item "><a class="nav-link" href="<spring:url value="${socialServiceUrl}/ui/public/profiles"/>">Profiles</a></li>
                   
                  </ul>
                </li>
                <!-- End Confettis -->
                
                <!-- Create -->
                <li class="hs-has-sub-menu nav-item  g-mx-10--lg g-mx-15--xl" data-animation-in="fadeIn" data-animation-out="fadeOut">
                  <a id="nav-link-create" class="nav-link g-py-7 g-px-0" href="#!" aria-haspopup="true" aria-expanded="false" aria-controls="nav-submenu-create">Create</a>
                  <ul class="hs-sub-menu list-unstyled u-shadow-v11 g-brd-top g-brd-primary g-brd-top-2 g-min-width-220 g-mt-18 g-mt-8--lg--scrolling" id="nav-submenu-create" aria-labelledby="nav-link-create">
                    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="/posts/add"/>">Add Post</a></li>
					<li class="dropdown-item "><a class="nav-link" href="<spring:url value="/plays/add"/>">Add Play</a></li>
					<li class="dropdown-item "><a class="nav-link" href="<spring:url value="/dossiers/add"/>">Add Dossier</a></li>
                  </ul>
                </li>
                <!-- End Create -->
                
                <security:authorize access="isAuthenticated()">
	                <!-- Profile -->
	                <li class="hs-has-sub-menu nav-item  g-mx-10--lg g-mx-15--xl" data-animation-in="fadeIn" data-animation-out="fadeOut">
	                  <a id="nav-link-profile" class="nav-link g-py-7 g-px-0" href="#!" aria-haspopup="true" aria-expanded="false" aria-controls="nav-submenu-profile">Profile</a>
	                  <ul class="hs-sub-menu list-unstyled u-shadow-v11 g-brd-top g-brd-primary g-brd-top-2 g-min-width-220 g-mt-18 g-mt-8--lg--scrolling" id="nav-submenu-profile" aria-labelledby="nav-link-profile">
	                    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="${socialServiceUrl}/ui/profile/"/>">Home - <security:authentication property="principal.username" /></a></li>
					    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="${authServiceUrl}/ui/updatePassword"/>">Password Update</a></li>
	                  </ul>
	                </li>
	                
	                <security:authorize access="hasRole('ROLE_ADMIN')">
	                
	                <li class="hs-has-sub-menu nav-item  g-mx-10--lg g-mx-15--xl" data-animation-in="fadeIn" data-animation-out="fadeOut">
	                  <a id="nav-link-admin" class="nav-link g-py-7 g-px-0" href="#!" aria-haspopup="true" aria-expanded="false" aria-controls="nav-submenu-admin">Admin</a>
	                  <ul class="hs-sub-menu list-unstyled u-shadow-v11 g-brd-top g-brd-primary g-brd-top-2 g-min-width-220 g-mt-18 g-mt-8--lg--scrolling" id="nav-submenu-admin" aria-labelledby="nav-link-admin">
	                    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="/admin/dashboard"/>">Dashboard</a></li>
					    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="/admin/featured"/>">Featured Update</a></li>
					    <li class="dropdown-item "><a class="nav-link" href="<spring:url value="/admin/profiles"/>">Profiles</a></li>
	                  </ul>
	                </li>
						
					</security:authorize>
	                
	                <c:url var="logoutPostUrl" value="/logout" />
					<li class="nav-item  g-mx-10--lg g-mx-15--xl"><a href="<spring:url value="${authServiceUrl}/logout"/>" class="nav-link g-py-7 g-px-0">Sign out</a></li>
	                <!-- End Profile -->
				</security:authorize>
				
				
				<security:authorize access="!isAuthenticated()">
					<li class="nav-item  g-mx-10--lg g-mx-15--xl"><a href="<spring:url value="/login"/>" class="nav-link g-py-7 g-px-0">Sign in</a></li>
				</security:authorize>

              </ul>
            </div>
            <!-- End Navigation -->
          </div>
        </nav>
      </div>
    </header>
    <!-- End Header -->
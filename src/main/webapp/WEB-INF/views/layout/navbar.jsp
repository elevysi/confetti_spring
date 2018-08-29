<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="taglib.jsp"%>
<!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse mega-menu navbar-responsive-collapse">
            <div class="container">
                <ul class="nav navbar-nav">
                    <!-- Home -->
                    <li class=""><a href="<spring:url value="/"/>"> Home </a></li>
                    <!-- End Home -->
                    
                    <security:authorize access="isAuthenticated()">
					<!-- Blog -->
					<li class="dropdown"><a href="javascript:void(0);"
						class="dropdown-toggle" data-toggle="dropdown"> Profile</a>
						<ul class="dropdown-menu">
							<li><a href="<spring:url value="/profile/"/>">Home - <security:authentication property="principal.username" /></a></li>
							<li><a href="<spring:url value="/profile/timeline"/>">Blogs</a></li>
						</ul>
					</li>
					<!-- End Blog -->
					</security:authorize>
					
	
					<security:authorize access="hasRole('ROLE_ADMIN')">
						
						<!-- Admin -->
						<li class="dropdown"><a href="javascript:void(0);"
							class="dropdown-toggle" data-toggle="dropdown"> Admin</a>
							<ul class="dropdown-menu">
								<li><a href="<spring:url value="/admin/dashboard"/>">Dashboard</a></li>
								<li><a href="<spring:url value="/admin/blog/posts"/>">Blogs</a></li>
								<li><a href="<spring:url value="/admin/users"/>">Users</a></li>
							</ul>
						</li>
						<!-- End Admin -->
					</security:authorize>
					
					
					<security:authorize access="isAuthenticated()">
						<li><a href="<spring:url value="/logout"/>">Logout</a></li>
					</security:authorize>

                    

                    <!-- Search Block -->
                    <li>
                        <i class="search fa fa-search search-btn"></i>
                        <div class="search-open">
                        	<form action="<spring:url value="/public/search"/>" method="post">
                            	<div class="input-group animated fadeInDown">
									<input type="text" class="form-control" placeholder="Search" name="globalSearch">
	                                <span class="input-group-btn">
	                                    <input class="btn-u" type="submit" value="Go">
	                                </span>
                              
                            	</div>
                            </form>
                        </div>
                    </li>
                    <!-- End Search Block -->
                </ul>
            </div><!--/end container-->
        </div><!--/navbar-collapse-->
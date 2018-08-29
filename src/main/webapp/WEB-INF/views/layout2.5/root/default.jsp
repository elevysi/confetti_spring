<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../layout/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<%@ include file="../../layout/taglib.jsp"%>
    <tiles:insertAttribute name="headTag" />

<c:choose>
	<c:when test="${not empty  pageTitle}">
		<title><c:out value="${pageTitle} - Elevysi"/></title>
	</c:when>
	<c:otherwise>
		<title><tiles:getAsString name="title" ignore="true"/></title>
	</c:otherwise>
</c:choose>
    	
</head>

<body>
  <main>


	<tiles:insertAttribute name="navbar" />
	<tiles:insertAttribute name="breadcrumb" />
    


    <!-- Blog Minimal Blocks -->
    <div class="container g-pt-100 g-pb-20">
      <div class="row justify-content-between">
        <div class="col-lg-9 g-mb-80">
        	<c:if test="${sessionMessage.msgText != null}">
				<div class="${sessionMessage.msgClass}">
					<button class="close" aria-hidden="true" data-dismiss="alert" type="button">x</button>
					<c:out value="${sessionMessage.msgText}"></c:out>
				</div>
			</c:if>
        	<tiles:insertAttribute name="body" />
        </div>

        <div class="col-lg-3 g-brd-left--lg g-brd-gray-light-v4 g-mb-80">
        	<tiles:insertAttribute name="rightBar" />
        </div>
      </div>
    </div>
    <!-- End Blog Minimal Blocks -->

    <!-- Footer -->
    <tiles:insertAttribute name="footer" />
    <!-- End Footer -->
    
    <a class="js-go-to u-go-to-v1" href="#!" data-type="fixed" data-position='{
     "bottom": 15,
     "right": 15
   }' data-offset-top="400" data-compensation="#js-header" data-show-effect="zoomIn">
      <i class="hs-icon hs-icon-arrow-top"></i>
    </a>
  </main>

  <div class="u-outer-spaces-helper"></div>


  <!-- JS Global Compulsory -->
  <script src="<c:url value='/resources_2_5/vendor/jquery/jquery.min.js'/>"></script>
  <script src="<c:url value='/resources_2_5/vendor/jquery-migrate/jquery-migrate.min.js'/>"></script>
  <script src="<c:url value='/resources_2_5/vendor/popper.min.js'/>"></script>
  <script src="<c:url value='/resources_2_5/vendor/bootstrap/bootstrap.min.js'/>"></script>


  <!-- JS Implementing Plugins -->
  <script src="<c:url value='/resources_2_5/vendor/hs-megamenu/src/hs.megamenu.js'/>"></script>

  <!-- JS Unify -->
  <script src="<c:url value='/resources_2_5/js/hs.core.js'/>"></script>
  <script src="<c:url value='/resources_2_5/js/components/hs.header.js'/>"></script>
  <script src="<c:url value='/resources_2_5/js/helpers/hs.hamburgers.js'/>"></script>
  <script src="<c:url value='/resources_2_5/js/components/hs.tabs.js'/>"></script>
  <script src="<c:url value='/resources_2_5/js/components/hs.sticky-block.js'/>"></script>
  <script src="<c:url value='/resources_2_5/js/components/hs.go-to.js'/>"></script>

  <!-- JS Customization -->
  <script src="<c:url value='/resources_2_5/js/custom.js'/>"></script>

  <!-- JS Plugins Init. -->
  <script>
    $(document).on('ready', function () {
        // initialization of tabs
        $.HSCore.components.HSTabs.init('[role="tablist"]');

        // initialization of go to
        $.HSCore.components.HSGoTo.init('.js-go-to');
      });

      $(window).on('load', function () {
        // initialization of header
        $.HSCore.components.HSHeader.init($('#js-header'));
        $.HSCore.helpers.HSHamburgers.init('.hamburger');

        // initialization of HSMegaMenu component
        $('.js-mega-menu').HSMegaMenu({
          event: 'hover',
          pageContainer: $('.container'),
          breakpoint: 991
        });

        // initialization of sticky blocks
        setTimeout(function() {
          $.HSCore.components.HSStickyBlock.init('.js-sticky-block');
        }, 300);
      });

      $(window).on('resize', function () {
        setTimeout(function () {
          $.HSCore.components.HSTabs.init('[role="tablist"]');
        }, 200);
      });
  </script>

  <tiles:insertAttribute name="bodyjs" />





</body>

</html>

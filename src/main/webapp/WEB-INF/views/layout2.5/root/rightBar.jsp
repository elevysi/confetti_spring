<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../layout/taglib.jsp"%>
<spring:eval expression="@environment.getProperty('socialService.url')" var="socialServiceUrl" />


<div class="g-pl-20--lg">

<!-- Photos -->
<div class="g-overflow-hidden g-mb-40">
<h3 class="h5 g-color-black g-font-weight-600 mb-4">The Gram</h3>
	<div class="row text-center mx-0 g-ml-minus-1 g-mb-minus-1" id="streamContainer">
			<div class="row">
			  <div class="u-block-hover g-brd-left g-brd-bottom g-brd-gray-light-v4 g-py-40">
			    <img class="mx-auto g-width-100 u-block-hover__main--grayscale g-opacity-0_4 g-opacity-1--hover g-cursor-pointer" src="<c:url value='/resources_1_9_5/img/sliders/elastislide/5.jpg'/>" alt="Image Description">
			  </div>
			</div>
			
			<div class="row">
			  <div class="u-block-hover g-brd-left g-brd-bottom g-brd-gray-light-v4 g-py-40">
			    <img class="mx-auto g-width-100 u-block-hover__main--grayscale g-opacity-0_4 g-opacity-1--hover g-cursor-pointer" src="<c:url value='/resources_1_9_5/img/sliders/elastislide/5.jpg'/>" alt="Image Description">
			  </div>
			</div>
			
			<div class="row">
			  <div class="u-block-hover g-brd-left g-brd-bottom g-brd-gray-light-v4 g-py-40">
			    <img class="mx-auto g-width-100 u-block-hover__main--grayscale g-opacity-0_4 g-opacity-1--hover g-cursor-pointer" src="<c:url value='/resources_1_9_5/img/sliders/elastislide/5.jpg'/>" alt="Image Description">
			  </div>
			</div>
	</div>
</div>

  <!-- End Photos -->

  <hr class="g-brd-gray-light-v4 g-mt-50 mb-0">

  <div id="stickyblock-start" class="js-sticky-block g-sticky-block--lg g-pt-50" data-start-point="#stickyblock-start" data-end-point="#stickyblock-end">
    <!-- Publications -->
    <div class="g-mb-50">
      <h3 class="h5 g-color-black g-font-weight-600 mb-4">Links</h3>
      <ul class="list-unstyled g-font-size-13 mb-0">
        <li>
          <article class="media">
            <img class="d-flex g-width-40 g-height-40 rounded-circle mr-3" src="<c:url value='/resources_2_5/img-temp/100x100/img2.jpg'/>" alt="Image Description">
            <div class="media-body">
              <h4 class="h6 g-color-black g-font-weight-600">Ad</h4>
              <p class="g-color-gray-dark-v4">Sample Ad</p>
              <a class="btn u-btn-outline-primary g-font-size-11 g-rounded-25" href="#!">Follow</a>
            </div>
          </article>
        </li>
      </ul>
    </div>
    <!-- End Publications -->

    <hr class="g-brd-gray-light-v4 g-my-50">

    <!-- Tags -->
    <div class="g-mb-40">
      <h3 class="h5 g-color-black g-font-weight-600 mb-4">Tags</h3>
      <ul class="u-list-inline mb-0" id="siteTags">
        
      </ul>
    </div>
    <!-- End Tags -->

    <hr class="g-brd-gray-light-v4 g-my-50">

    <!-- Newsletter -->
    <div class="g-mb-50">
      <h3 class="h5 g-color-black g-font-weight-600 mb-4">Newsletter</h3>
      <div class="input-group">
        <span class="input-group-btn">
            <button class="btn u-btn-primary g-rounded-left-50 g-py-13 g-px-20">
              <i class="icon-communication-062 u-line-icon-pro g-pos-rel g-top-1"></i>
            </button>
          </span>
        <input class="form-control g-brd-primary g-placeholder-gray-dark-v5 border-left-0 g-rounded-right-50 g-px-15" type="email" placeholder="Enter your email ...">
      </div>
    </div>
    <!-- End Newsletter -->
  </div>
</div>

<!-- Photo Stream -->


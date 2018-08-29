<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../layout/taglib.jsp"%>
<spring:eval expression="@environment.getProperty('socialService.url')" var="socialServiceUrl" />
    
<div id="contacts-section" class="g-bg-black-opacity-0_9 g-color-white-opacity-0_8 g-py-60">
  <div class="container">
    <div class="row">
      <!-- Footer Content -->
      <div class="col-lg-3 col-md-6 g-mb-40 g-mb-0--lg">
        <div class="u-heading-v2-3--bottom g-brd-white-opacity-0_8 g-mb-20">
          <h2 class="u-heading-v2__title h6 text-uppercase mb-0">About Us</h2>
        </div>

        <p>Confettis Bucket is a blogging and socializing web site. Pick your confettis and put them in your bucket</p>
      </div>
      <!-- End Footer Content -->

      <!-- Footer Content -->
      <div class="col-lg-3 col-md-6 g-mb-40 g-mb-0--lg">
        <div class="u-heading-v2-3--bottom g-brd-white-opacity-0_8 g-mb-20">
          <h2 class="u-heading-v2__title h6 text-uppercase mb-0">Latest Publications</h2>
        </div>
        
        <div id="footerLatestPublications">
        
        </div>
      </div>
      <!-- End Footer Content -->

      <!-- Footer Content -->
      <div class="col-lg-3 col-md-6 g-mb-40 g-mb-0--lg">
        <div class="u-heading-v2-3--bottom g-brd-white-opacity-0_8 g-mb-20">
          <h2 class="u-heading-v2__title h6 text-uppercase mb-0">Useful Links</h2>
        </div>

        <nav class="text-uppercase1">
          <ul class="list-unstyled g-mt-minus-10 mb-0">
            <li class="g-pos-rel g-brd-bottom g-brd-white-opacity-0_1 g-py-10">
              <h4 class="h6 g-pr-20 mb-0">
            <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">About Us</a>
            <i class="fa fa-angle-right g-absolute-centered--y g-right-0"></i>
          </h4>
            </li>
            <li class="g-pos-rel g-brd-bottom g-brd-white-opacity-0_1 g-py-10">
              <h4 class="h6 g-pr-20 mb-0">
            <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">Social</a>
            <i class="fa fa-angle-right g-absolute-centered--y g-right-0"></i>
          </h4>
            </li>
            <li class="g-pos-rel g-brd-bottom g-brd-white-opacity-0_1 g-py-10">
              <h4 class="h6 g-pr-20 mb-0">
            <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">Shop</a>
            <i class="fa fa-angle-right g-absolute-centered--y g-right-0"></i>
          </h4>
            </li>
            <li class="g-pos-rel g-brd-bottom g-brd-white-opacity-0_1 g-py-10">
              <h4 class="h6 g-pr-20 mb-0">
            <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">The Gram</a>
            <i class="fa fa-angle-right g-absolute-centered--y g-right-0"></i>
          </h4>
            </li>
            <li class="g-pos-rel g-py-10">
              <h4 class="h6 g-pr-20 mb-0">
            <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">Contact Us</a>
            <i class="fa fa-angle-right g-absolute-centered--y g-right-0"></i>
          </h4>
            </li>
          </ul>
        </nav>
      </div>
      <!-- End Footer Content -->

      <!-- Footer Content -->
      <div class="col-lg-3 col-md-6">
        <div class="u-heading-v2-3--bottom g-brd-white-opacity-0_8 g-mb-20">
          <h2 class="u-heading-v2__title h6 text-uppercase mb-0">Our Contacts</h2>
        </div>

        <address class="g-bg-no-repeat g-font-size-12 mb-0" style="background-image: url(../../assets/img/maps/map2.png);">
      <!-- Location -->
      <div class="d-flex g-mb-20">
        <div class="g-mr-10">
          <span class="u-icon-v3 u-icon-size--xs g-bg-white-opacity-0_1 g-color-white-opacity-0_6">
            <i class="fa fa-map-marker"></i>
          </span>
        </div>
        <p class="mb-0">Lamadelaine, <br> Luxembourg</p>
      </div>
      <!-- End Location -->

      <!-- Phone -->
      <div class="d-flex g-mb-20">
        <div class="g-mr-10">
          <span class="u-icon-v3 u-icon-size--xs g-bg-white-opacity-0_1 g-color-white-opacity-0_6">
            <i class="fa fa-phone"></i>
          </span>
        </div>
        <p class="mb-0">(+352)  <br> (+352) </p>
      </div>
      <!-- End Phone -->

      <!-- Email and Website -->
      <div class="d-flex g-mb-20">
        <div class="g-mr-10">
          <span class="u-icon-v3 u-icon-size--xs g-bg-white-opacity-0_1 g-color-white-opacity-0_6">
            <i class="fa fa-globe"></i>
          </span>
        </div>
        <p class="mb-0">
          <a class="g-color-white-opacity-0_8 g-color-white--hover" href="mailto:info@htmlstream.com">info@elevysi.com</a>
          <br>
          <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">www.elevysi.com</a>
        </p>
      </div>
      <!-- End Email and Website -->
    </address>
      </div>
      <!-- End Footer Content -->
    </div>
  </div>
</div>


<!-- Copyright Footer -->
    <footer class="g-bg-gray-dark-v1 g-color-white-opacity-0_8 g-py-20">
      <div class="container">
        <div class="row">
          <div class="col-md-8 text-center text-md-left g-mb-10 g-mb-0--md">
            <div class="d-lg-flex">
              <small class="d-block g-font-size-default g-mr-10 g-mb-10 g-mb-0--md">2018 &copy; All Rights Reserved.</small>
              <ul class="u-list-inline">
                <li class="list-inline-item">
                  <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">Privacy Policy</a>
                </li>
                <li class="list-inline-item">
                  <span>|</span>
                </li>
                <li class="list-inline-item">
                  <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">Terms of Use</a>
                </li>
                <li class="list-inline-item">
                  <span>|</span>
                </li>
                <li class="list-inline-item">
                  <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">License</a>
                </li>
                <li class="list-inline-item">
                  <span>|</span>
                </li>
                <li class="list-inline-item">
                  <a class="g-color-white-opacity-0_8 g-color-white--hover" href="#!">Support</a>
                </li>
              </ul>
            </div>
          </div>

          <div class="col-md-4 align-self-center">
            <ul class="list-inline text-center text-md-right mb-0">
              <li class="list-inline-item g-mx-10" data-toggle="tooltip" data-placement="top" title="Facebook">
                <a href="#!" class="g-color-white-opacity-0_5 g-color-white--hover">
                  <i class="fa fa-facebook"></i>
                </a>
              </li>
              <li class="list-inline-item g-mx-10" data-toggle="tooltip" data-placement="top" title="Skype">
                <a href="#!" class="g-color-white-opacity-0_5 g-color-white--hover">
                  <i class="fa fa-skype"></i>
                </a>
              </li>
              <li class="list-inline-item g-mx-10" data-toggle="tooltip" data-placement="top" title="Linkedin">
                <a href="#!" class="g-color-white-opacity-0_5 g-color-white--hover">
                  <i class="fa fa-linkedin"></i>
                </a>
              </li>
              <li class="list-inline-item g-mx-10" data-toggle="tooltip" data-placement="top" title="Pinterest">
                <a href="#!" class="g-color-white-opacity-0_5 g-color-white--hover">
                  <i class="fa fa-pinterest"></i>
                </a>
              </li>
              <li class="list-inline-item g-mx-10" data-toggle="tooltip" data-placement="top" title="Twitter">
                <a href="#!" class="g-color-white-opacity-0_5 g-color-white--hover">
                  <i class="fa fa-twitter"></i>
                </a>
              </li>
              <li class="list-inline-item g-mx-10" data-toggle="tooltip" data-placement="top" title="Dribbble">
                <a href="#!" class="g-color-white-opacity-0_5 g-color-white--hover">
                  <i class="fa fa-dribbble"></i>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
    <!-- End Copyright Footer -->
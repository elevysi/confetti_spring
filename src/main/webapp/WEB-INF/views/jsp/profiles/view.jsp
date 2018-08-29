<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp" %>
<c:url var="bucketPostUrl" value="/bucket/${userProfile.name}" />
<h2><c:out value="${userProfile.name}" /></h2>
<ul class="list-inline up-ul">
	<li><a class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-dark-blue btn-u-sm"  href="<spring:url value='/conversations/conversate/${userProfile.name}' />"><i class="fa fa-magic"></i>Message</a></li>
	<li>
		<form:form action="${bucketPostUrl}" method="post" onSubmit="return confirm('Please confirm before the action is processed.');" class="form-inline">
			<input type="hidden" value="${userProfile.name}" name="bucketID" >
			<button class="btn-u btn-brd btn-brd-hover rounded-2x btn-u-purple btn-u-sm" type="submit">Add to my Bucket</button>
		</form:form>
	</li>
	<li></li>
</ul>



<hr>

<c:forEach items="${plays}" var="play">

<!-- News v3 -->
 <div class="row margin-bottom-20">
     <div class="col-sm-5 sm-margin-bottom-20">
     
     <div class="responsive-video">
		<iframe src="<c:out value='${play.url}'/>" frameborder="0" allowfullscreen></iframe>
	 </div>
         
     </div>
     <div class="col-sm-7 news-v3">
         <div class="news-v3-in-sm no-padding">
             <ul class="list-inline posted-info">
                 
                 <li>In <a href="<c:url value='/playTypes/view/${play.playType.name}' />"><c:out value="${play.playType.name}" /></a></li>
                
             </ul>
             <h2><a href="<c:url value='/plays/view/${play.id}' />"><c:out value="${play.title}" /></a></h2>
            <c:out value="${play.description}" escapeXml="false"/>
            <ul class="post-shares">
                 <li>
                     <a href="#">
                         <i class="rounded-x icon-speech"></i>
                         <span>5</span>
                     </a>
                 </li>
                 <li><a href="#"><i class="rounded-x icon-share"></i></a></li>
                 <li><a href="#"><i class="rounded-x icon-heart"></i></a></li>
             </ul>
         </div>
     </div>
 </div><!--/end row-->
 <!-- End News v3 -->
<div class="clearfix margin-bottom-20"><hr></div>
</c:forEach>

<tiles:insertAttribute name="pagination" ignore="true"/>



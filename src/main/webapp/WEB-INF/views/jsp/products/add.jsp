<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>

<c:url var="post_url" value="/products/add/" />

<c:url var="uploadLink" value="/uploads/post" />
<c:url var="uploadDeleteLink" value="/uploads/profileDelete" />
<c:url var ="tinymceCss" value='/css/tinymce.css'/>


<form:form commandName="product" cssClass="sky-form addPostForm" method="post" action="${post_url}">

	<fieldset>
	
		
		<div class="form-item">
			<section>
				<label class="label"><spring:message code="label.posts.add.title" /> <span class="color-red">*</span> <form:errors path="title" /></label> <label class="input"> <form:input
						path="title" />
				</label>
			</section>
		</div>

		<section class="form-item">
			<label class="label"><spring:message code="label.posts.add.description" /></label> <label class="textarea">
				<form:textarea path="description" />
				
			</label>
			<div class="note">
				<strong>Note:</strong> A small description or a subtitle to your post
			</div>
		</section>



		<section class="form-item">
			<label class="label"><spring:message code="label.posts.add.content" /> <span class="color-red">*</span>
				<form:textarea path="description" cssClass="editorTextarea" />
			</label>
		</section>


	</fieldset>
	<footer>
		<button class="btn-u" type="submit" name="action" value="draft"><spring:message code="label.posts.add.saveDraft" /></button>
		<button class="btn-u" type="submit" name="action" value="publish"><spring:message code="label.posts.add.submitBtn" /></button>
		<button class="btn-u btn-u-default" name="action" value="cancel" type="button"><spring:message code="label.posts.add.backBtn" /></button>
		
	</footer>



</form:form>
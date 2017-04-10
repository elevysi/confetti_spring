<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>

<c:url var="post_url" value="/posts/edit/${post.id}" />

<c:url var="uploadLink" value="/uploads/post" />
<c:url var="uploadDeleteLink" value="/uploads/profileDelete" />
<c:url var ="tinymceCss" value='/css/tinymce.css'/>



<form:form commandName="post" cssClass="sky-form addPostForm" method="post" action="${post_url}">
<form:hidden path="uuid"/>
<form:hidden path="id"/>
	<header><spring:message code="label.posts.add.heading" /></header>		
	<fieldset>
	
	<div class="row">
		<a class="btn-u btn-u-green modalOpen noSave"  href="<spring:url value='/uploads/manage/${post.uuid}' />"><i class="glyphicon glyphicon-file"></i><spring:message code="label.posts.add.manageUploads"/></a>
	</div>
	
	<hr>
	
	<div class="form-item">
		<section>
			<label class="label">Choose Dossier <form:errors path="dossier" /></label> <label class="select">
			<form:select path="dossier.id">
				<form:option  value="">--Select one--</form:option>
				<form:options items="${dossiers}" itemValue="id" itemLabel="name"/>
				<form:errors path="dossier"/>
			</form:select>
			
			</label>
		</section>
	</div>
	
	<div class="form-item">
		<section>
			<label class="label">Choose Category <form:errors path="categories" /></label> <label class="select select-multiple">
			<form:select path="categories" items="${categories}" />
			</label>
		</section>
	</div>
	
		
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
			<label class="label"><spring:message code="label.posts.add.content" /> <span class="color-red">*</span> <form:errors path="content" /></label><label class="textarea">
				<form:textarea path="content" cssClass="editorTextarea" />
			</label>
		</section>


	</fieldset>
	<footer>
		<button class="btn-u" type="submit" name="action" value="draft"><spring:message code="label.posts.add.saveDraft" /></button>
		<button class="btn-u" type="submit" name="action" value="publish"><spring:message code="label.posts.add.submitBtn" /></button>
		<button class="btn-u btn-u-default" name="action" value="cancel" type="button"><spring:message code="label.posts.add.backBtn" /></button>
		
	</footer>



</form:form>

<!-- TinyMCE -->
<script type="text/javascript" src="<c:url value='/js/tinymce_4.4.1/tinymce/js/tinymce/tinymce.min.js'/>"></script>	
<script type="text/javascript">




tinymce.init({
	  mode : "specific_textareas",
      editor_selector : "editorTextarea",
	  height: 500,
	  theme: 'modern',
	  content_css : "${tinymceCss}",
	  plugins: [
	    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
	    'searchreplace wordcount sh4tinymce visualblocks visualchars code fullscreen',
	    'insertdatetime media nonbreaking save table contextmenu directionality',
	    'emoticons template paste textcolor colorpicker textpattern imagetools'
	  ],
	  toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
	  toolbar2: 'print preview media | forecolor backcolor emoticons | sh4tinymce',
	  image_advtab: true,
	  templates: [
	    { title: 'Test template 1', content: 'Test 1' },
	    { title: 'Test template 2', content: 'Test 2' }
	  ]
	  
});

/**
 * content_css: [
         	    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
        	    '//www.tinymce.com/css/codepen.min.css'
        	  ]
 */






$(document).ready(function(){
	$(".addPostForm").validate(
		{	
			rules : {
				title: {
					required : true,
					minlength : 2
				},
				description:{
					maxlength : 255
				},
				content: {
					required : true,
					minlength : 2
				},
			},
			highlight:function(element){
				$(element).closest(".form-item").removeClass('has-success').addClass('has-error')
			},
			unhighlight:function(element){
				$(element).closest(".form-item").removeClass('has-error').addClass('has-success')
			},
			messages :{
				title : {
					required : '<spring:message code="label.posts.add.titleRequired" />',
					minlength : '<spring:message code="label.posts.add.titleMinSize" />'
					
				},
				content : {
					required : '<spring:message code="label.posts.add.contentRequired" />',
					minlength : '<spring:message code="label.posts.add.contentMinSize" />'
				}
			}
		}
	);
	
	/* $(".addPostForm").submit( function(eventObj) {
	      $('<input />').attr('type', 'hidden')
	          .attr('name', "upload_id")
	          .attr('value', uploadedId)
	          .appendTo('.addPostForm');
	      return true;
	  }); */
	
	
});

</script>


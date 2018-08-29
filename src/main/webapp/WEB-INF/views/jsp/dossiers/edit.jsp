<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>

<c:url var="dossier_post_url" value="/dossiers/edit/${dossier.id}" />
<c:url var ="tinymceCss" value='/css/tinymce.css'/>

<form:form commandName="dossier" cssClass="sky-form addPostForm" method="post" action="${dossier_post_url}">
	<header>Add Dossier</header>		
	<fieldset>
	<form:hidden path="id"/>
	<form:hidden path="uuid"/>
	<div class="row">
		<a class="btn-u btn-u-green modalOpen noSave"  href="<spring:url value='/uploads/manage/${dossier.uuid}?type=dossiers' />"><i class="glyphicon glyphicon-file"></i><spring:message code="label.posts.add.manageUploads"/></a>
	</div>
	
	<hr>
	
		<div class="form-item">
			<section>
				<label class="label">Public<span class="color-red">*</span> <form:errors path="name" /></label>
				<label class="radio"> 
					<form:radiobutton path="isPublic" value="1"/><i class="rounded-x"></i>Yes
				</label>
				<label class="radio">
					<form:radiobutton path="isPublic" value="0"/><i class="rounded-x"></i>No
				</label>
			</section>
		</div>
		
		<div class="form-item">
			<section>
				<label class="label">Choose Category <form:errors path="publication.categories" /></label> <label class="select select-multiple">
				<form:select path="publication.categories" items="${categories}" itemValue="id" itemLabel="name"/>
				
				</label>
			</section>
		</div>
		
		<div class="form-item">
			<section>
				<label class="label">
					Name<span class="color-red">*</span> <form:errors path="name" />
				</label>
				<label class="input"> 
					<form:input path="name" />
				</label>
			</section>
		</div>
		
		<div class="form-item">
			
			<section class="form-item">
				<label class="label">Description</label> <label class="textarea">
					<form:textarea path="description" cssClass="editorTextarea"/>
					
				</label>
				<div class="note">
					<strong>Note:</strong> A small description or a subtitle to your dossier
				</div>
			</section>
		</div>
		
		

	</fieldset>
	<footer>
		<button class="btn-u" type="submit" name="action" value="draft"><spring:message code="label.posts.add.saveDraft" /></button>
		<button class="btn-u" type="submit" name="action" value="publish"><spring:message code="label.posts.add.submitBtn" /></button>
		<button class="btn-u btn-u-default" name="action" value="cancel" type="button"><spring:message code="label.posts.add.backBtn" /></button>
	</footer>



</form:form>
<!-- TinyMCE -->
<script type="text/javascript" src="<c:url value='/js/tinymce_4.4.1/tinymce/js/tinymce/tinymce.min.js'/>"></script>	
<script>

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

</script>
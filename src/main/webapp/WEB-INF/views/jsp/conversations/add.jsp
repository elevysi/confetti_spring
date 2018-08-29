<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>
<c:url var="post_url" value="/conversations/add" />
<c:url var ="tinymceCss" value='/css/tinymce.css'/>

<c:url value="/profile/searchAjax/" var="searchProfileUrl" />

<form:form commandName="message" cssClass="sky-form addPostForm" method="post" action="${post_url}">
<input type="hidden" name="conversationUUID" value="${conversationUUID}"/>
	
	<fieldset>
	
		<div class="form-item">
			<section>
				<label class="label">Search Correspondent <span class="color-red">*</span> </label> 
				<label class="input">
					<input type="text" name="search_key" id="correspondentInput" class=""/>
				</label>
			</section>
		</div>
		<div class="form-item">
			<label>Correspondents <span class="color-red">*</span> </label>
			<div id="selectedItems"></div>
		</div>
	
		<section class="form-item">
			<label class="label">Message<span class="color-red">*</span> <form:errors path="message" /></label><label class="textarea">
				<form:textarea path="message" cssClass="editorTextarea" />
			</label>
		</section>
	</fieldset>
	
	<footer>
		<button class="btn-u" type="submit" name="action" value="publish">Send</button>
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

$(function() {
	var _i = 0;
	var container = document.getElementById('selectedItems');
	$("#correspondentInput").autocomplete({
		source: function (request, response) {
	           $.ajax({
	               url: "${searchProfileUrl}",
	               type: 'GET',
	               dataType: 'json',
	               data: request,
	               success: function (data) {
	                   response($.map(data, function (_profile) { 
	                        return {
	                            label: _profile.name,
	                            value: _profile.id
	                        };
	                   }));
	               }
	           });
	        },
	        html: true,
	        minLength: 2,
	        select: function(event, ui) {
	             var id = ui.item.value;
	             if(id != '') {
	            	 var checkbox = document.createElement('input');
	            	 var icon = document.createElement('i');
	            	 checkbox.type = "checkbox";
	            	 checkbox.name = "correspondentsInput";
	            	 checkbox.value = ui.item.value;
	            	 _currentVal = ++_i;
	            	 checkbox.id = "correspondentsInput"+_currentVal;
	            	 checkbox.setAttribute("checked", "true");

	            	 var label = document.createElement('label');
	            	 label.className = "checkbox";
	            	 label.htmlFor = "correspondentsInput"+_currentVal;
	            	 label.appendChild(checkbox);
	            	 label.appendChild(icon);
	            	 label.appendChild(document.createTextNode(ui.item.label));
	            	 
	            	 container.appendChild(label);
	             }
	        },
	
	});
	
});

</script>
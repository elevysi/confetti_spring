<%@ include file="../../layout/taglib.jsp"%>
<c:url value="/admin/searchPublication/" var="searchPublicationUrl" />
<c:url value="/admin/featured" var="postUrl" />
<form:form cssClass="sky-form addPostForm" method="post" action="${postUrl}">
<header>Choose the Featured Publications</header>		
	<fieldset>
	<div class="form-item">
			<section>
				<label class="label">Publication key <span class="color-red">*</span> </label> 
				<label class="input">
					<input type="text" name="search_key" id="publicationInput" class=""/>
				</label>
			</section>
	</div>
	<div class="form-item">
		<label>Featured <span class="color-red">*</span> </label>
		
		<div id="selectedItems"></div>
	</div>
	</fieldset>
	<footer>
		<button class="btn-u" type="submit" name="action">Save</button>
	</footer>
</form:form>



<script type="text/javascript">

$(function() {
	var _i = 0;
	var container = document.getElementById('selectedItems');
	$("#publicationInput").autocomplete({
		source: function (request, response) {
	           $.ajax({
	               url: "${searchPublicationUrl}",
	               type: 'GET',
	               dataType: 'json',
	               data: request,
	               success: function (data) {
	                   response($.map(data, function (_publicationMock) { 
	                        return {
	                            label: _publicationMock.displayField+" - "+_publicationMock.childType,
	                            value: _publicationMock.id
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
	            	 checkbox.name = "featuredPublications";
	            	 checkbox.value = ui.item.value;
	            	 _currentVal = ++_i;
	            	 checkbox.id = "featuredPublications"+_currentVal;
	            	 checkbox.setAttribute("checked", "true");

	            	 var label = document.createElement('label');
	            	 label.className = "checkbox";
	            	 label.htmlFor = "featuredPublications"+_currentVal;
	            	 label.appendChild(checkbox);
	            	 label.appendChild(icon);
	            	 label.appendChild(document.createTextNode(ui.item.label));
	            	
	            	 
	            	 container.appendChild(label);
	             }
	        },
	
	});
	
});

</script>
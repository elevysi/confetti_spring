<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp"%>
<!-- Jquery File Upload -->

<c:url var="album_post" value="/albums/edit" />
<c:url var="uploadLink" value="/uploads/album/add" />
<c:url var="uploadDeleteLink" value="/uploads/profileDelete" />
<c:set var="albumUuid" value="${albumEdit.uuid}" />

<form:form commandName="albumEdit" cssClass="sky-form addAlbumPost" method="post" action="${album_post}">
<form:hidden path="id"/>
<form:hidden path="uuid"/>
<header><spring:message code="label.albums.add.heading" /></header>	
<fieldset>

	<div class="form-item">
		<section>
			<label class="label">Public<span class="color-red">*</span> 
				<form:errors path="publicAlbum" /></label> <label class="select"> 
				<form:select path="publicAlbum" >
					<form:option value="1" label="YES" />
					<form:option value="0" label="NO" />
				</form:select>
			</label>
		</section>
	</div>
	
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
			<label class="label"><spring:message code="label.albums.add.title" /> <span class="color-red">*</span> 
				<form:errors path="name" /></label> <label class="input"> 
				<form:input path="name" />
			</label>
		</section>
	</div>
	<div class="form-item">
		<section>
			<label class="label"><spring:message code="label.albums.add.description" /> 
				<form:errors path="description" /></label> <label class="input"> 
				<form:input path="description" />
			</label>
		</section>
	</div>
	<div class="form-item">
		<section>
			<label class="label"><spring:message code="label.albums.add.place" /> 
				<form:errors path="place" /></label> <label class="input"> 
				<form:input path="place" />
			</label>
		</section>
	</div>
	
	<div class="row" id="uploadDelegationDropzone">



	<div class="panel panel-warning">
		<div class="panel-body">
			<p>
				<spring:message code="label.posts.addImage.explanation" />
			</p>
		</div>
	</div>

	<!--    ************************************************************************************************************************************* -->

	<div class="row">

		<div class="col-lg-7" id="adderLink">
			<!-- The fileinput-button span is used to style the file input field as button -->
			<span class="btn btn-success fileinput-button"> <i
				class="glyphicon glyphicon-plus"></i> <span><spring:message
						code="label.albums.add.addImage" /></span>
			</span>

		</div>

		<!--    ************************************************************************************************************************************* -->

		<div class="col-lg-5">
			<!-- The global file processing state -->
			<span class="fileupload-process">
				<div id="total-progress" class="progress progress-striped active"
					role="progressbar" aria-valuemin="0" aria-valuemax="100"
					aria-valuenow="0">
					<div class="progress-bar progress-bar-success" style="width: 0%;"
						data-dz-uploadprogress></div>
				</div>
			</span>
		</div>

		<!--    ************************************************************************************************************************************* -->

	</div>

	<div class="table table-striped" class="files" id="previews">

		<div id="delegationUploadSpace" class="file-row">
			<!-- This is used as the file preview template -->
			<div>
				<span class="preview"><img data-dz-thumbnail /></span>
			</div>

			<div>
				<p class="name" data-dz-name></p>
				<strong class="error text-danger" data-dz-errormessage></strong>
			</div>

			<div>
				<p class="size" data-dz-size></p>
				<div class="progress progress-striped active" role="progressbar"
					aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
					<div class="progress-bar progress-bar-success" style="width: 0%;"
						data-dz-uploadprogress></div>
				</div>
			</div>

			<div>

				<button data-dz-remove class="btn btn-danger delete">
					<i class="glyphicon glyphicon-trash"></i> <span><spring:message
							code="label.posts.add.addImage.delete" /></span>
				</button>
			</div>
		</div>

	</div>

</div>
<!--    ************************************************************************************************************************************* -->


</fieldset>

	<div id="imagesContainer">
	
		<c:if test="${not empty  albumUploads}">
			<section>
				<div class="row">
				<c:forEach items="${albumUploads}" var="savedUpload" varStatus="status">
					
					<div class="col-sm-4 sm-margin-bottom-30 image_block">
						<div class=" image_block_container">
							<div class="image_block_inner">
								<div class=""><form:checkbox path="uploads" value="${savedUpload.id}"/></div>
								<img class="img-responsive" src="<c:url value='/uploads/download?key=${savedUpload.keyIdentification}' />" alt="${savedUpload.altText}">
							</div>
							<div class="title"><c:out value="${savedUpload.filename}" /></div>
						
							  
						</div>
					</div>
					<c:if test="${status.count % 3 == 0}">
						</div>
						<div class="row">
					</c:if>
					
				</c:forEach>
				</div>
			</section>
		</c:if>
		
	</div>

<footer>
		<button class="btn-u" type="submit" name="action" value="draft"><spring:message code="label.albums.add.submitBtn" /></button>
</footer>


</form:form>



<script type="text/javascript" src="<c:url value='/js/dropzone-4.0.0/dist/dropzone.js'/>"></script>
<!-- TinyMCE -->
<script type="text/javascript" src="<c:url value='/js/tinymce_4.4.1/tinymce/js/tinymce/tinymce.min.js'/>"></script>	
<script>
	Dropzone.autoDiscover = false;

	var uploadedId = "";

	var upload_target_url = '${uploadLink}';
	var upload_delete_target_url = '${uploadDeleteLink}';


	var previewNode = document.querySelector("#delegationUploadSpace");
	previewNode.id = "";
	var previewTemplate = previewNode.parentNode.innerHTML;
	previewNode.parentNode.removeChild(previewNode);


	var myDropzone = new Dropzone("div#uploadDelegationDropzone", { // Make the whole body a dropzone
		url : upload_target_url, // Set the url,
		method : "post",
		thumbnailWidth : 80,
		thumbnailHeight : 80,
		parallelUploads : 20,
		maxFilesize : 20,
		maxFiles : 1,
		maxfilesexceeded : function(file) {
			this.removeAllFiles();

		},
		acceptedFiles : "image/*, .pdf, .doc, .docx",
		accept : function(file, done) {
		},
		previewTemplate : previewTemplate,
		autoQueue : false, // Make sure the files aren't queued until manually added
		previewsContainer : "#previews", // Define the container to display the previews
		clickable : ".fileinput-button", // Define the element that should be used as click trigger to select files.
		success : function(file, returnValue) {

			if (returnValue.code == 1) {
				/* $("#adderLink").hide(); */
				
			} else {
				alert(response.message);
				myDropzone.removeFile(file);
			}

		},
		sending : function(file, xhr, formData) {
			xhr.setRequestHeader(header, token);
			formData.append("uuid", "${albumUuid}");
		},
		removedfile : function(file) {
			$.ajax({
				type : 'POST',
				url : upload_delete_target_url,
				data : {
					type : 1
				},
				success : function(file, response) {
					$("#adderLink").show();
					uploadedId = "";
				},
			});
			var _ref;
			return (_ref = file.previewElement) != null ? _ref.parentNode
					.removeChild(file.previewElement) : void 0;
		}
	});

	myDropzone.on("addedfile", function(file) {
		myDropzone.processFile(file);
	});

	// Update the total progress bar
	myDropzone
			.on(
					"totaluploadprogress",
					function(progress) {
						document.querySelector("#total-progress .progress-bar").style.width = progress
								+ "%";
					});

	myDropzone.on("sending", function(file) {
		// Show the total progress bar when upload starts
		document.querySelector("#total-progress").style.opacity = "1";
		// And disable the start button
		//   file.previewElement.querySelector(".start").setAttribute("disabled", "disabled");
	});

	// Hide the total progress bar when nothing's uploading anymore
	myDropzone.on("queuecomplete", function(progress) {
		document.querySelector("#total-progress").style.opacity = "0";
	});
	
	$(document).ready(function(){
		
		
		$('#imagesContainer').find(':checkbox').each(function(){
			
			if(this.checked){
				$(this).parent().parent().parent()[0].style.border = '4px solid #38A';
			}
	    });
		
		$('.image_block input[type="checkbox"]').on("click", function(e){
			
			if((this).checked){
				$(this).parent().parent().parent()[0].style.border = '4px solid #38A';
			}else{
				$(this).parent().parent().parent()[0].style.border = '1px solid #ccc';
			}
			
			
		});
	
		
	});

</script>
<%@ include file="../../layout/taglib.jsp"%>
<style>

#dropzone {
    background: #ccccc;
    width: 150px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    font-weight: bold;
}
#dropzone.in {
    width: 600px;
    height: 200px;
    line-height: 200px;
    font-size: larger;
}
#dropzone.hover {
    background: lawngreen;
}
#dropzone.fade {
    -webkit-transition: all 0.3s ease-out;
    -moz-transition: all 0.3s ease-out;
    -ms-transition: all 0.3s ease-out;
    -o-transition: all 0.3s ease-out;
    transition: all 0.3s ease-out;
    opacity: 1;
}
</style>
<c:url var="getPictureURL" value="/uploads/get" />
<div style="width: 500px; padding: 20px">

	<input id="fileupload" type="file" name="files[]" data-url="<c:url value='/uploads/upload'/>" multiple>

	<div id="dropzone">Drop files here</div>

	<div id="progress">
		<div style="width: 0%;"></div>
	</div>

	<table id="uploaded-files" class="table">
		<tr>
			<th>File Name</th>
			<th>File Size</th>
			<th>File Type</th>
			<th>Download</th>
		</tr>
	</table>

</div>	
<script type="text/javascript" src="<c:url value='/js/dropzone-4.0.0/dist/dropzone.js'/>"></script>
<script>

$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
 
        done: function (e, data) {
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
 
                $("#uploaded-files").append(
                        $('<tr/>')
                        .append($('<td/>').text(file.filename))
                        .append($('<td/>').text(file.filesize))
                        .append($('<td/>').text(file.filemine))
                        .append($('<td/>').html("<a href='${getPictureURL}'>Click</a>"))
                        )//end $("#uploaded-files").append()
            }); 
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        },
 
        dropZone: $('#dropzone')
    });
});

</script>
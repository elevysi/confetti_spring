<%@ include file="../../layout/taglib.jsp"%>
<c:url var="checkSessionValidityUrl" value="/login/issessionvalid"/>
<c:url var="dialogLoginUrl" value="/modal/login"/>
<c:url var="dialogLoginPostUrl" value="/modal/doLogIn"/>
<c:url var="latestPublicationssUrl" value="/public/latestPublications"/>
<c:url var="tagsUrl" value="/public/tags/"/>
<c:url var="tagViewUrl" value="/public/tag/"/>
<c:url var ="viewPublicationUrl" value="/public/publication/view/"/>
<c:url var ="postImageUrl" value="/uploads/download?key="/>
<c:url var ="logoutUrl" value="/logout"/>
<c:url var ="logoutSuccess" value="/logout/ajax/page/success"/>
<c:url var ="logoutUrl" value="/logout"/>
<c:url var ="photoStreamUrl" value="/ui/public/rest/consumer/mean/snaps/"/>
<c:url var ="photoDownload" value="/ui/public/rest/consumer/mean/download/"/>



<script type="text/javascript">

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function confirmDelete(delForm, delUrl) { 
    if (confirm("Are you sure you want to delete?")) {
        delForm.action = delUrl;
        return true;                      
    }
    return false;                        
}

$(document).ready(function(){
	$(".triggerRemove").click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
	});
	
	$(".logoutBtn").click(function(e){
		e.preventDefault();
		
		$.ajax({
			type: "POST",
			url: "${logoutUrl}",
			data: token,
	        beforeSend:function(xhr){
	             xhr.setRequestHeader(header, token);
	        },
			dataType: "json",
			success: function(returnValue){
				console.log(returnValue);
				if(returnValue.code == 1){
					console.log(returnValue.message);
					window.location.href = "${logoutSuccess}";
					
				}else{
					console.log(returnValue.message);
					alert("Failed to log out!");
				}
				
			},
			error: function(data){
				console.log(data);
				alert("There was an error");
			}
		});
	});
	
	
	//Get Latests Publications through Ajax
	$.ajax({
		type: "GET",
		url: "${latestPublicationssUrl}",
		dataType: "json",
		success: function(latestPublications){
			//Get the html and put it where it goes\
			var _footerSubstitutionHtml = "";
			if(typeof(latestPublications) == "object"){
				for (var key in latestPublications) {
					if (latestPublications.hasOwnProperty(key)) {
						var latestPublication = latestPublications[key];
						
						_footerSubstitutionHtml += " <article> <h3 class=\"h6 g-mb-2\"><a class=\"g-color-white-opacity-0_8 g-color-white--hover\" href='${viewPublicationUrl}"+latestPublication.id+"/"+latestPublication.friendlyUrl+"'>"+latestPublication.friendlyUrl+"</a></h3><div class=\"small g-color-white-opacity-0_6\">"+latestPublication.created+"</div></article>";
					}
				}
				if($("#footerLatestPublications").length != 0){
					$("#footerLatestPublications").html(_footerSubstitutionHtml);
				}
				
			}
			
		}
	});
	
	$.ajax({
		type: "GET",
		url: "${tagsUrl}",
		dataType: "json",
		success: function(tags){
			//Get the html and put it where it goes\
			//var _tagsSubstitutionHtml = "<ul class=\"u-list-inline mb-0" id="siteTags\">";
			var _tagsSubstitutionHtml = "";
			if(typeof(tags) == "object"){
				for (var key in tags) {
					if (tags.hasOwnProperty(key)) {
						var tag = tags[key];
						_tagsSubstitutionHtml += "<li class=\"list-inline-item g-mb-10\"><a class=\"u-tags-v1 g-color-gray-dark-v4 g-color-white--hover g-bg-gray-light-v5 g-bg-primary--hover g-font-size-12 g-rounded-50 g-py-4 g-px-15\" href='${tagViewUrl}"+tag.name+"'>"+tag.name+"</a></li>";
					}
				}
				//_tagsSubstitutionHtml+="</ul>";
				if($("#siteTags").length != 0){
					$("#siteTags").html(_tagsSubstitutionHtml);
				}
				
			}
			
		}
	});
	
	
	
	//if($("#streamContainer").length != 0){
		
		$.ajax({
			type: "GET",
			url: "${photoStreamUrl}",
			//data: token,
		    //beforeSend:function(xhr){
		      //   xhr.setRequestHeader(header, token);
		    //},
			dataType: "json",
			success: function(_photoStream){
				var _stremPhotoList="";
				for (var _photoKey in _photoStream) {
					
					if (_photoStream.hasOwnProperty(_photoKey)) {
						var _photo = _photoStream[_photoKey];
						
						_stremPhotoList+="<div class=\"row\"><div class=\"u-block-hover g-brd-left g-brd-bottom g-brd-gray-light-v4 g-py-40\">";
						_stremPhotoList+="<img class=\"mx-auto g-width-100 u-block-hover__main--grayscale g-opacity-0_4 g-opacity-1--hover g-cursor-pointer\" alt=\"\" src=\"<c:url value='/meanUploads/"+_photo.path+"'/>\">";
						//_stremPhotoList+="<img class=\"hover-effect\" alt=\"\" src=\"${photoDownload}"+_photo.path+"/"+_photo.mime+"/"+_photo.fileName+"/"+_photo.size+"\">";
						_stremPhotoList+="</div></div>";
					}
					
					
				}
				console.log(_stremPhotoList);
				if($("#streamContainer").length != 0){
					$("#streamContainer").append(_stremPhotoList);
				}
			},
			error: function(data){
				console.log("Error photo stream "+data);
				
			}
		});
	//}

});
//Global Variables needed all along

var checkusersessionlink = '${checkSessionValidityUrl}';
var dialogloginlink = '${dialogLoginUrl}';
var dialogloginPostLink = '${dialogLoginPostUrl}';

var dialogReferer = false;

var loginReferedFromMainWindow = false;
var loginReferedFromModalWindow = false;
var loginReferedFromSaveOfModalWindow = false;

var showSave = true;

//Executed when button with class overlay is clicked, used to launch generic modal
$(document).on('click', '.modalOpen', function(e){
  e.preventDefault();
  var requestedLink = $(this).attr('href');
  
  if($(this).hasClass("noSave")){
	  showSave = false;
  } 
  
  dialogReferer = requestedLink;

  $.getJSON(checkusersessionlink, function(returnValue){
	  if(returnValue){
		  $("#modalholder .modal-body").html('');
		  $("#modalholder .modal-body").addClass('loader');
		  $("#modalholder #modalAdd").modal('show');
		  
		  	if (! showSave){
		  		$("#modalholder .saveBtn").hide();
			}

		  $.get(requestedLink, function(html){
		    $("#modalholder .modal-hrefLink").attr('href', requestedLink);
		    $("#modalholder .modal-body").removeClass('loader');
		    $("#modalholder .modal-body").html(html);
		    if (requestedLink.match('#')) {
		        if($('.nav-tabs a')){
		        	$('.nav-tabs a[href=#'+requestedLink.split('#')[1]+']').tab('show');
		        }
		    	
		    }
		  });
	  }else{
		  loginReferedFromModalWindow = true;
		  $("#loginmodalholder .modal-body").html('');
		  	$("#loginmodalholder .modal-body").addClass('loader');
			$("#loginmodalholder #dialog-login").modal('show');

		  $.get(dialogloginlink, function(html){
		    $("#loginmodalholder .modal-hrefLink").attr('href', dialogloginlink);
		    $("#loginmodalholder .modal-body").removeClass('loader');
		    $("#loginmodalholder .modal-body").html(html);
		  });

	  }
  });
});

//Global for AJAX POST

$(function(){

	//Used to ajax post content of the generic modal
  $("#modalholder #saveModal").click(function(e){
    e.preventDefault();
    
    var callingURL;
	if(showSave){
		callingURL = $(".modal-hrefLink").attr('href');
	}else{
		callingURL = $(this).attr('href');
	}

    $.getJSON(checkusersessionlink, function(returnValue){
    	if(returnValue){
    		
    		var addInput = $("<input>").attr("type", "hidden").attr("name", "_csrf.parameterName").val("${_csrf.token}");
    		$('#modalform').append($(addInput));
    		var _postindData = $('.modalform').serialize();
	    	if(callingURL){
    			if($(".modalForm").valid()){
    	    	
    	    		$.ajax({
	  	                  type: "POST",
	  	                  url: callingURL,
	  	                  data: _postindData,
	  	                  dataType: "json",
	  	                  beforeSend:function(xhr){
			  	 	         xhr.setRequestHeader(header, token)
			  	 	      },
	  	                  success: function(msg){
	  	                	  console.log(msg);
	  	                    //location.reload(true);
	  	                  },
	  	                  error: function(){
	  	                      alert("Failed to save!");
	  	                  }
     				 });
    	    	}else{
    	    		$("#modalholder #modalAdd").modal('hide');
    	    	}
    	        

    	    }else{
    	    	//Just Hide Modal
    	    	$("#modalholder #modalAdd").modal('hide');
    	    }
		  }else{
			  	loginReferedFromSaveOfModalWindow = true;

				//Hide the modal to show the login modal
				$("#modalholder #modalAdd").modal('hide');
			  	
			  	$("#loginmodalholder .modal-body").html('');
			  	$("#loginmodalholder .modal-body").addClass('loader');
				$("#loginmodalholder #dialog-login").modal('show');

			  $.get(dialogloginlink, function(html){
			    $("#loginmodalholder .modal-hrefLink").attr('href', dialogloginlink);
			    $("#loginmodalholder .modal-body").removeClass('loader');
			    $("#loginmodalholder .modal-body").html(html);
			  });

		  }
    });
    
    });

	//For the login modal dialog post
  $("#loginmodalholder #modalLoginSubmitBtn").click(function(e){
	    e.preventDefault();
	   
	    if($(".modalSignIn").valid()){
	        $.ajax({
	                  type: "POST",
	                  url: dialogloginPostLink,	
	                  data: $('.modalSignIn').serialize(),
	                  dataType : "json",
	                  success: function(returned){
	                	  	                	  		                  
	                	  if(returned.responseCode === "SUCCESS"){ //if LdapAuth fails, weird happens due ldap->bind(), better off checking strings

							//Hide the Login Window
							$("#loginmodalholder #dialog-login").modal('hide');

							if(loginReferedFromModalWindow){
								 $("#modalholder .modal-body").html('');
		            			  $("#modalholder .modal-body").addClass('loader');
		            			  $("#modalholder #modalAdd").modal('show');

		            			  $.get(dialogReferer, function(html){
		            			    $("#modalholder .modal-hrefLink").attr('href', dialogReferer);
		            			    $("#modalholder .modal-body").removeClass('loader');
		            			    $("#modalholder .modal-body").html(html);
		            			  });
							}else if(loginReferedFromMainWindow){
								 $("#bottomCommunicationDiv").html("<div class=\"alert alert-success attentionVIP\">Login was successful, you may now continue to the next step.</div>");
								 $("#bottomCommunicationDiv").delay("5000").fadeOut("slow");
							}else if(loginReferedFromSaveOfModalWindow){
								console.log("Here");
								$("#modalholder #modalAdd").modal('show');
								$("#modalholder #modalFlashCommunications").html("<div class=\"alert alert-success attentionVIP\">Login was successful, you may now continue to the next step.</div>");
								$("#modalholder #modalFlashCommunications").delay("5000").fadeOut("slow");
							}

	                		 
							  
			              }else{
			            	  var _closeBtn = '<button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>';
				              $("#communicationDiv").html("<div class=\"alert alert-danger attentionVIP\">"+_closeBtn+"Login Failed, please try again.</div>");
				              $("#communicationDiv").show();
			              }
		                  
	                   
	                      //$("#thanks").html(msg) //hide button and show thank you
	                      //$("#form-content").modal('hide'); //hide popup  
	                  },
	                  error: function(){
	                      alert("Failed to log you in.");
	                  }
	       });

	    }
	    
	    });

});


var FancyBox = function () {

    return {
            
        //Fancybox
        initFancybox: function () {
            jQuery(".fancybox").fancybox({
            groupAttr: 'data-rel',
            prevEffect: 'fade',
            nextEffect: 'fade',
            openEffect  : 'elastic',
            closeEffect  : 'fade',
            closeBtn: true,
            helpers: {
                title: {
                        type: 'float'
                    }
                }
            });
            
           /*  $('.ajaxFancyBox').on('click', function(e){
            	e.preventDefault();
                $.fancybox({
                   maxWidth    : 800,
                    maxHeight   : 600,
                    fitToView   : false,
                    width       : '70%',
                    height      : '70%',
                    autoSize    : false,
                    closeClick  : false,
                    closeEffect : 'fade',
                    openEffect  : 'elastic',
                    type: 'ajax',
                    href: $(this).attr("href"),
                });
            }); */

            $(".fbox-modal").fancybox({
                maxWidth    : 800,
                maxHeight   : 600,
                fitToView   : false,
                width       : '70%',
                height      : '70%',
                autoSize    : false,
                autoScale   : true,
                closeClick  : false,
                closeEffect : 'fade',
                openEffect  : 'elastic'
            });
        }

    };

}(); 
</script>
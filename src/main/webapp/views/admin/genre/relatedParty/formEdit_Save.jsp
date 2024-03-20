<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>    
<style>
</style>
<div class="main-content-inner">
    <div class="row">
        <div class="col-12 mt-5">
            <div class="row">
                <!-- Textual inputs start -->
                <div class="col-12 mt-5">
                    <div class="card">
                        <form:form class="card-body" role="form" id="form-edit-save" modelAttribute="model"> 
                            <h4 class="header-title title">Save Related Party</h4>
                            <div class="form-group">
                                <label for="code" class="col-form-label">Related Party Code</label>
                                <form:input path="code" id="code" cssClass="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-form-label">Name</label>
                                <form:input path="name" id="name" cssClass="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="overview" class="col-form-label">Overview</label>
                                <form:input path="overview" id="overview" cssClass="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="website" class="col-form-label">Website</label>
                                <form:input path="website" id="website" cssClass="form-control"/>                        
                            </div>
                            <div class="form-group">
                                <label for="followerCount" class="col-form-label">Follower Count</label>
                                <form:input path="followerCount" min="0" type="number" id="followerCount" cssClass="form-control"/>                        
                            </div>
                            <label class="col-form-label">Avatar (URL or UploadFile)</label>
                            <div class="form-group mb-3 changeTypeInputAvatar" style="display: flex; flex-direction: row; flex-wrap: nowrap;">
	                            <div>
	                            	<button class="btn btn-outline-secondary btn_changeTypeInputAvatar" type="button"> With URL <i class="fa fa-exchange"></i></button>
	                            </div>
	                            <div class="form-group" style="width: 100%; margin-bottom: 0%">
		                            <input type="text" class="form-control" id="avatar1" value="${model.avatar}">
	                        	</div>
	                        </div>
	                        <div class="input-group mb-3 changeTypeInputAvatar" style="display: none">
	                            <div class="input-group-prepend ">
	                            	<button class="btn btn-outline-secondary btn_changeTypeInputAvatar" type="button"> With UploadFile <i class="fa fa-exchange"></i></button>
	                            </div>
	                            <div class="custom-file">
		                            <input type="file" class="custom-file-input" id="avatar2" name="avatar2">
		                            <label class="custom-file-label" for="avatar2">Choose file</label>
	                        	</div>
	                        </div>
	                        <label for="example-email-input" class="col-form-label">Role</label>
	                        <div class="form-group">		                        
		                        <select id="role" name="roleId" class="form-control"> 
		                    		<c:forEach var="item" items="${roleList}">
		                    			<c:choose>
			                                <c:when test="${item.id==model.roleId}">
			                                    <option value="${item.id}" selected>${item.name}</option>
			                                </c:when>
			                                <c:otherwise>
			                                    <option value="${item.id}">${item.name}</option>
			                                </c:otherwise>
			                            </c:choose>
		                    		</c:forEach>	                    		
		                    	</select>
	                        </div>
	                        <div class="form-group">
                                <label for="status" class="col-form-label">Status</label>
                                <form:input path="status" id="status" cssClass="form-control"/>                       
                            </div>
	                        <div class="form-group" style="display: flex; justify-content: flex-end;"><button class="btn btn-primary btnSave_Edit" id="btnSave_Edit" type="button">Submit form</button></div>
                            <input type="hidden" name="id" value="${model.id}"> 
                        </form:form>
                    </div>
                </div>
                <!-- Textual inputs end -->
            </div>       
        </div>
    </div>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    
    <c:url var="listURL" value="/admin/relatedparty"/>
	<c:url var="formURL" value="/admin/relatedparty/edit"/>
	<c:url var="restAPI_URL" value="/api/admin/relatedparty"/>
	<c:url var="Upload_URL" value="/api/admin/UploadFile"/>
	<c:url var="UploadImg_URL" value="/uploads/"/>
    <script>
	    $(".btn_changeTypeInputAvatar").click(function(){
	    	$(".btn_changeTypeInputAvatar").parent().parent().show();
	    	$(this).parent().parent().hide();
	    });
	    
	    var getUrlParameter = function getUrlParameter(sParam) {
	        var sPageURL = window.location.search.substring(1),
	            sURLVariables = sPageURL.split('&'),
	            sParameterName,
	            i;

	        for (i = 0; i < sURLVariables.length; i++) {
	            sParameterName = sURLVariables[i].split('=');

	            if (sParameterName[0] === sParam) {
	                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
	            }
	        }
	        return false;
	    };	    
	    
	    var id = getUrlParameter('id');
	    if(id == false){
	    	$(".title").html("Save Related Party")		    	
	    }    
	    else {
	    	$(".title").html("Edit Related Party")		    	
	    }  
	    
	    $("#btnSave_Edit").click(function(e){
	    	e.preventDefault();
		    var data = {};
		    var formData = $('#form-edit-save').serializeArray();
		    $.each(formData, function (i, v) {
	            data[""+v.name+""] = v.value;	
		    })
		    data["avatar"] = $("#avatar1").val();	
		    if($("#avatar1").val()!="")		    	
		    	data["avatar"] = $("#avatar1").val();	
		    else {
		    	thumbnail = $("#avatar2")[0].files[0].name;
		    	data["avatar"] = "/images/"+thumbnail;
		    	var formData = new FormData();
				formData.append('fileName', $("#avatar2")[0].files[0]);
			    uploadFile(formData)
		    }		    	    
	    	if(id == false)
	 	    	save(data);	 	     
	 	    else 
	 	    	edit(data);	    	    	
	    });
	    
		function save(data){
			console.log(data);
			$.ajax({
		    	url: '${restAPI_URL}',
		        type: 'POST',
		        contentType: 'application/json',
		        data: JSON.stringify(data),
		        dataType: 'json',
		        success: function (result) {
		        	window.location.href = "${listURL}?message=insert_success";
		        },
		        error: function (error) {
		        	console.log(error)
		        }
		    });
		};
		
		function edit(data) {
			$.ajax({
		    	url: '${restAPI_URL}',
		        type: 'PUT',
		        contentType: 'application/json',
		        data: JSON.stringify(data),
		        dataType: 'json',
		        success: function (result) {
		        	window.location.href = "${listURL}?message=insert_success";
		        },
		        error: function (error) {
		        	window.location.href = "${listURL}?page=1&limit=2&message=error_system";
		        }
		    });
		};
	    function uploadFile(formData) {
	    	$.ajax({
				url: '${Upload_URL}',
				type: 'POST',
				contentType: false,
				processData: false,
				data: formData,
				success: function(result) {
				},
				error: function (error) {
					console.log(error)
				}
			})
	    }
	</script>
</div>

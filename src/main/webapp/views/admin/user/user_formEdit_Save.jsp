<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>    
<style>
.checkBoxList > span{
	margin: 0 1%; 
}
</style>
<div class="main-content-inner">
    <div class="row">
        <div class="col-12 mt-5">
            <div class="row">
                <!-- Textual inputs start -->
                <div class="col-12 mt-5">
                    <div class="card">
                        <form:form class="card-body" role="form" id="form-edit-save" modelAttribute="model"> 
                            <h4 class="header-title title">Save User</h4>
                            <div class="form-group">
                                <label for="code" class="col-form-label">User Code</label>
                                <form:input path="code" id="code" cssClass="form-control"/>
                                <!-- <input class="form-control" type="text" name="code" id="code" value="${model.code}"> -->
                            </div>                            
                            <div class="form-group mb-3 changeTypeInputThumbnail" style="display: flex; flex-direction: row; flex-wrap: nowrap;">
	                            <div>
	                            	<button class="btn btn-outline-secondary btn_changeTypeInputThumbnail" type="button"> With URL <i class="fa fa-exchange"></i></button>
	                            </div>
	                            <div class="form-group" style="width: 100%; margin-bottom: 0%">
		                            <input type="text" class="form-control" id="thumbnail1" value="${model.avatar}">
	                        	</div>
	                        </div>
	                        <div class="input-group mb-3 changeTypeInputThumbnail" style="display: none">
	                            <div class="input-group-prepend ">
	                            	<button class="btn btn-outline-secondary btn_changeTypeInputThumbnail" type="button"> With UploadFile <i class="fa fa-exchange"></i></button>
	                            </div>
	                            <div class="custom-file">
		                            <input type="file" class="custom-file-input" id="thumbnail2">
		                            <label class="custom-file-label" for="thumbnail2">Choose file</label>
	                        	</div>
	                        </div>
                            
                            <div class="form-group">
                                <label for="email" class="col-form-label">Email</label>
                                <form:input path="email" id="email" cssClass="form-control"/>
                               
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-form-label">Password</label>
                                <form:input path="passWord" id="passWord" cssClass="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="fullName" class="col-form-label">FullName</label>
                                <form:input path="fullName" id="fullName" cssClass="form-control"/>
                            </div>
                           	                         
                            <label for="example-email-input" class="col-form-label">Roles</label>
	                        <div class="form-group checkBoxList" style="display: flex; flex-wrap: wrap">		                        
		                        <form:checkboxes items="${roleCodeList}" path="roleCodeList"/>
	                        </div>
	                        
	                        <div class="form-group">
	                       		<label for="status" class="col-form-label">Tình trạng tài khoản</label>
	                        	<select name="status" class="form-control">
	                        		<c:if test="${model.status == 1}">
		                        		<option value="0">Khoá</option>
		                        		<option value="1" selected="selected"> Hoạt động</option>
	                        		</c:if>
	                        		<c:if test="${model.status != 1}">
		                        		<option value="0" selected="selected">Khoá</option>
		                        		<option value="1"> Hoạt động</option>
	                        		</c:if>
	                        	</select>								
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
    
    <c:url var="listURL" value="/admin/user"/>
	<c:url var="formURL" value="/admin/user/edit"/>
	<c:url var="restAPI_URL" value="/api/admin/user"/>
	<c:url var="Upload_URL" value="/api/admin/UploadFile"/>
	<c:url var="UploadImg_URL" value="/uploads/"/>
    <script>
	    $(".btn_changeTypeInputThumbnail").click(function(){
	    	$(".btn_changeTypeInputThumbnail").parent().parent().show();
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
	    	$(".title").html("Save User")		    	
	    }    
	    else {
	    	$(".title").html("Edit User")		    	
	    }  
	    
	    $("#btnSave_Edit").click(function(e){
	    	e.preventDefault();
		    var data = {};
		    var formData = $('#form-edit-save').serializeArray();
		    console.log("FormData: "+formData[3].value)
		    $.each(formData, function (i, v) {
	            data[""+v.name+""] = v.value;	
		    })		    
		    var roleCodeList = $('input[type=checkbox]:checked').map(function () {
	            return $(this).val();
	        }).get();
		    console.log("roleCodeList: "+roleCodeList)	
		    data["roleCodeList"] = roleCodeList;	
		    
		    data["avatar"] = $("#thumbnail1").val();	
		    thumbnailChoose = $("#thumbnail2")[0].files[0];
		    if(thumbnailChoose == null)		    	
		    	data["avatar"] = $("#thumbnail1").val();	
		    else {
		    	thumbnail = $("#thumbnail2")[0].files[0].name;
		    	data["avatar"] = "/template/uploads/"+thumbnail;
		    }
		    
	    	if(id == false)
	 	    	save(data);	 	     
	 	    else 
	 	    	edit(data);	    	    	
	    });
	    
		function save(data){			
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
		        	console.log(error)
		        }
		    });
		};
	   
	</script>
</div>
